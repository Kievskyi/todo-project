package com.denysdudnik.todo_project.controller;

import com.denysdudnik.todo_project.entity.Task;
import com.denysdudnik.todo_project.exception.PageNotFoundException;
import com.denysdudnik.todo_project.service.CookieService;
import com.denysdudnik.todo_project.service.ModelAndViewService;
import com.denysdudnik.todo_project.service.TaskService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import static java.util.Objects.isNull;

@Controller
@RequestMapping("/tasks")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MainController {

    final TaskService taskService;
    final CookieService cookieService;
    final ModelAndViewService viewService;

    @GetMapping("/")
    public ModelAndView showAllTasks(@CookieValue(name = "previousSessionPageNum", defaultValue = "0") String page,
                                     Model model, HttpServletResponse response) {

        return showPaginated(Integer.valueOf(page), model, response);
    }

    @GetMapping("/page/{pageNumber}")
    public ModelAndView showPaginated(@PathVariable(required = false) Integer pageNumber, Model model,
                                      HttpServletResponse response) {

        pageNumber = isNull(pageNumber) ? 0 : pageNumber;
        int pageSize = 5;
        Page<Task> allTasks;

        try {

            allTasks = taskService.getAllTasksByPageable(pageNumber, pageSize);
            model.addAttribute("tasks", allTasks.getContent());
            model.addAttribute("task", new Task());
            model.addAttribute("currentPage", pageNumber);
            model.addAttribute("totalPages", allTasks.getTotalPages());
            model.addAttribute("totalTasks", allTasks.getTotalElements());
            cookieService.setPageNumberToCookie(String.valueOf(pageNumber), response);

        } catch (PageNotFoundException e) {

            if (taskService.isPageDeletedRecently(pageNumber, pageSize)) {
                cookieService.setPageNumberToCookie
                        (String.valueOf(taskService.getTotalCountOfPages(pageSize) - 1), response);

                return viewService.buildModelAndViewRedirect("/tasks/").addAllObjects(model.asMap());
            } else {
                model.addAttribute("errorMessage", e.getMessage());

                cookieService.setPageNumberToCookie
                        (String.valueOf(taskService.getTotalCountOfPages(pageSize)), response);

                ModelAndView mv = viewService.buildModelAndView("html/error/error", HttpStatus.NOT_FOUND);
                mv.addObject("errorMessage", e.getMessage());

                return mv;
            }
        }

        return viewService.buildModelAndView("html/main/index", HttpStatus.OK);
    }

    @DeleteMapping("/")
    public ModelAndView deleteTask(@ModelAttribute Task task) {
        taskService.deleteTask(task);

        return viewService.buildModelAndViewRedirect("/tasks/");
    }

    @PatchMapping("/{id}")
    public ModelAndView editTask(@PathVariable Long id, @Valid @ModelAttribute Task task, BindingResult result,
                                 Model model) {

        model.addAttribute("task", task);

        if (result.hasErrors()) {

            return viewService.buildModelAndView("html/main/editTask", HttpStatus.BAD_REQUEST);
        }

        taskService.editTask(task, id);

        return viewService.buildModelAndViewRedirect("/tasks/");
    }

    @GetMapping("/editTask/{id}")
    public ModelAndView sendToEditTaskPage(@PathVariable("id") Long id, Model model) {
        Task task = taskService.findById(id);
        model.addAttribute("task", task);

        return viewService.buildModelAndView("html/main/editTask", HttpStatus.OK);
    }

    @GetMapping("/addTask")
    public ModelAndView goToAddNewTaskPage(Model model) {
        model.addAttribute("task", new Task());

        return viewService.buildModelAndView("html/main/addTask", HttpStatus.OK);
    }

    @PostMapping("/")
    public ModelAndView addNewTask(@Valid @ModelAttribute("task") Task task, BindingResult result) {

        if (result.hasErrors()) {
            return viewService.buildModelAndView("html/main/addTask", HttpStatus.BAD_REQUEST);
        }

        taskService.addNewTask(task);

        return viewService.buildModelAndViewRedirect("/tasks/");
    }
}

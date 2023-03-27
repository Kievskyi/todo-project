package com.denysdudnik.todo_project.controller;

import com.denysdudnik.todo_project.entity.Task;
import com.denysdudnik.todo_project.service.TaskDAOService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import static java.util.Objects.isNull;

@Controller
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class MainController {

    private final TaskDAOService taskService;

    @GetMapping("/")
    public String showAllTasks(Model model) {

        return showPaginated(0, model);
    }

    @GetMapping("/page/{pageNum}")
    public String showPaginated(@PathVariable(required = false) Integer pageNum, Model model) {
        pageNum = isNull(pageNum) ? 0 : pageNum;
        int size = 5;

        Page<Task> allTasks = taskService.getAllTasksByPageable(pageNum, size);
        model.addAttribute("tasks", allTasks.getContent());
        model.addAttribute("task", new Task());
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", allTasks.getTotalPages());
        model.addAttribute("totalTasks", allTasks.getTotalElements());

        return "html/main/index";
    }

    @DeleteMapping("/")
    public String deleteTask(@ModelAttribute Task task) {
        taskService.deleteTask(task);

        return "redirect:/tasks/";
    }

    @PatchMapping("/{id}")
    public String editTask(@PathVariable("id") Long id, @Valid @ModelAttribute("task") Task task, BindingResult result, Model model) {
        model.addAttribute("task", task);

        if (result.hasErrors()) {
            return "html/main/editTask";
        }

        taskService.editTask(task, id);

        return "redirect:/tasks/";
    }

    @GetMapping("/editTask/{id}")
    public String sendToEditTaskPage(@PathVariable("id") Long id, Model model) {
        Task task = taskService.findById(id);
        model.addAttribute("task", task);

        return "html/main/editTask";
    }

    @GetMapping("/addTask")
    public String goToAddNewTaskPage(Model model) {
        model.addAttribute("task", new Task());

        return "html/main/addTask";
    }

    @PostMapping("/")
    public String addNewTask(@Valid @ModelAttribute("task") Task task, BindingResult result) {

        if (result.hasErrors()) {
            return "html/main/addTask";
        }

        taskService.addNewTask(task);

        return "redirect:/tasks/";
    }
}

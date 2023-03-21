package com.denysdudnik.todo_project.controller;

import com.denysdudnik.todo_project.entity.Task;
import com.denysdudnik.todo_project.service.TaskDAOService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static java.util.Objects.isNull;

@Controller
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class MainController {

    private final TaskDAOService taskService;

    @GetMapping("/")
    public String showAllTasks(Model model) {
        System.out.println("showAllTasks");

        return showPaginated(0, model);
    }

    @GetMapping("/page/{pageNum}")
    public String showPaginated(@PathVariable(required = false) int pageNum,
                                Model model) {
        System.out.println("showPaginated");
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
    public String editTask(@ModelAttribute Task task, @PathVariable("id") Long id) {
        taskService.editTask(task, id);

        return "redirect:/tasks/";
    }

    @GetMapping("/{id}")
    public String sendToEditTaskPage(@PathVariable("id") Long id, Model model) {
        Task task = taskService.findById(id);
        model.addAttribute("task", task);
        System.out.println("editTask");

        return "html/main/editTask";
    }

    @PostMapping("/")
    public String addNewTask(@ModelAttribute("task") Task task) {
        taskService.addNewTask(task);

        return "redirect:/tasks/";
    }
}

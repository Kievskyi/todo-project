package com.denysdudnik.todo_project.controller;

import com.denysdudnik.todo_project.entity.Task;
import com.denysdudnik.todo_project.service.TaskDAOService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class MainController {

    private final TaskDAOService taskService;

    @GetMapping(value = "/")
    public String showAllTasks(Model model) {
        List<Task> allTasks = taskService.getAllTasks();
        model.addAttribute("tasks", allTasks);

        return "main/index";
    }

    @DeleteMapping("/delete")
    public String deleteTask(@PathVariable("id") Long id) {
        taskService.deleteTask(id);
        
        return "main/index";
    }

    @PostMapping("/edit")
    public String editTask(@PathVariable("id") Long id) {
        return null;
    }

    @GetMapping("/newTask")
    public String newTaskFormPage(Model model) {
        model.addAttribute("task", new Task());

        return "main/newTask";
    }

    @PostMapping()
    public String addNewTask(@ModelAttribute("task") Task task) {
        taskService.addNewTask(task);

        return "redirect:/tasks/";
    }
}

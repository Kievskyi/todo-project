package com.denysdudnik.todo_project.controller;

import com.denysdudnik.todo_project.entity.Task;
import com.denysdudnik.todo_project.service.TaskDAOService;
import lombok.RequiredArgsConstructor;
import org.hibernate.engine.jdbc.Size;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Objects.isNull;

@Controller
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class MainController {

    private final TaskDAOService taskService;

    @GetMapping(value = {"/", "/{number}/{size}"})
    public String showAllTasks(@PathVariable(value = "size",required = false) Integer size,
                               @PathVariable(value = "number", required = false) Integer number, Model model) {

        number = isNull(number) ? 0 : number;
        size = isNull(size) ? 3 : size;

        List<Task> allTasks = taskService.getAllTasks(number, size);
        model.addAttribute("tasks", allTasks);
        model.addAttribute("task", new Task());
        model.addAttribute("countOfPages", taskService.getCountOfPages(number, size));

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

        return "html/main/editTask";
    }

    @PostMapping("/")
    public String addNewTask(@ModelAttribute("task") Task task) {
        taskService.addNewTask(task);

        return "redirect:/tasks/";
    }
}

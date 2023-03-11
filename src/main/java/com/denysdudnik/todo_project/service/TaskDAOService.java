package com.denysdudnik.todo_project.service;

import com.denysdudnik.todo_project.entity.Task;

import java.util.List;

public interface TaskDAOService {

    public List<Task> getAllTasks();

    public Task addNewTask(Task task);

    public Task editTask(Task task);

    public Task deleteTask(Task task);
}

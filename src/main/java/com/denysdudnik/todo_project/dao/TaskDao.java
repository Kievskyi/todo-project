package com.denysdudnik.todo_project.dao;

import com.denysdudnik.todo_project.entity.Task;

import java.util.List;

public interface TaskDao {

    public List<Task> getAllTasks();

    public Task addNewTask(Task task);

    public Task editTask(Task task);

    public Task deleteTask(Task task);
}

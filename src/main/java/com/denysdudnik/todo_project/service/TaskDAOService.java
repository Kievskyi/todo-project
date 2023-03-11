package com.denysdudnik.todo_project.service;

import com.denysdudnik.todo_project.entity.Task;

import java.util.List;

public interface TaskDAOService {

    public List<Task> getAllTasks();

    public void addNewTask(Task task);

    public void editTask(Task task, long id);

    public void deleteTask(long id);
}

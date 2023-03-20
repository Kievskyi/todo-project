package com.denysdudnik.todo_project.service;

import com.denysdudnik.todo_project.entity.Task;

import java.util.List;

public interface TaskDAOService {

    public Task findById(Long id);

    public List<Task> getAllTasks(int number, int size);

    public void addNewTask(Task task);

    public void editTask(Task task, long id);

    public void deleteTask(Task task);

    public Integer getCountOfPages(int number, int size);
}

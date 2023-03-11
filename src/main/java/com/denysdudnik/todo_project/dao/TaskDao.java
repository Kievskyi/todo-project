package com.denysdudnik.todo_project.dao;

import com.denysdudnik.todo_project.entity.Task;

import java.util.List;

public interface TaskDao {

    public List<Task> getAllTasks();

    public void addNewTask(Task task);

    public void editTask(Task task, long id);

    public void deleteTask(long id);
}

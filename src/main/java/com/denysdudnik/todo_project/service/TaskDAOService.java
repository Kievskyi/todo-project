package com.denysdudnik.todo_project.service;

import com.denysdudnik.todo_project.entity.Task;
import org.springframework.data.domain.Page;

public interface TaskDAOService {

    public Task findById(Long id);

    public Page<Task> getAllTasksByPageable(int number, int size);

    public void addNewTask(Task task);

    public void editTask(Task task, long id);

    public void deleteTask(Task task);
}

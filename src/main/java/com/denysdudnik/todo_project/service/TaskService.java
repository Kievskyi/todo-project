package com.denysdudnik.todo_project.service;

import com.denysdudnik.todo_project.entity.Task;
import com.denysdudnik.todo_project.exception.PageNotFoundException;
import org.springframework.data.domain.Page;

public interface TaskService {

    Task findById(Long id);

    Page<Task> getAllTasksByPageable(int number, int size) throws PageNotFoundException;

    Integer getTotalCountOfPages(int size);

    boolean isPageDeletedRecently(int pageNumber, int pageSize);

    void addNewTask(Task task);

    void editTask(Task task, long id);

    void deleteTask(Task task);
}

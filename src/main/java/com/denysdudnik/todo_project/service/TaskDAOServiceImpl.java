package com.denysdudnik.todo_project.service;

import com.denysdudnik.todo_project.dao.TaskDao;
import com.denysdudnik.todo_project.entity.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TaskDAOServiceImpl implements TaskDAOService{

    private final TaskDao taskDao;

    @Override
    public List<Task> getAllTasks() {
        return taskDao.getAllTasks();
    }

    @Override
    public Task addNewTask(Task task) {
        return taskDao.addNewTask(task);
    }

    @Override
    public Task editTask(Task task) {
        return taskDao.editTask(task);
    }

    @Override
    public Task deleteTask(Task task) {
        return taskDao.deleteTask(task);
    }
}

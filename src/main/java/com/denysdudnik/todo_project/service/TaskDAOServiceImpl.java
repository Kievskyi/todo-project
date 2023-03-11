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
    public void addNewTask(Task task) {
        taskDao.addNewTask(task);
    }

    @Override
    public void editTask(Task task, long id) {
        taskDao.editTask(task, id);
    }

    @Override
    public void deleteTask(long id) {
        taskDao.deleteTask(id);
    }
}

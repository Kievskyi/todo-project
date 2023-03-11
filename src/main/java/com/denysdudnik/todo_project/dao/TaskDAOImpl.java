package com.denysdudnik.todo_project.dao;

import com.denysdudnik.todo_project.entity.Task;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class TaskDAOImpl implements TaskDao{

    private final SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<Task> getAllTasks() {
        Session session = sessionFactory.openSession();

        return session.createQuery("from Task", Task.class).getResultList();
    }

    @Override
    public Task addNewTask(Task task) {
        return null;
    }

    @Override
    public Task editTask(Task task) {
        return null;
    }

    @Override
    public Task deleteTask(Task task) {
        return null;
    }
}

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
    @Transactional
    public void addNewTask(Task task) {
        Session session = sessionFactory.openSession();
        session.persist(task);
    }

    @Override
    @Transactional
    public void editTask(Task task, long id) {
        Session session = sessionFactory.openSession();
        Task taskFromDB = session.find(Task.class, id);
        taskFromDB.setDescription(task.getDescription());
        taskFromDB.setStatus(task.getStatus());
    }

    @Override
    @Transactional
    public void deleteTask(long id) {
        Session session = sessionFactory.openSession();
        Task taskFromDb = session.find(Task.class, id);
        session.remove(taskFromDb);
    }
}

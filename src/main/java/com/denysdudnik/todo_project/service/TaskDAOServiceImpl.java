package com.denysdudnik.todo_project.service;

import com.denysdudnik.todo_project.entity.Task;
import com.denysdudnik.todo_project.repository.TaskRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskDAOServiceImpl implements TaskDAOService {

    private final TaskRepository taskRepository;

    @Override
    public Task findById(Long id) {

        return taskRepository.findById(id).get();
    }

    @Override
    @Transactional
    public List<Task> getAllTasks(int number, int size) {
        Pageable page =  PageRequest.of(number, size);
        return taskRepository.findAll(page).stream().collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void addNewTask(Task task) {
        taskRepository.save(task);
    }

    @Override
    @Transactional
    public void editTask(Task task, long id) {
        taskRepository.save(task);
    }

    @Override
    @Transactional
    public void deleteTask(Task task) {
        taskRepository.delete(task);
    }
}

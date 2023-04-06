package com.denysdudnik.todo_project.service;

import com.denysdudnik.todo_project.entity.Task;
import com.denysdudnik.todo_project.exception.PageNotFoundException;
import com.denysdudnik.todo_project.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public Task findById(Long id) {
        return taskRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    @Transactional
    public Page<Task> getAllTasksByPageable(int pageNumber, int pageSize) throws PageNotFoundException {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        Page<Task> pages = taskRepository.findAll(page);

        if (pages.isEmpty()) {
            throw new PageNotFoundException();
        }

        return pages;
    }

    @Override
    public boolean isPageDeletedRecently(int pageNumber, int pageSize) {
        return (getTotalCountOfPages(pageSize)) == pageNumber;
    }

    @Override
    @Transactional
    public Integer getTotalCountOfPages(int size) {
        Pageable pageable = PageRequest.of(1, size);
        Page<Task> page = taskRepository.findAll(pageable);

        return page.getTotalPages();
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

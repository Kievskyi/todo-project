package com.denysdudnik.todo_project.service;

import com.denysdudnik.todo_project.entity.Task;
import com.denysdudnik.todo_project.enums.Status;
import com.denysdudnik.todo_project.exception.PageNotFoundException;
import com.denysdudnik.todo_project.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringJUnitConfig(classes = MockitoExtension.class)
@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {

    @Mock
    TaskRepository taskRepository;

    @InjectMocks
    TaskServiceImpl taskService;

    @Test
    void whenWeGetExistedTaskAllFinishSuccess() {
        //given
        Task task = buildTask();
        //when
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        //then
        assertEquals(task, taskService.findById(1L));
    }

    @Test
    void whenWeGetNotExistedTaskThenThrowEntityNotFoundException() {
        //when
        when(taskRepository.findById(1L)).thenReturn(Optional.empty());
        //then
        assertThrows(EntityNotFoundException.class, () -> taskService.findById(1L));
    }

    @Test
    void whenWeTryingToGetNonexistentPage_ThenThrowPageNotFound() {
        //given
        Pageable page = PageRequest.of(5, 5);
        //when
        when(taskRepository.findAll(page)).thenReturn(Page.empty());
        //then
        assertThrows(PageNotFoundException.class, () -> taskService.getAllTasksByPageable(5, 5));
    }

    Task buildTask() {
        return Task.builder()
                .id(1L)
                .status(Status.DONE)
                .description("hello")
                .build();
    }
}
package com.denysdudnik.todo_project.controller;

import com.denysdudnik.todo_project.entity.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class EditTaskTest extends AbstractTest {

    @Test
    @Transactional
    public void whenDescriptionIsNull_ThenReturnToTheSamePageWithBadRequestStatus() throws Exception {
        Task task = buildTask();
        task.setDescription(null);

        mockMvc.perform(patch("/tasks/{id}", task.getId())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .flashAttr("task", task))
                .andExpect(model().hasErrors())
                .andExpect(view().name("html/main/editTask"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Transactional
    public void whenTaskSuccessfullyEdited_ThenDoRedirect() throws Exception {
        Task task = buildTask();
        Task newTask = buildTask();
        newTask.setDescription("bye");

        mockMvc.perform(patch("/tasks/{id}", task.getId())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .flashAttr("task", newTask))
                .andExpect(status().is3xxRedirection())
                .andExpect(model().hasNoErrors())
                .andExpect(redirectedUrl("/tasks/"));

        Task editedTask = taskService.findById(task.getId());
        Assertions.assertEquals(editedTask.getDescription(), newTask.getDescription());
    }
}

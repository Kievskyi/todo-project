package com.denysdudnik.todo_project.controller;

import com.denysdudnik.todo_project.entity.Task;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AddTaskTest extends AbstractTest {

    @Test
    public void whenTaskDescriptionIsEmpty_ThenReturnSamePageWithBadRequestStatus() throws Exception {
        Task task = buildTask();
        task.setDescription(null);

        mockMvc.perform(post("/tasks/")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .flashAttr("task", task))
                .andExpect(model().hasErrors())
                .andExpect(view().name("html/main/addTask"))
                .andExpect(status().isBadRequest());
    }
}

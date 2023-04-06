package com.denysdudnik.todo_project.controller;


import com.denysdudnik.todo_project.entity.Task;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

public class DeleteTaskTest extends AbstractTest {

    @Test
    public void whenTaskSuccessfullyDeleted_ThenDoRedirections() throws Exception {
        Task task = buildTask();

        mockMvc.perform(delete("/tasks/")
                        .flashAttr("task", task))
                .andExpect(redirectedUrl("/tasks/"));
    }
}

package com.denysdudnik.todo_project.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ShowPaginatedTest extends AbstractTest {

    @Test
    @Transactional
    public void whenNoMoreTasksOnCurrentPage_ThenDoRedirectToPreviousPage() throws Exception {

        mockMvc.perform(get("/tasks/page/{pageNumber}", 5)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/tasks/"));
    }

    @Test
    @Transactional
    public void whenTryingToGetNonexistentPage_ThenShowErrorPage() throws Exception {

        mockMvc.perform(get("/tasks/page/{pageNumber}", 100)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isNotFound())
                .andExpect(view().name("html/error/error"));
    }
}

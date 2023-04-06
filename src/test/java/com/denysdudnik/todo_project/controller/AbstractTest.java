package com.denysdudnik.todo_project.controller;

import com.denysdudnik.todo_project.configuration.SpringConfig;
import com.denysdudnik.todo_project.configuration.SpringWebInitializer;
import com.denysdudnik.todo_project.entity.Task;
import com.denysdudnik.todo_project.enums.Status;
import com.denysdudnik.todo_project.service.CookieService;
import com.denysdudnik.todo_project.service.ModelAndViewService;
import com.denysdudnik.todo_project.service.TaskService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = {SpringConfig.class, SpringWebInitializer.class})
@WebAppConfiguration
@Sql(scripts = "classpath:sql_for_testing/initTest.sql", config = @SqlConfig(encoding = "UTF-8"))
public abstract class AbstractTest {

    @Autowired
    WebApplicationContext applicationContext;

    MockMvc mockMvc;

    @Autowired
    TaskService taskService;

    @Autowired
    ModelAndViewService viewService;

    @Autowired
    CookieService cookieService;

    @BeforeAll
    public void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(applicationContext)
                .addFilter((request, response, chain) -> {
                    chain.doFilter(request, response);
                })
                .build();
    }


    Task buildTask() {
        return Task.builder()
                .id(1L)
                .status(Status.DONE)
                .description("hello")
                .build();
    }
}

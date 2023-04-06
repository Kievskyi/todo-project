package com.denysdudnik.todo_project.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;

public interface ModelAndViewService {

    ModelAndView buildModelAndView(String path, HttpStatus status);

    ModelAndView buildModelAndViewRedirect(String path);
}

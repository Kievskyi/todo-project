package com.denysdudnik.todo_project.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Service
public class ModelAndViewServiceImpl implements ModelAndViewService {
    @Override
    public ModelAndView buildModelAndView(String path, HttpStatus status) {
        ModelAndView modelAndView = new ModelAndView(path);
        modelAndView.setStatus(status);

        return modelAndView;
    }

    @Override
    public ModelAndView buildModelAndViewRedirect(String path) {
        RedirectView rv = new RedirectView();
        rv.setUrl(path);
        rv.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
        String url = "redirect:" + path;

        return new ModelAndView(rv);
    }
}

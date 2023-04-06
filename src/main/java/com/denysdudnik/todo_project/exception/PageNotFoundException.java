package com.denysdudnik.todo_project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PageNotFoundException extends IllegalArgumentException {

    public PageNotFoundException() {
        super("Page not found");
    }
}

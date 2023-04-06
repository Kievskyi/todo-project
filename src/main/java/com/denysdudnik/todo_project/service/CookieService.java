package com.denysdudnik.todo_project.service;

import jakarta.servlet.http.HttpServletResponse;

public interface CookieService {

    void setPageNumberToCookie(String pageNumber, HttpServletResponse response);
}

package com.denysdudnik.todo_project.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

@Service
public class CookieServiceImpl implements CookieService {
    @Override
    public void setPageNumberToCookie(String pageNumber, HttpServletResponse response) {

        Cookie cookie = new Cookie("previousSessionPageNum", pageNumber);
        cookie.setMaxAge(60 * 60 * 24 * 30);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}

package com.example.springservlet.web.frontcontroller.v5;

import com.example.springservlet.web.frontcontroller.MyModelAndView;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface MyHandlerAdapter {

    boolean supports(Object handler);

    MyModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException;

}

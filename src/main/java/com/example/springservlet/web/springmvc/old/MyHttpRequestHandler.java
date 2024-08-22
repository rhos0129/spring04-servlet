package com.example.springservlet.web.springmvc.old;

import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

// 서블릿과 가장 유사한 형태의 핸들러 HttpRequestHandler 상속
// - BeanNameUrlHandlerMapping, HttpRequestHandlerAdapter
@Component("/springmvc/request-handler") // 스프링 빈의 이름으로 URL 매핑
public class MyHttpRequestHandler implements HttpRequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("MyHttpRequestHandler.handleRequest");
    }

}

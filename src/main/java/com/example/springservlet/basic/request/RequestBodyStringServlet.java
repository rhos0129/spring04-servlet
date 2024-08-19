package com.example.springservlet.basic.request;


import org.springframework.util.StreamUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "requestBodyStringServlet", urlPatterns = "/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // HTTP 메시지 바디의 데이터 읽기
        ServletInputStream inputStream = request.getInputStream(); // request정보 -> byte코드
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8); // byte코드 -> String

        System.out.println("messageBody = " + messageBody);

        response.getWriter().write("ok");
    }
}

package com.example.springservlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet(name = "requestHeaderServlet", urlPatterns = "/request-header")
public class RequestHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        printStartLine(request);
        printHeaders(request);

        // header 편의 조회
        host(request);
        acceptLanguage(request);
        cookie(request);
        content(request);

        // 기타정보 조회
        remote(request);
        local(request);

    }

    // [start-line]
    private void printStartLine(HttpServletRequest request) {
        System.out.println("[request start-line]");

        System.out.println("request.getMethod() = " + request.getMethod());
        System.out.println("request.getProtocal() = " + request.getProtocol());
        System.out.println("request.getScheme() = " + request.getScheme());
        System.out.println("request.getRequestURL() = " + request.getRequestURL());
        System.out.println("request.getRequestURI() = " + request.getRequestURI());
        System.out.println("request.getQueryString() = " + request.getQueryString());
        System.out.println("request.isSecure() = " + request.isSecure());

        System.out.println();
    }

    // [header]
    private void printHeaders(HttpServletRequest request) {
        System.out.println("[request header]");

//        Enumeration<String> headerNames = request.getHeaderNames();
//        while (headerNames.hasMoreElements()) {
//            String headerName = headerNames.nextElement();
//            System.out.println(headerName + ": " + request.getHeader(headerName));
//        }

        request.getHeaderNames().asIterator()
                .forEachRemaining(headerName -> System.out.println(headerName + ": " + request.getHeader(headerName)));

        System.out.println();
    }

    // [header] 편의 조회 - Host
    private void host(HttpServletRequest request) {
        System.out.println("[header 편의 조회 - Host]");
        System.out.println("request.getServerName() = " + request.getServerName());
        System.out.println("request.getServerPort() = " + request.getServerPort());
        System.out.println();
    }

    // [header] 편의 조회 - Accept-Language
    private void acceptLanguage(HttpServletRequest request) {
        System.out.println("[header 편의 조회 -  Accept-Language]");
        request.getLocales().asIterator()
                .forEachRemaining(locale -> System.out.println("locale = " + locale));
        System.out.println("request.getLocale() = " + request.getLocale());
        System.out.println();
    }

    // [header] 편의 조회 - cookie
    private void cookie(HttpServletRequest request) {
        System.out.println("[header 편의 조회 -  cookie 편의 조회]");
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                System.out.println(cookie.getName() + ": " + cookie.getValue());
            }
        }
        System.out.println();
    }

    // [header] 편의 조회 - Content
    private void content(HttpServletRequest request) {
        System.out.println("[header 편의 조회 - Content 편의 조회]");
        System.out.println("request.getContentType() = " + request.getContentType());
        System.out.println("request.getContentLength() = " + request.getContentLength());
        System.out.println("request.getCharacterEncoding() = " + request.getCharacterEncoding());
        System.out.println();
    }

    // 기타 정보 조회 - Remote
    private void remote(HttpServletRequest request) {
        System.out.println("[기타 정보 조회 - Remote]");
        System.out.println("request.getRemoteHost() = " + request.getRemoteHost());
        System.out.println("request.getRemoteAddr() = " + request.getRemoteAddr());
        System.out.println("request.getRemotePort() = " + request.getRemotePort());
        System.out.println();
    }

    // 기타 정보 조회 - Local
    private void local(HttpServletRequest request) {
        System.out.println("[기타 정보 조회 - Local]");
        System.out.println("request.getLocalName() = " + request.getLocalName());
        System.out.println("request.getLocalAddr() = " + request.getLocalAddr());
        System.out.println("request.getLocalPort() = " + request.getLocalPort());
        System.out.println();
    }

}

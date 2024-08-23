package com.example.springservlet.web.springmvc.old;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 스프링이 제공하는 Controller 인터페이스 상속 (과거에 주로 사용)
// - BeanNameUrlHandlerMapping, SimpleControllerHandlerAdapter
@Component("/springmvc/old-controller") // 스프링 빈의 이름으로 URL 매핑
public class OldController implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("OldController.handleRequest");
        return new ModelAndView("new-form");
    }

}


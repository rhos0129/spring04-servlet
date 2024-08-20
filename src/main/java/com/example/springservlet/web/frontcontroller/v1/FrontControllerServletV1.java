package com.example.springservlet.web.frontcontroller.v1;

import com.example.springservlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import com.example.springservlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import com.example.springservlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {

    // 매핑정보 저장소 controllerMap 생성 - URL과 controller 매핑
    private Map<String, ControllerV1> controllerMap = new HashMap<>();

    // controllerMap에 매핑정보 저장
    public FrontControllerServletV1() {
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // controllerMap에서 URL로 controller 조회
        String requestURI = request.getRequestURI();
        ControllerV1 controller = controllerMap.get(requestURI); // 다형성 활용

        // controllerMap에 없다면 상태코드 404 설정
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 컨트롤러 로직 실행
        controller.process(request, response);
    }

}

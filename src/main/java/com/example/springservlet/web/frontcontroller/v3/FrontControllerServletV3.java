package com.example.springservlet.web.frontcontroller.v3;

import com.example.springservlet.web.frontcontroller.MyModelAndView;
import com.example.springservlet.web.frontcontroller.MyView;
import com.example.springservlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import com.example.springservlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import com.example.springservlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        ControllerV3 controller = controllerMap.get(requestURI);

        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // request의 파라미터를 컨트롤러에게 전달  -> 컨트롤러에서는  HttpServletRequest, HttpServletResponse 제거 (서블릿 종속성 제거)
        // 컨트롤러는 로직을 실행하고 viewName과 model을 담고 있는 MyModelAndView 반환
        Map<String, String> paramMap = createParamMap(request);
        MyModelAndView mv = controller.process(paramMap);

        // MyModelAndView의 viewName으로 MyView 생성 - viewResolver
        String viewName = mv.getViewName();
        MyView view = viewResolver(viewName);

        // model도 전달하여 뷰 렌더링
        view.render(mv.getModel(), request, response);
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }

}

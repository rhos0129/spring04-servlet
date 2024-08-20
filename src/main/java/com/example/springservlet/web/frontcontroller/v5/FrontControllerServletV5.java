package com.example.springservlet.web.frontcontroller.v5;

import com.example.springservlet.web.frontcontroller.MyModelAndView;
import com.example.springservlet.web.frontcontroller.MyView;
import com.example.springservlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import com.example.springservlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import com.example.springservlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import com.example.springservlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    // 매핑정보 저장소 handlerMap 생성 - URL과 handler(controller) 매핑
    private final Map<String, Object> handlerMap = new HashMap<>();

    // 어댑터 배열 handlerAdapters 생성
    private final List<MyHandlerAdapter> myHandlerAdapters = new ArrayList<>();

    public FrontControllerServletV5() {
        initHandlerMappingMap();
        initHandlerAdapters();
    }

    // handlerMap에 매핑정보 저장
    private void initHandlerMappingMap() {
        handlerMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());
    }

    // handlerAdapters에 handlerAdapter 저장
    private void initHandlerAdapters() {
        myHandlerAdapters.add(new ControllerV3HandlerAdapter());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // handlerMap에서 URL로 handler(controller) 조회
        Object handler = getHandler(request);

        // handlerMap에 없다면 상태코드 404 설정
        if (handler == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // handler(controller)를 처리할 수 있는 handlerAdapter를 찾아 로직 실행 후 MyModelAndView 반환
        // -> 프론트 컨트롤러가 바로 실제 컨트롤러를 호출하는 게 아니라 어댑터를 통해서 호출한다.
        MyHandlerAdapter adapter = getHandlerAdapter(handler);
        MyModelAndView mv = adapter.handle(request, response, handler);

        String viewName = mv.getViewName();
        MyView view = viewResolver(viewName);

        view.render(mv.getModel(), request, response);

    }

    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return handlerMap.get(requestURI);
    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        for (MyHandlerAdapter adapter : myHandlerAdapters) {
            if (adapter.supports(handler)) {
                return adapter;
            }
        }
        throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다. handler=" + handler);
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

}

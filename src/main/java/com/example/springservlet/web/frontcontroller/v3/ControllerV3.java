package com.example.springservlet.web.frontcontroller.v3;

import com.example.springservlet.web.frontcontroller.MyModelAndView;

import java.util.Map;

public interface ControllerV3 {

    MyModelAndView process(Map<String, String> paramMap);

}

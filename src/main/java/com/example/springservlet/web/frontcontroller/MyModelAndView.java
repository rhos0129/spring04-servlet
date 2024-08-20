package com.example.springservlet.web.frontcontroller;

import java.util.HashMap;
import java.util.Map;

public class MyModelAndView {

    private String viewName;
    private Map<String, Object> model = new HashMap<>();

    public MyModelAndView(String viewName) {
        this.viewName = viewName;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public void setModel(Map<String, Object> model) {
        this.model = model;
    }

}

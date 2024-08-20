package com.example.springservlet.web.frontcontroller.v3.controller;

import com.example.springservlet.domain.member.Member;
import com.example.springservlet.domain.member.MemberRepository;
import com.example.springservlet.web.frontcontroller.MyModelAndView;
import com.example.springservlet.web.frontcontroller.v3.ControllerV3;

import java.util.List;
import java.util.Map;

public class MemberListControllerV3 implements ControllerV3 {

    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public MyModelAndView process(Map<String, String> paramMap) {
        List<Member> members = memberRepository.findAll();

        MyModelAndView mv = new MyModelAndView("members");
        mv.getModel().put("members", members);

        return mv;
    }

}

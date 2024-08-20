package com.example.springservlet.web.frontcontroller.v3.controller;

import com.example.springservlet.domain.member.Member;
import com.example.springservlet.domain.member.MemberRepository;
import com.example.springservlet.web.frontcontroller.MyModelAndView;
import com.example.springservlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public MyModelAndView process(Map<String, String> paramMap) {
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        MyModelAndView mv = new MyModelAndView("save-result");
        mv.getModel().put("member", member);
        return mv;
    }

}

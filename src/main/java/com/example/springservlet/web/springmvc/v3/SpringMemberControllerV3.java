package com.example.springservlet.web.springmvc.v3;

import com.example.springservlet.domain.member.Member;
import com.example.springservlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    // @RequestMapping 대신 HTTP Method도 함께 구분할 수 있는 @GetMapping 사용
    @GetMapping("/new-form")
    public String newForm() {
        return "new-form"; // ModelAndView 대신 ViewName을 직접 반환
    }

    // model을 파라미터로 받아 데이터 전달
    // @RequestParam로 HTTP 요청의 쿼리 파라미터를 받아 조회 가능
    @PostMapping("/save")
    public String save(
            @RequestParam("username") String username,
            @RequestParam("age") int age,
            Model model) {

        Member member = new Member(username, age);
        memberRepository.save(member);

        model.addAttribute("member", member);
        return "save-result";
    }

    @GetMapping
    public String members(Model model) {

        List<Member> members = memberRepository.findAll();

        model.addAttribute("members", members);
        return "members";
    }

}

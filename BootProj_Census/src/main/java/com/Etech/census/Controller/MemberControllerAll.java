package com.Etech.census.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Etech.census.Respository.MemberRepository;

@Controller
@RequestMapping("/member")
public class MemberControllerAll {

    private final MemberRepository memberRepository;

    public MemberControllerAll(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @GetMapping("/all")
    public String viewAllMembers(Model model) {
        model.addAttribute("members", memberRepository.findAll());
        return "member_list";
    }
}
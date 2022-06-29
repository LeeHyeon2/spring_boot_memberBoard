package com.its.memberboard.controller;

import com.its.memberboard.dto.MemberDTO;
import com.its.memberboard.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
    @GetMapping("/save")
    public String saveForm(){
        return "/memberPages/save";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute MemberDTO memberDTO) throws IOException {
        memberService.save(memberDTO);
        return "redirect:/";
    }
    @PostMapping("/dup-check")
    public @ResponseBody String dupCheck(@RequestParam("memberEmail") String email){
        return memberService.dupCheck(email);
    }

    @GetMapping("/login")
    public String loginForm(){
        return "/memberPages/login";
    }

    @PostMapping("/login")
    public @ResponseBody String login(@RequestParam("memberEmail") String memberEmail,
                                           @RequestParam("memberPassword") String memberPassword,HttpSession session){
        MemberDTO memberDTO = memberService.loginCheck(memberEmail, memberPassword);
        if (memberDTO != null){
            session.setAttribute("loginId",memberDTO.getId());
            session.setAttribute("loginMemberEmail",memberDTO.getMemberEmail());
            return "ok";
        }else {
            return "no";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "index";
    }



















}

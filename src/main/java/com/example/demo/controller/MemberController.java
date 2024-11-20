package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.domain.Article;
import com.example.demo.model.domain.Board;
import com.example.demo.model.domain.Member;
import com.example.demo.model.service.AddArticleRequest;
import com.example.demo.model.service.AddMemberRequest;
import com.example.demo.model.service.BlogService;
import com.example.demo.model.service.MemberService;

@Controller // 컨트롤러 어노테이션 명시
public class MemberController {
    @Autowired // 객체 주입 자동화, 생성자 1개면 생략 가능
    public MemberService memberService;

    @GetMapping("/join_new") // 회원 가입 페이지 연결
    public String join_new() {
    return "join_new"; // .HTML 연결
    }

    @PostMapping("/api/members") // 회원 가입 저장
    public String addmembers(@ModelAttribute AddMemberRequest request) {
        memberService.saveMember(request);
        return "join_end"; // .HTML 연결
    }

    @GetMapping("/member_login") // 로그인 페이지 연결
    public String member_login() {
    return "login"; // .HTML 연결
    }

    @PostMapping("/api/login_check") // 로그인(아이디, 패스워드) 체크
    public String checkMembers(@ModelAttribute AddMemberRequest request, Model model) {
    try {
        Member member = memberService.loginCheck(request.getEmail(), request.getPassword()); // 패스워드 반환
        model.addAttribute("member", member); // 로그인 성공 시 회원 정보 전달
        return "redirect:/board_list"; // 로그인 성공 후 이동할 페이지
    } catch (IllegalArgumentException e) {
        model.addAttribute("error", e.getMessage()); // 에러 메시지 전달
        return "login"; // 로그인 실패 시 로그인 페이지로 리다이렉트
    }
    }
}
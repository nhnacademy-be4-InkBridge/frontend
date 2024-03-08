package com.nhnacademy.inkbridge.front.controller;

import com.nhnacademy.inkbridge.front.dto.member.request.MemberSignupRequestDto;
import com.nhnacademy.inkbridge.front.service.MemberService;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.BorderUIResource;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * class: MemberController.
 *
 * @author devminseo
 * @version 2/27/24
 */
@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;


    /**
     * 로그아웃 시켜주는 메서드.
     *
     * @return 로그아웃후 메인페이지로 이동
     */
    @GetMapping("/logout")
    public String logout(HttpServletResponse response) {

        memberService.logout(response);

        return "redirect:/";
    }

    /**
     * 회원가입 페이지 보여주는 메서드.
     *
     * @return 회원가입 페이지
     */
    @GetMapping("/signup")
    public String signupPage() {
        return "member/signup";
    }


    @PostMapping("/signup")
    public String signupAfter(@Valid MemberSignupRequestDto memberSignupRequestDto, Model model) {
        log.info("signup controller start");
        memberService.signup(memberSignupRequestDto);

        return "redirect:/";
    }
}

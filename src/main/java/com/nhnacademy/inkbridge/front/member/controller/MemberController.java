package com.nhnacademy.inkbridge.front.member.controller;

import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * class: MemberController.
 *
 * @author devminseo
 * @version 2/27/24
 */
@Controller
@RequiredArgsConstructor
public class MemberController {

    @GetMapping("/login")
    public String loginPage() {
        return "member/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletResponse response) {
        // todo: memberService logout
        return "redirect:/";
    }

    @GetMapping("/signup")
    public String signupPage() {
        return "/member/signup";
    }
}

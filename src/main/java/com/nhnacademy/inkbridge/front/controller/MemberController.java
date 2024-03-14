package com.nhnacademy.inkbridge.front.controller;

import static com.nhnacademy.inkbridge.front.jwt.utils.JwtEnums.ACCESS_COOKIE;
import static com.nhnacademy.inkbridge.front.jwt.utils.JwtEnums.HEADER_UUID;
import static com.nhnacademy.inkbridge.front.jwt.utils.JwtEnums.LOGIN_UUID;
import static com.nhnacademy.inkbridge.front.jwt.utils.JwtEnums.REFRESH_COOKIE;
import static com.nhnacademy.inkbridge.front.utils.CommonUtils.makeCookieWhenDoLogin;

import com.nhnacademy.inkbridge.front.dto.member.request.MemberLoginRequestDto;
import com.nhnacademy.inkbridge.front.dto.member.request.MemberSignupOAuthRequestDto;
import com.nhnacademy.inkbridge.front.dto.member.request.MemberSignupRequestDto;
import com.nhnacademy.inkbridge.front.jwt.utils.JwtCookie;
import com.nhnacademy.inkbridge.front.service.MemberService;
import com.nhnacademy.inkbridge.front.utils.CookieUtils;
import java.io.IOException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

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
    private final RedisTemplate<String, Object> redisTemplate;


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

    @GetMapping("/signup/oauth")
    public String signupOAuthPage() {
        return "member/oauthSignup";
    }

    @PostMapping("/signup/oauth")
    public void signupAfterOAuth(@Valid MemberSignupOAuthRequestDto memberSignupOAuthRequestDto,HttpServletResponse response)
            throws IOException {
        String email = memberSignupOAuthRequestDto.getEmail();
        Cookie uuidCookie = CookieUtils.getCookie(LOGIN_UUID.getName());

        if (Objects.isNull(uuidCookie)) {
            response.sendRedirect("/");
            return;
        }

        memberSignupOAuthRequestDto.setPasswordFromRedis(
                (String) redisTemplate.opsForHash().get(uuidCookie.getValue(), LOGIN_UUID.getName()));
        memberSignupOAuthRequestDto.setEmailWithSocial();

        redisTemplate.opsForHash().delete(uuidCookie.getValue(), LOGIN_UUID.getName());
        CookieUtils.deleteCookie(response, LOGIN_UUID.getName());

        memberService.signupWithOAuth(memberSignupOAuthRequestDto);

        ResponseEntity<Void> result = memberService.doLogin(email,
                memberSignupOAuthRequestDto.getPassword());

        makeCookieWhenDoLogin(response, result.getHeaders().toString());

        response.sendRedirect("/");
    }


    @PostMapping("/signup")
    public String signupAfter(@Valid MemberSignupRequestDto memberSignupRequestDto) {
        log.info("signup controller start");
        memberService.signup(memberSignupRequestDto);

        return "redirect:/";
    }
}

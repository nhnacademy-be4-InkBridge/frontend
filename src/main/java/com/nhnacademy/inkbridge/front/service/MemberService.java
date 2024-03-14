package com.nhnacademy.inkbridge.front.service;

import com.nhnacademy.inkbridge.front.dto.member.request.MemberSignupOAuthRequestDto;
import com.nhnacademy.inkbridge.front.dto.member.request.MemberSignupRequestDto;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

/**
 * class: MemberService.
 *
 * @author devminseo
 * @version 3/4/24
 */
public interface MemberService {
    void signup(MemberSignupRequestDto memberSignupRequestDto);

    void signupWithOAuth(MemberSignupOAuthRequestDto memberSignupOAuthRequestDto);

    void logout(HttpServletResponse response);

    ResponseEntity<Void> doLogin(String email, String password);
}

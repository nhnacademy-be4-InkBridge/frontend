package com.nhnacademy.inkbridge.front.service;

import com.nhnacademy.inkbridge.front.dto.member.request.MemberSignupRequestDto;
import javax.servlet.http.HttpServletResponse;

/**
 * class: MemberService.
 *
 * @author devminseo
 * @version 3/4/24
 */
public interface MemberService {
    void signup(MemberSignupRequestDto memberSignupRequestDto);

    void logout(HttpServletResponse response);
}

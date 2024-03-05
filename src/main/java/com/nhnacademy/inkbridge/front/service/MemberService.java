package com.nhnacademy.inkbridge.front.service;

import com.nhnacademy.inkbridge.front.dto.member.request.MemberSignupRequestDto;
import org.springframework.http.HttpStatus;

/**
 * class: MemberService.
 *
 * @author devminseo
 * @version 3/4/24
 */
public interface MemberService {
    HttpStatus signup(MemberSignupRequestDto memberSignupRequestDto);
}

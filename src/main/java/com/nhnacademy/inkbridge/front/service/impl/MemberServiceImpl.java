package com.nhnacademy.inkbridge.front.service.impl;

import com.nhnacademy.inkbridge.front.adaptor.MemberAdaptor;
import com.nhnacademy.inkbridge.front.dto.member.request.MemberSignupRequestDto;
import com.nhnacademy.inkbridge.front.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * class: MemberServiceImpl.
 *
 * @author devminseo
 * @version 3/4/24
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {
    private final PasswordEncoder passwordEncoder;
    private final MemberAdaptor memberAdaptor;
    @Override
    public HttpStatus signup(MemberSignupRequestDto memberSignupRequestDto) {
        String password = memberSignupRequestDto.getPassword();
        String digestPassword = passwordEncoder.encode(password);
        log.info("signup service start ->");

        memberSignupRequestDto.setEncodePassword(digestPassword);

        ResponseEntity<HttpStatus> exchange =
                memberAdaptor.signup(memberSignupRequestDto);

        return exchange.getBody();

    }
}

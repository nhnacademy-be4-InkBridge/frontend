package com.nhnacademy.inkbridge.front.controller;

import com.nhnacademy.inkbridge.front.dto.member.request.MemberEmailRequestDto;
import com.nhnacademy.inkbridge.front.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * class: MemberRestController.
 *
 * @author devminseo
 * @version 3/15/24
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberRestController {
    private final MemberService memberService;

    @PostMapping("/checkEmail")
    public Boolean isDuplicatedEmail(@RequestBody MemberEmailRequestDto memberEmailRequestDto) {
        return memberService.isDuplicatedEmail(memberEmailRequestDto).getBody();
    }

}

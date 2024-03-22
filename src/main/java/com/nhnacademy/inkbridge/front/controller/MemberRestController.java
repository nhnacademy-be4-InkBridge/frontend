package com.nhnacademy.inkbridge.front.controller;

import com.nhn.dooray.client.DoorayHook;
import com.nhnacademy.inkbridge.front.config.DoorayConfig;
import com.nhnacademy.inkbridge.front.dto.member.request.MemberEmailRequestDto;
import com.nhnacademy.inkbridge.front.dto.member.request.MemberPasswordRequestDto;
import com.nhnacademy.inkbridge.front.service.MemberService;
import java.security.SecureRandom;
import javax.validation.Valid;
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
    private final DoorayConfig doorayConfig;
    private static final SecureRandom secureRandom = new SecureRandom();

    /**
     * 회원 이메일 중복여부 메서드입니다.
     *
     * @param memberEmailRequestDto 중복여부할 이메일
     * @return 중복 여부
     */
    @PostMapping("/checkEmail")
    public Boolean isDuplicatedEmail(@RequestBody MemberEmailRequestDto memberEmailRequestDto) {
        return memberService.isDuplicatedEmail(memberEmailRequestDto).getBody();
    }

    @PostMapping("/updatePassword")
    public Boolean updatePassword(@RequestBody @Valid MemberPasswordRequestDto memberPasswordRequestDto) {
        return memberService.updatePassword(memberPasswordRequestDto);
    }

    /**
     * 두레이 메신저 인증번호 메서드입니다.
     *
     * @return 전송되는 인증 번호
     */
    @PostMapping("/dooraySend")
    public String dooraySender() {
        String certificationNumber = String.valueOf(getRandomNumber());
        doorayConfig.doorayHookSender().send(
                DoorayHook.builder()
                        .botName("InkBridge 인증번호")
                        .text(certificationNumber)
                        .build()
        );
        return certificationNumber;
    }
    private static int getRandomNumber() {
        return 1000 + secureRandom.nextInt(9000);
    }

}

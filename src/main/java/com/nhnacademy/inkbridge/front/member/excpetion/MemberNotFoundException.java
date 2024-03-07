package com.nhnacademy.inkbridge.front.member.excpetion;

/**
 * class: MemberNotFoundException.
 *
 * @author devminseo
 * @version 3/2/24
 */
public class MemberNotFoundException extends RuntimeException {
    public static final String MSG = "회원이 아닙니다.";

    public MemberNotFoundException() {
        super(MSG);
    }
}

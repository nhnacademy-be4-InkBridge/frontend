package com.nhnacademy.inkbridge.front.exception;

/**
 * class: UnAuthorizedException.
 *
 * @author devminseo
 * @version 3/6/24
 */
public class UnAuthorizedException extends RuntimeException {
    private static final String MSG = "로그아웃합니다.";

    public UnAuthorizedException() {
        super(MSG);
    }
}

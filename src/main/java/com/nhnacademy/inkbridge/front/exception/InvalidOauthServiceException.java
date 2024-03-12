package com.nhnacademy.inkbridge.front.exception;

/**
 * class: InvalidOauthServiceException.
 *
 * @author devminseo
 * @version 3/12/24
 */
public class InvalidOauthServiceException extends RuntimeException {
    public static final String MSG = "존재하지 않는 소셜 로그인 서비스 입니다.";

    public InvalidOauthServiceException() {
        super(MSG);

    }
}

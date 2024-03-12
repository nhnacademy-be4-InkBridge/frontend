package com.nhnacademy.inkbridge.front.exception;

/**
 * class: InvalidOauthServiceException.
 *
 * @author devminseo
 * @version 3/12/24
 */
public class InvalidOauthServiceException extends RuntimeException {
    private static final String MSG = "존재하지 않는 서비스입니다.";

    public InvalidOauthServiceException() {
        super(MSG);
    }
}

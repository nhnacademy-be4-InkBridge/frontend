package com.nhnacademy.inkbridge.front.exception;

/**
 * class: OauthException.
 *
 * @author devminseo
 * @version 3/12/24
 */
public class OauthException extends RuntimeException {
    public static final String MSG = "유저 정보를 가져오지 못했습니다.";

    public OauthException() {
        super(MSG);
    }
}

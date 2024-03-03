package com.nhnacademy.inkbridge.front.jwt.filter.utils;


import javax.servlet.http.Cookie;
import lombok.extern.slf4j.Slf4j;

/**
 * class: JwtUtils.
 *
 * @author devminseo
 * @version 2/29/24
 */
@Slf4j
public class JwtCookie {
    private static final Integer EXPIRED_TIME = 1209600;

    private JwtCookie() {
    }

    public static Cookie createJwtCookie(String token, Long expiredTime, JwtEnums type) {
        String tokenCookie = token + "." + expiredTime;

        Cookie cookie = new Cookie(type.getName(), tokenCookie);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(EXPIRED_TIME);
        return cookie;
    }
    public static Cookie createUUIDCookie(String uuid, JwtEnums type) {

        Cookie cookie = new Cookie(type.getName(), uuid);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(EXPIRED_TIME);
        return cookie;
    }
}

package com.nhnacademy.inkbridge.front.utils;


import static com.nhnacademy.inkbridge.front.utils.JwtEnums.ACCESS_COOKIE;
import static com.nhnacademy.inkbridge.front.utils.JwtEnums.REFRESH_COOKIE;

import javax.servlet.http.Cookie;

/**
 * class: JwtUtils.
 *
 * @author devminseo
 * @version 2/29/24
 */
public class JwtCookie {

    private JwtCookie() {
    }

    public static Cookie createAccessCookie(String token,String expiredTime,JwtEnums type) {
        String tokenCookie = token + "-" + expiredTime;

        Cookie cookie = new Cookie(type.getName(), tokenCookie);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(Integer.parseInt(expiredTime));
        return cookie;
    }
}

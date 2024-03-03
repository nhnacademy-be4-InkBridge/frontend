package com.nhnacademy.inkbridge.front.utils;

import java.util.Arrays;
import java.util.Objects;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * class: CookieUtils.
 * 쿠키 객체를 생성하고 저장하고, 저장된 쿠키를 꺼내는 클래스.
 *
 * @author devminseo
 * @version 3/2/24
 */
public class CookieUtils {
    private CookieUtils() {
    }

    /**
     * 쿠키 이름으로 쿠키를 찾는 메서드.
     * @param cookieName 쿠키 이름.
     * @return 쿠키 값.
     */
    public static Cookie getCookie(String cookieName) {
        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        Cookie[] cookies = request.getCookies();

        if (Objects.nonNull(cookies)) {
            return Arrays.stream(cookies)
                    .filter(cookie -> cookie.getName().equals(cookieName))
                    .findFirst()
                    .orElse(null);
        }

        return null;
    }

    public static void deleteCookie(HttpServletResponse response,String name) {
        Cookie cookie = getCookie(name);
        Objects.requireNonNull(cookie).setMaxAge(0);
        response.addCookie(cookie);
    }

}

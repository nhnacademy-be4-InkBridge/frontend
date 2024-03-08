package com.nhnacademy.inkbridge.front.jwt.interceptor;

import static com.nhnacademy.inkbridge.front.jwt.utils.JwtEnums.ACCESS_COOKIE;
import static com.nhnacademy.inkbridge.front.jwt.utils.JwtEnums.HEADER_UUID;
import static com.nhnacademy.inkbridge.front.jwt.utils.JwtEnums.REFRESH_COOKIE;

import com.nhnacademy.inkbridge.front.jwt.utils.JwtEnums;
import com.nhnacademy.inkbridge.front.utils.CookieUtils;
import java.util.Objects;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * class: JwtInterceptor.
 *
 * @author devminseo
 * @version 3/6/24
 */

@Component
@Slf4j
public class JwtInterceptor implements HandlerInterceptor {

    /**
     * adaptor 에서 요청하기전 회원이라면 토큰을 헤더에 넣어줌.
     *
     * @param request  request
     * @param response response
     * @param handler  handler
     * @return 넣는지 여부
     * @throws Exception exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        Cookie access = CookieUtils.getCookie(ACCESS_COOKIE.getName());
        Cookie refresh = CookieUtils.getCookie(REFRESH_COOKIE.getName());
        Cookie uuid = CookieUtils.getCookie(HEADER_UUID.getName());

        if (Objects.isNull(access) || Objects.isNull(refresh) || Objects.isNull(uuid)) {
            return true;
        }

        String value = access.getValue();
        String expired = value.split("\\.")[3];
        int expiredLength = value.length() - (expired.length() + 1);

        String token = access.getValue().substring(0, expiredLength);

        request.setAttribute(HttpHeaders.AUTHORIZATION, JwtEnums.BEARER_PREFIX.getName() + token);

        return true;
    }
}

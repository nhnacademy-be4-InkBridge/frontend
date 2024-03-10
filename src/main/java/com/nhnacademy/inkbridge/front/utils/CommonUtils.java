package com.nhnacademy.inkbridge.front.utils;

import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * class: HeaderUtils.
 *
 * @author devminseo
 * @version 2/29/24
 */
@Slf4j
public class CommonUtils {
    private CommonUtils() {
    }

    /**
     * 공통헤더를 만들어줍니다.
     * 사용자가 로그인해있다면 토큰을 헤더에 담아줍니다.
     *
     * @return 헤더
     */
    public static HttpHeaders createHeader() {
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        String token = (String) request.getAttribute(HttpHeaders.AUTHORIZATION);
        if (Objects.nonNull(token)) {
            headers.add(HttpHeaders.AUTHORIZATION, token);
        }

        return headers;
    }
    public static HttpHeaders createHeader(MediaType mediaType) {
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(mediaType);
        headers.setAccept(List.of(mediaType));

        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        String token = (String) request.getAttribute(HttpHeaders.AUTHORIZATION);
        if (Objects.nonNull(token)) {
            headers.add(HttpHeaders.AUTHORIZATION, token);
        }

        return headers;
    }

    /**
     * memberId를 반환해줌 로그인 안한 유저이면 null 값 전송.
     *
     * @return 멤버 아이디
     */
    public static Long getMemberId() {
        String memberId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if ("anonymousUser".equals(memberId)) {
            return null;
        }
        return Long.parseLong(memberId);
    }
}

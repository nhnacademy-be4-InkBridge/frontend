package com.nhnacademy.inkbridge.front.utils;

import static com.nhnacademy.inkbridge.front.jwt.utils.JwtEnums.ACCESS_COOKIE;
import static com.nhnacademy.inkbridge.front.jwt.utils.JwtEnums.HEADER_UUID;
import static com.nhnacademy.inkbridge.front.jwt.utils.JwtEnums.REFRESH_COOKIE;

import com.nhnacademy.inkbridge.front.jwt.utils.JwtCookie;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletResponse;
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
     * 로그인 로직을 통해 가져온 쿠키 헤더를 가져와 현재 response 에 쿠키를 추가하는 메서드.
     *
     * @param response response
     * @param headers 쿠키 헤더
     */
    public static void makeCookieWhenDoLogin(HttpServletResponse response, String headers) {
        // access-cookie 값 추출을 위한 정규 표현식
        Pattern accessPattern = Pattern.compile("access-cookie=([^\"\\s]+)");

        // refresh-cookie 값 추출을 위한 정규 표현식
        Pattern refreshPattern = Pattern.compile("refresh-cookie=([^\"\\s]+)");

        // header_uuid 값 추출을 위한 정규 표현식
        Pattern uuidPattern = Pattern.compile("header_uuid=([^\"\\s]+)");

        // 각 쿠키의 값을 저장할 변수들
        String access = null;
        String refresh = null;
        String uuid = null;

        // 정규 표현식에 맞게 매칭되는 부분을 찾기 위한 Matcher 객체 생성
        Matcher matcher = accessPattern.matcher(headers);
        if (matcher.find()) {
            access = matcher.group(1);
        }

        matcher = refreshPattern.matcher(headers);
        if (matcher.find()) {
            refresh = matcher.group(1);
        }

        matcher = uuidPattern.matcher(headers);
        if (matcher.find()) {
            uuid = matcher.group(1);
        }

        Cookie accessCookie = JwtCookie.createJwtCookie(access.substring(0,access.length()-1), ACCESS_COOKIE);
        Cookie refreshCookie = JwtCookie.createJwtCookie(refresh.substring(0,refresh.length()-1), REFRESH_COOKIE);
        Cookie uuidCookie = JwtCookie.createJwtCookie(uuid.substring(0, uuid.length() - 1), HEADER_UUID);

        response.addCookie(accessCookie);
        response.addCookie(refreshCookie);
        response.addCookie(uuidCookie);
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

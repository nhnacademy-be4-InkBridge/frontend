package com.nhnacademy.inkbridge.front.service.impl;

import static com.nhnacademy.inkbridge.front.jwt.utils.JwtEnums.ACCESS_COOKIE;
import static com.nhnacademy.inkbridge.front.jwt.utils.JwtEnums.HEADER_UUID;
import static com.nhnacademy.inkbridge.front.jwt.utils.JwtEnums.MEMBER_INFO;
import static com.nhnacademy.inkbridge.front.jwt.utils.JwtEnums.REFRESH_COOKIE;

import com.nhnacademy.inkbridge.front.adaptor.MemberAdaptor;
import com.nhnacademy.inkbridge.front.dto.member.request.MemberSignupRequestDto;
import com.nhnacademy.inkbridge.front.exception.UnAuthorizedException;
import com.nhnacademy.inkbridge.front.service.MemberService;
import com.nhnacademy.inkbridge.front.utils.CookieUtils;
import java.util.Objects;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

/**
 * class: MemberServiceImpl.
 *
 * @author devminseo
 * @version 3/4/24
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {
    private final PasswordEncoder passwordEncoder;
    private final MemberAdaptor memberAdaptor;
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public void signup(MemberSignupRequestDto memberSignupRequestDto) {
        String password = memberSignupRequestDto.getPassword();
        String digestPassword = passwordEncoder.encode(password);
        log.info("signup service start ->");

        memberSignupRequestDto.setEncodePassword(digestPassword);

        try {
            memberAdaptor.signup(memberSignupRequestDto);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                throw new UnAuthorizedException();
            }
        }
    }

    @Override
    public void logout(HttpServletResponse response) {
        if ("anonymousUser".equals(SecurityContextHolder.getContext().getAuthentication().getPrincipal())) {
            return;
        }

        Cookie accessCookie = CookieUtils.getCookie(ACCESS_COOKIE.getName());
        Cookie refreshCookie = CookieUtils.getCookie(REFRESH_COOKIE.getName());


        if (Objects.nonNull(accessCookie) && Objects.nonNull(refreshCookie)) {
            String accessValue = accessCookie.getValue();
            String refreshValue = refreshCookie.getValue();

            String accessExp = accessValue.split("\\.")[3];
            int accessExpLength = accessValue.length() - (accessExp.length() + 1);
            String refreshExp = refreshValue.split("\\.")[3];
            int refreshExpLength = refreshValue.length() - (refreshExp.length() + 1);

            String access = accessValue.substring(0, accessExpLength);
            String refresh = refreshValue.substring(0, refreshExpLength);

            memberAdaptor.logout(access, refresh);

        }

        String uuid = Objects.requireNonNull(CookieUtils.getCookie(HEADER_UUID.getName())).getValue();
        redisTemplate.opsForHash().delete(uuid, MEMBER_INFO.getName());

        CookieUtils.deleteCookie(response, ACCESS_COOKIE.getName());
        CookieUtils.deleteCookie(response, REFRESH_COOKIE.getName());
        CookieUtils.deleteCookie(response, HEADER_UUID.getName());

        SecurityContextHolder.clearContext();
    }
}
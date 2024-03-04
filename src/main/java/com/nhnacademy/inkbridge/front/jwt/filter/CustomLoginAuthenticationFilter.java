package com.nhnacademy.inkbridge.front.jwt.filter;


import static com.nhnacademy.inkbridge.front.jwt.filter.utils.JwtEnums.ACCESS_COOKIE;
import static com.nhnacademy.inkbridge.front.jwt.filter.utils.JwtEnums.ACCESS_HEADER;
import static com.nhnacademy.inkbridge.front.jwt.filter.utils.JwtEnums.HEADER_ACCESS_EXPIRED_TIME;
import static com.nhnacademy.inkbridge.front.jwt.filter.utils.JwtEnums.HEADER_REFRESH_EXPIRED_TIME;
import static com.nhnacademy.inkbridge.front.jwt.filter.utils.JwtEnums.HEADER_UUID;
import static com.nhnacademy.inkbridge.front.jwt.filter.utils.JwtEnums.REFRESH_COOKIE;
import static com.nhnacademy.inkbridge.front.jwt.filter.utils.JwtEnums.REFRESH_HEADER;

import com.nhnacademy.inkbridge.front.member.adaptor.MemberAdaptor;
import com.nhnacademy.inkbridge.front.member.dto.request.MemberLoginRequestDto;
import com.nhnacademy.inkbridge.front.jwt.filter.utils.JwtCookie;
import com.nhnacademy.inkbridge.front.jwt.filter.utils.JwtEnums;
import java.io.IOException;
import java.util.Objects;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * class: CustomLoginAuthenticationFilter.
 *
 * @author devminseo
 * @version 2/27/24
 */
@RequiredArgsConstructor
@Slf4j
public class CustomLoginAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final MemberAdaptor memberAdaptor;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        String email = obtainUsername(request);
        String password = obtainPassword(request);

        MemberLoginRequestDto requestDto = new MemberLoginRequestDto(email, password);

        ResponseEntity<Void> tokenResponse = memberAdaptor.login(requestDto);

        String accessToken = getToken(tokenResponse, ACCESS_HEADER);
        Long accessExpiredTime = Long.parseLong(getExpiredTime(tokenResponse, HEADER_ACCESS_EXPIRED_TIME));
        String refreshToken = getToken(tokenResponse, REFRESH_HEADER);
        Long refreshExpiredTime = Long.parseLong(getExpiredTime(tokenResponse, HEADER_REFRESH_EXPIRED_TIME));
        String uuid = getUUID(tokenResponse, HEADER_UUID);

        Cookie accessCookie = JwtCookie.createJwtCookie(accessToken, accessExpiredTime, ACCESS_COOKIE);
        Cookie refreshCookie = JwtCookie.createJwtCookie(refreshToken, refreshExpiredTime, REFRESH_COOKIE);
        Cookie uuidCookie = JwtCookie.createUUIDCookie(uuid, HEADER_UUID);

        response.addCookie(accessCookie);
        response.addCookie(refreshCookie);
        response.addCookie(uuidCookie);

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(accessToken, password);
        return getAuthenticationManager().authenticate(token);

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        response.sendRedirect("/");
    }

    private static String getToken(ResponseEntity<Void> response, JwtEnums type) {
        return Objects.requireNonNull(response.getHeaders().get(type.getName())).get(0).substring(7);
    }

    private static String getExpiredTime(ResponseEntity<Void> response, JwtEnums type) {
        return Objects.requireNonNull(response.getHeaders().get(type.getName())).get(0);
    }

    private static String getUUID(ResponseEntity<Void> response,JwtEnums type) {
        return Objects.requireNonNull(response.getHeaders().get(type.getName())).get(0);
    }
}

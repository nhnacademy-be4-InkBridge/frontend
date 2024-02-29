package com.nhnacademy.inkbridge.front.jwt.filter;


import static com.nhnacademy.inkbridge.front.utils.JwtEnums.ACCESS_COOKIE;
import static com.nhnacademy.inkbridge.front.utils.JwtEnums.ACCESS_HEADER;
import static com.nhnacademy.inkbridge.front.utils.JwtEnums.HEADER_ACCESS_EXPIRED_TIME;
import static com.nhnacademy.inkbridge.front.utils.JwtEnums.HEADER_REFRESH_EXPIRED_TIME;
import static com.nhnacademy.inkbridge.front.utils.JwtEnums.REFRESH_COOKIE;
import static com.nhnacademy.inkbridge.front.utils.JwtEnums.REFRESH_HEADER;

import com.nhnacademy.inkbridge.front.member.adaptor.MemberAdaptor;
import com.nhnacademy.inkbridge.front.member.dto.request.MemberLoginRequestDto;
import com.nhnacademy.inkbridge.front.utils.JwtCookie;
import com.nhnacademy.inkbridge.front.utils.JwtEnums;
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

        log.info("Attempt authentication email -> {}", email);
        log.info("Attempt authentication password -> {}", password);

        MemberLoginRequestDto requestDto = new MemberLoginRequestDto(email, password);

        ResponseEntity<Void> tokenResponse = memberAdaptor.login(requestDto);

        String accessToken = getToken(tokenResponse, ACCESS_HEADER);
        String accessExpiredTime = getExpiredTime(tokenResponse, HEADER_ACCESS_EXPIRED_TIME);
        String refreshToken = getToken(tokenResponse, REFRESH_HEADER);
        String refreshExpiredTime = getExpiredTime(tokenResponse, HEADER_REFRESH_EXPIRED_TIME);

        Cookie accessCookie = JwtCookie.createAccessCookie(accessToken, accessExpiredTime, ACCESS_COOKIE);
        Cookie refreshCookie = JwtCookie.createAccessCookie(refreshToken, refreshExpiredTime, REFRESH_COOKIE);

        response.addCookie(accessCookie);
        response.addCookie(refreshCookie);

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, password);
        return getAuthenticationManager().authenticate(token);

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        response.sendRedirect("/");
    }

    private static String getToken(ResponseEntity<Void> response, JwtEnums type) {
        return Objects.requireNonNull(response.getHeaders().get(type.getName())).get(0).substring(7);
    }

    private static String getExpiredTime(ResponseEntity<Void> response, JwtEnums type) {
        return Objects.requireNonNull(response.getHeaders().get(type.getName())).get(0);
    }
}

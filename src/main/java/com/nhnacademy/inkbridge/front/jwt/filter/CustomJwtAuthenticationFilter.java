package com.nhnacademy.inkbridge.front.jwt.filter;

import static com.nhnacademy.inkbridge.front.jwt.filter.utils.JwtEnums.ACCESS_COOKIE;
import static com.nhnacademy.inkbridge.front.jwt.filter.utils.JwtEnums.ACCESS_HEADER;
import static com.nhnacademy.inkbridge.front.jwt.filter.utils.JwtEnums.HEADER_ACCESS_EXPIRED_TIME;
import static com.nhnacademy.inkbridge.front.jwt.filter.utils.JwtEnums.HEADER_UUID;
import static com.nhnacademy.inkbridge.front.jwt.filter.utils.JwtEnums.REFRESH_COOKIE;

import com.nhnacademy.inkbridge.front.jwt.filter.utils.JwtCookie;
import com.nhnacademy.inkbridge.front.jwt.service.CustomUserDetailService;
import com.nhnacademy.inkbridge.front.member.adaptor.MemberAdaptor;
import com.nhnacademy.inkbridge.front.utils.CookieUtils;
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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * class: CustomJwtAuthenticationFilter.
 *
 * @author devminseo
 * @version 2/27/24
 */
@Slf4j
@RequiredArgsConstructor
public class CustomJwtAuthenticationFilter extends OncePerRequestFilter {
    private final MemberAdaptor memberAdaptor;
    private final CustomUserDetailService userDetailsService;

    /**
     * 모든 요청마다 사용자인지 아닌지 체크.
     *
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            // 알맞지 않은 경로
            if (handleInvalidRequest(request, response, filterChain)) {
                return;
            }

            // 토큰이 쿠키에 존재 유무
            Cookie accessCookie = CookieUtils.getCookie(ACCESS_COOKIE.getName());
            Cookie refreshCookie = CookieUtils.getCookie(REFRESH_COOKIE.getName());
            Cookie uuidCookie = CookieUtils.getCookie(HEADER_UUID.getName());
            if (handleNoneExistCookie(request, response, filterChain, accessCookie, refreshCookie, uuidCookie)) {
                return;
            }

            if (isExpireTime(Objects.requireNonNull(refreshCookie).getValue())) {
                // todo: 로그아웃 로직 처리
                filterChain.doFilter(request, response);
                return;
            }

            // 재발급 로직
            String accessValue = Objects.requireNonNull(accessCookie).getValue();
            String accessExp = accessValue.split("\\.")[3];
            if (isExpireTime(Objects.requireNonNull(accessCookie).getValue())) {

            String refreshValue = refreshCookie.getValue();
            String refreshEXP = refreshValue.split("\\.")[3];

                ResponseEntity<Void> reissued =
                        memberAdaptor.reissueToken(
                                accessValue.substring(0, tokenWithoutExpLength(accessValue, accessExp)),
                                refreshValue.substring(0, tokenWithoutExpLength(refreshValue, refreshEXP)));
                String newAccessToken =
                        Objects.requireNonNull(reissued.getHeaders().get(ACCESS_HEADER.getName())).get(0).substring(7);
                Long accessExpiredTime =
                        Long.parseLong(
                                Objects.requireNonNull(reissued.getHeaders().get(HEADER_ACCESS_EXPIRED_TIME.getName()))
                                        .get(0));
                if (Objects.isNull(newAccessToken)) {
                    // todo: 로그아웃 처리
                    filterChain.doFilter(request, response);
                    return;
                }
                // 기존 쿠키 삭제
                CookieUtils.deleteCookie(response, ACCESS_COOKIE.getName());
                // 새로 추가
                Cookie newCookie = JwtCookie.createJwtCookie(newAccessToken, accessExpiredTime, ACCESS_COOKIE);
                response.addCookie(newCookie);

                accessValue = newCookie.getValue();
                accessExp = accessExpiredTime.toString();
            }

            if (SecurityContextHolder.getContext().getAuthentication() == null) {

                UserDetails userDetails =
                        userDetailsService.loadUserByUsername(
                                accessValue.substring(0, tokenWithoutExpLength(accessValue, accessExp)));
                if (userDetails != null) {
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails.getUsername(), "",
                                    userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }

            filterChain.doFilter(request, response);
        } catch (Exception e) {
            log.error("jwtFilter error");
        } finally {
            SecurityContextHolder.clearContext();
        }
    }

    private static int tokenWithoutExpLength(String value,String exp) {
        return value.length() - (exp.length() + 1);
    }

    private static boolean isExpireTime(String token) {
        long expire = Long.parseLong(token.split("\\.")[3]);

        return expire < System.currentTimeMillis();
    }

    /**
     * 쿠키들의 존재유무
     *
     * @param request       request
     * @param response      response
     * @param filterChain   filterChain
     * @param accessCookie  accessCookie
     * @param refreshCookie refreshCookie
     * @param uuidCookie    uuidCookie
     * @return 존재유무
     * @throws ServletException exception
     * @throws IOException      exception
     */
    private static boolean handleNoneExistCookie(HttpServletRequest request, HttpServletResponse response,
                                                 FilterChain filterChain, Cookie accessCookie, Cookie refreshCookie,
                                                 Cookie uuidCookie)
            throws ServletException, IOException {
        if (Objects.isNull(accessCookie) || Objects.isNull(refreshCookie) || Objects.isNull(uuidCookie)) {
            filterChain.doFilter(request, response);
            return true;
        }
        return false;
    }

    /**
     * 알맞지 않은 요청
     *
     * @param request     request
     * @param response    response
     * @param filterChain filterChain
     * @return 옳은 요청인지 아닌지
     */
    private static boolean handleInvalidRequest(HttpServletRequest request, HttpServletResponse response,
                                                FilterChain filterChain) throws ServletException, IOException {
        if (request.getRequestURI().contains("/static/**")) {
            filterChain.doFilter(request, response);
            return true;
        }
        return false;
    }
}

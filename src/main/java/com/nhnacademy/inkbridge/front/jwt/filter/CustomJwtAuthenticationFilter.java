package com.nhnacademy.inkbridge.front.jwt.filter;

import static com.nhnacademy.inkbridge.front.jwt.utils.JwtEnums.ACCESS_COOKIE;
import static com.nhnacademy.inkbridge.front.jwt.utils.JwtEnums.ACCESS_HEADER;
import static com.nhnacademy.inkbridge.front.jwt.utils.JwtEnums.HEADER_ACCESS_EXPIRED_TIME;
import static com.nhnacademy.inkbridge.front.jwt.utils.JwtEnums.HEADER_UUID;
import static com.nhnacademy.inkbridge.front.jwt.utils.JwtEnums.MEMBER_INFO;
import static com.nhnacademy.inkbridge.front.jwt.utils.JwtEnums.REFRESH_COOKIE;

import com.nhnacademy.inkbridge.front.adaptor.MemberAdaptor;
import com.nhnacademy.inkbridge.front.dto.member.response.MemberInfoResponseDto;
import com.nhnacademy.inkbridge.front.jwt.utils.JwtCookie;
import com.nhnacademy.inkbridge.front.utils.CookieUtils;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final RedisTemplate<String, Object> redisTemplate;

    /**
     * 모든 요청마다 사용자인지 아닌지 체크.
     *
     * @param request     request
     * @param response    response
     * @param filterChain filterChain
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        log.debug("jwt filter start ->");
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

            if (isExpireTime(Objects.requireNonNull(refreshCookie).getValue(),response)){
                log.error("jwt filter refresh token 만료");
                goLogout(response);
                filterChain.doFilter(request, response);
                return;
            }
            // 재발급 로직
            if (isExpireTime(Objects.requireNonNull(accessCookie).getValue(),response)){
                log.debug("jwt filter access token 재발급 시작 ->");

                String accessValue = Objects.requireNonNull(accessCookie).getValue();
                String accessExp = accessValue.split("\\.")[3];

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
                    goLogout(response);
                    filterChain.doFilter(request, response);
                    return;
                }
                // 기존 쿠키 삭제
                CookieUtils.deleteCookie(response, ACCESS_COOKIE.getName());
                // 새로 추가
                Cookie newCookie = JwtCookie.createJwtCookie(newAccessToken, accessExpiredTime, ACCESS_COOKIE);
                response.addCookie(newCookie);
                log.debug("jwt filter access token 재발급 종료 ->");
            }

            if (!(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken) &&
                    uuidCookie != null) {
                MemberInfoResponseDto responseDto =
                        (MemberInfoResponseDto) redisTemplate.opsForHash()
                                .get(uuidCookie.getValue(), MEMBER_INFO.getName());

                if (Objects.isNull(responseDto)) {
                    filterChain.doFilter(request, response);
                    return;
                }

                List<SimpleGrantedAuthority> authorities =
                        responseDto.getRoles().stream().map(SimpleGrantedAuthority::new).collect(
                                Collectors.toList());

                UsernamePasswordAuthenticationToken token =
                        new UsernamePasswordAuthenticationToken(responseDto.getMemberId().toString(), "", authorities);

                SecurityContextHolder.getContext().setAuthentication(token);
                log.debug("jwt filter 컨텍스트 홀드 저장 완료 -> {}", SecurityContextHolder.getContext().getAuthentication());
            }

            log.debug("jwt filter end ->");
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            log.error("jwtFilter error {}", e.getMessage());
        } finally {
            SecurityContextHolder.clearContext();
        }
    }

    private static void goLogout(HttpServletResponse response) throws IOException {
        response.sendRedirect("/logout");
    }

    private static int tokenWithoutExpLength(String value, String exp) {
        return value.length() - (exp.length() + 1);
    }

    private static boolean isExpireTime(String token, HttpServletResponse response) throws IOException {
        long expire = System.currentTimeMillis();
        try {
            expire = Long.parseLong(token.split("\\.")[3]);
        } catch (Exception e) {
            goLogout(response);
        }

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
        if (request.getRequestURI().contains("/static") || request.getRequestURI().contains("/css") ||
                request.getRequestURI().contains("/error") || request.getRequestURI().contains("/js") ||
                request.getRequestURI().contains("/assets")) {
            filterChain.doFilter(request, response);
            return true;
        }
        return false;
    }
}

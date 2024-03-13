package com.nhnacademy.inkbridge.front.oauth.handler;

import static com.nhnacademy.inkbridge.front.jwt.utils.JwtEnums.ACCESS_COOKIE;
import static com.nhnacademy.inkbridge.front.jwt.utils.JwtEnums.HEADER_UUID;
import static com.nhnacademy.inkbridge.front.jwt.utils.JwtEnums.LOGIN_UUID;
import static com.nhnacademy.inkbridge.front.jwt.utils.JwtEnums.REFRESH_COOKIE;
import static com.nhnacademy.inkbridge.front.utils.CommonUtils.makeCookieWhenDoLogin;

import com.nhnacademy.inkbridge.front.jwt.utils.JwtCookie;
import com.nhnacademy.inkbridge.front.oauth.dto.CustomOAuth2User;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * class: CustomOAuthSuccessHandler.
 *
 * @author devminseo
 * @version 3/11/24
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class CustomOAuthSuccessHandler implements AuthenticationSuccessHandler {
    private final RestTemplate restTemplate;
    private final RedisTemplate<String, Object> redisTemplate;
    private static final String URL = "https://inkbridge.store/auth-login";
    private static final long LOGIN_UUID_EXPIRED_TIME = 5 * 60L * 1000; // 5분
    private static final Integer LOGIN_COOKIE_EXPIRED_TIME = 300; // 5분


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        log.info("oauth login success ->");
        //OAuth2User
        CustomOAuth2User customUserDetails = (CustomOAuth2User) authentication.getPrincipal();

        // oauth 첫 로그인시
        if ("null".equals(customUserDetails.getName())) {
            String loginUUID = UUID.randomUUID().toString();
            redisTemplate.opsForHash().put(loginUUID, LOGIN_UUID.getName(), customUserDetails.getPassword());
            redisTemplate.expire(loginUUID, LOGIN_UUID_EXPIRED_TIME, TimeUnit.MILLISECONDS);

            Cookie loginCookie = JwtCookie.createJwtCookie(loginUUID, LOGIN_UUID);
            loginCookie.setMaxAge(LOGIN_COOKIE_EXPIRED_TIME);

            response.addCookie(loginCookie);
            response.sendRedirect("/signup/oauth");
        }else{
            // 처음이 아닌경우 바로 로그인
            // POST 요청에 필요한 데이터
            MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
            formData.add("email", customUserDetails.getName());
            formData.add("password", customUserDetails.getPassword());

            // POST 요청 헤더 설정
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            // POST 요청 생성
            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(formData, headers);

            // POST 요청 보내기
            ResponseEntity<Void> result = restTemplate.exchange(URL, HttpMethod.POST, requestEntity, Void.class);

            makeCookieWhenDoLogin(response, result.getHeaders().toString());

            response.sendRedirect("/");
        }

    }
}

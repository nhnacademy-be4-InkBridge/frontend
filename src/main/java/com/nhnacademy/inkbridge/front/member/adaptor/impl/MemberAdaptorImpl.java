package com.nhnacademy.inkbridge.front.member.adaptor.impl;

import static com.nhnacademy.inkbridge.front.jwt.filter.utils.JwtEnums.ACCESS_HEADER;
import static com.nhnacademy.inkbridge.front.jwt.filter.utils.JwtEnums.BEARER_PREFIX;
import static com.nhnacademy.inkbridge.front.jwt.filter.utils.JwtEnums.REFRESH_HEADER;
import static com.nhnacademy.inkbridge.front.utils.HeaderUtils.createHeader;

import com.nhnacademy.inkbridge.front.member.adaptor.MemberAdaptor;
import com.nhnacademy.inkbridge.front.member.dto.request.MemberLoginRequestDto;
import com.nhnacademy.inkbridge.front.member.dto.response.MemberInfoResponseDto;
import com.nhnacademy.inkbridge.front.property.GatewayProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * class: MemberAdaptorImpl.
 *
 * @author devminseo
 * @version 2/29/24
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class MemberAdaptorImpl implements MemberAdaptor {
    private final RestTemplate restTemplate;
    private final GatewayProperties gatewayProperties;

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<Void> login(MemberLoginRequestDto memberLoginRequestDto) {
        return restTemplate.exchange(
                gatewayProperties.getUrl() + "/auth/login",
                HttpMethod.POST,
                new HttpEntity<>(memberLoginRequestDto, createHeader()),
                Void.class
        );
    }

    /**
     * {@inheritDoc}
     *
     * @param accessToken 인가 토큰
     * @return 멤버 정보
     */
    @Override
    public MemberInfoResponseDto getMemberInfoByToken(String accessToken) {
        HttpHeaders header = createHeader();
        header.add(HttpHeaders.AUTHORIZATION, BEARER_PREFIX.getName() + accessToken);

        return restTemplate.exchange(
                gatewayProperties.getUrl() + "/api/auth/info",
                HttpMethod.GET,
                new HttpEntity<>(header),
                MemberInfoResponseDto.class
        ).getBody();
    }

    /**
     * {@inheritDoc}
     *
     * @param access  access 토큰
     * @param refresh refresh 토큰
     * @return 헤더로 토큰 다시 가져옴
     */
    @Override
    public ResponseEntity<Void> reissueToken(String access, String refresh) {
        log.info("access 재발급 -> {}",access);
        log.info("refresh 재발급 -> {}",refresh);
        HttpHeaders header = createHeader();
        header.add(ACCESS_HEADER.getName(), BEARER_PREFIX.getName() + access);
        header.add(REFRESH_HEADER.getName(), BEARER_PREFIX.getName() + refresh);

        return restTemplate.exchange(
                gatewayProperties.getUrl() + "/auth/reissue",
                HttpMethod.POST,
                new HttpEntity<>(header),
                Void.class
        );
    }
}

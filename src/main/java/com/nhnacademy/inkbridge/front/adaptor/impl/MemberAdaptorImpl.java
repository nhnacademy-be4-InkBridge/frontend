package com.nhnacademy.inkbridge.front.adaptor.impl;

import static com.nhnacademy.inkbridge.front.jwt.utils.JwtEnums.ACCESS_HEADER;
import static com.nhnacademy.inkbridge.front.jwt.utils.JwtEnums.BEARER_PREFIX;
import static com.nhnacademy.inkbridge.front.jwt.utils.JwtEnums.REFRESH_HEADER;
import static com.nhnacademy.inkbridge.front.utils.CommonUtils.createHeader;

import com.nhnacademy.inkbridge.front.adaptor.MemberAdaptor;
import com.nhnacademy.inkbridge.front.dto.member.request.MemberLoginRequestDto;
import com.nhnacademy.inkbridge.front.dto.member.request.MemberSignupRequestDto;
import com.nhnacademy.inkbridge.front.dto.member.response.MemberInfoResponseDto;
import com.nhnacademy.inkbridge.front.property.GatewayProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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
     */
    @Override
    public ResponseEntity<Void> reissueToken(String access, String refresh) {
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void signup(MemberSignupRequestDto memberSignupRequestDto) {
        Integer memberId =(Integer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        restTemplate.exchange(
                gatewayProperties.getUrl() + "/api/members/"+memberId,
                HttpMethod.POST,
                new HttpEntity<>(memberSignupRequestDto, createHeader()),
                Void.class
        );
    }

    @Override
    public void logout(String access, String refresh) {
        HttpHeaders header = createHeader();
        header.add(ACCESS_HEADER.getName(),BEARER_PREFIX.getName()+access);
        header.add(REFRESH_HEADER.getName(), BEARER_PREFIX.getName() + refresh);

        restTemplate.exchange(
                gatewayProperties.getUrl() + "/auth/logout",
                HttpMethod.GET,
                new HttpEntity<>(header),
                Void.class
        );
    }
}

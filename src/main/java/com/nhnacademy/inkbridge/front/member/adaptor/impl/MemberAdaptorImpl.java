package com.nhnacademy.inkbridge.front.member.adaptor.impl;

import static com.nhnacademy.inkbridge.front.utils.HeaderUtils.createHeader;

import com.nhnacademy.inkbridge.front.member.adaptor.MemberAdaptor;
import com.nhnacademy.inkbridge.front.member.dto.request.MemberLoginRequestDto;
import com.nhnacademy.inkbridge.front.property.GatewayProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
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
}

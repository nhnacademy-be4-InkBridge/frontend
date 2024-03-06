package com.nhnacademy.inkbridge.front.adaptor.impl;

import com.nhnacademy.inkbridge.front.adaptor.WrappingAdaptor;
import com.nhnacademy.inkbridge.front.dto.wrapping.WrappingReadResponseDto;
import com.nhnacademy.inkbridge.front.property.GatewayProperties;
import com.nhnacademy.inkbridge.front.utils.HeaderUtils;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * class: WrappingServiceImpl.
 *
 * @author jangjaehun
 * @version 2024/03/05
 */
@Component
@RequiredArgsConstructor
public class WrappingAdaptorImpl implements WrappingAdaptor {

    private final RestTemplate restTemplate;

    private final GatewayProperties gatewayProperties;

    @Override
    public List<WrappingReadResponseDto> getWrappingList() {
        HttpHeaders httpHeaders = HeaderUtils.createHeader();

        HttpEntity<Void> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<List<WrappingReadResponseDto>> exchange = restTemplate.exchange(
            gatewayProperties.getUrl() + "/api/wrappings",
            HttpMethod.GET,
            entity,
            new ParameterizedTypeReference<>() {
            });

        return exchange.getBody();
    }
}

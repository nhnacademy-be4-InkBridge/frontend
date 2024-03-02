package com.nhnacademy.inkbridge.front.adaptor.impl;

import com.nhnacademy.inkbridge.front.adaptor.AccumulationRatePolicyAdaptor;
import com.nhnacademy.inkbridge.front.dto.accumulationratepolicy.AccumulationRatePolicyCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.accumulationratepolicy.AccumulationRatePolicyReadResponseDto;
import com.nhnacademy.inkbridge.front.property.GatewayProperties;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * class: AccumulationRatePolicyAdaptorImpl.
 *
 * @author jangjaehun
 * @version 2024/02/25
 */
@Component
@RequiredArgsConstructor
public class AccumulationRatePolicyAdaptorImpl implements AccumulationRatePolicyAdaptor {

    private final RestTemplate restTemplate;

    private final GatewayProperties gatewayProperties;

    /**
     * {@inheritDoc}
     *
     * @return AccumulationRatePolicyReadResponseDto
     */
    @Override
    public AccumulationRatePolicyReadResponseDto getCurrentPolicy() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<Void> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<AccumulationRatePolicyReadResponseDto> exchange = restTemplate.exchange(
            gatewayProperties.getUrl() + "/api/accumulation-rate-policies/current",
            HttpMethod.GET,
            entity,
            new ParameterizedTypeReference<>() {
            });

        return exchange.getBody();
    }

    /**
     * {@inheritDoc}
     *
     * @return List - AccumulationRatePolicyReadResponseDto
     */
    @Override
    public List<AccumulationRatePolicyReadResponseDto> getAccumulationRatePolicies() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<Void> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<List<AccumulationRatePolicyReadResponseDto>> exchange = restTemplate.exchange(
            gatewayProperties.getUrl() + "/api/accumulation-rate-policies",
            HttpMethod.GET,
            entity,
            new ParameterizedTypeReference<>() {
            });

        return exchange.getBody();
    }

    /**
     * {@inheritDoc}
     *
     * @param requestDto AccumulationRatePolicyCreateRequestDto
     */
    @Override
    public void createAccumulationRatePolicy(AccumulationRatePolicyCreateRequestDto requestDto) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<AccumulationRatePolicyCreateRequestDto> entity = new HttpEntity<>(requestDto,
            httpHeaders);
        restTemplate.exchange(
            gatewayProperties.getUrl() + "/api/accumulation-rate-policies",
            HttpMethod.POST,
            entity,
            new ParameterizedTypeReference<Void>() {
            });
    }
}

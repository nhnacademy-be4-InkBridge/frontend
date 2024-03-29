package com.nhnacademy.inkbridge.front.adaptor.impl;

import static com.nhnacademy.inkbridge.front.utils.CommonUtils.createHeader;

import com.nhnacademy.inkbridge.front.adaptor.AccumulationRatePolicyAdaptor;
import com.nhnacademy.inkbridge.front.dto.accumulationratepolicy.AccumulationRatePolicyAdminReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.accumulationratepolicy.AccumulationRatePolicyCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.accumulationratepolicy.AccumulationRatePolicyReadResponseDto;
import com.nhnacademy.inkbridge.front.property.GatewayProperties;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
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
        HttpEntity<Void> entity = new HttpEntity<>(createHeader());
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
     * @return List - AccumulationRatePolicyAdminReadResponseDto
     */
    @Override
    public List<AccumulationRatePolicyAdminReadResponseDto> getAccumulationRatePolicies() {

        HttpEntity<Void> entity = new HttpEntity<>(createHeader());
        ResponseEntity<List<AccumulationRatePolicyAdminReadResponseDto>> exchange = restTemplate.exchange(
            gatewayProperties.getUrl() + "/api/admin/accumulation-rate-policies",
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

        HttpEntity<AccumulationRatePolicyCreateRequestDto> entity = new HttpEntity<>(requestDto,
            createHeader());
        restTemplate.exchange(
            gatewayProperties.getUrl() + "/api/admin/accumulation-rate-policies",
            HttpMethod.POST,
            entity,
            new ParameterizedTypeReference<Void>() {
            });
    }
}

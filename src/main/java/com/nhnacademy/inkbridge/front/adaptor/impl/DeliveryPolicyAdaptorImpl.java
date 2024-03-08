package com.nhnacademy.inkbridge.front.adaptor.impl;

import static com.nhnacademy.inkbridge.front.utils.CommonUtils.createHeader;

import com.nhnacademy.inkbridge.front.adaptor.DeliveryPolicyAdaptor;
import com.nhnacademy.inkbridge.front.dto.deliverypolicy.DeliveryPolicyAdminReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.deliverypolicy.DeliveryPolicyCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.deliverypolicy.DeliveryPolicyReadResponseDto;
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
 * class: DeliveryPolicyAdaptorImpl.
 *
 * @author jangjaehun
 * @version 2024/02/25
 */
@Component
@RequiredArgsConstructor
public class DeliveryPolicyAdaptorImpl implements DeliveryPolicyAdaptor {

    private final RestTemplate restTemplate;

    private final GatewayProperties gatewayProperties;


    /**
     * {@inheritDoc}
     *
     * @return DeliveryPolicyReadResponseDto
     */
    @Override
    public DeliveryPolicyReadResponseDto getCurrentPolicy() {
        HttpEntity<Void> entity = new HttpEntity<>(createHeader());
        ResponseEntity<DeliveryPolicyReadResponseDto> exchange = restTemplate.exchange(
            gatewayProperties.getUrl() + "/api/delivery-policies/current",
            HttpMethod.GET,
            entity,
            new ParameterizedTypeReference<>() {
            });

        return exchange.getBody();
    }

    /**
     * {@inheritDoc}
     *
     * @return List - DeliveryPolicyReadResponseDto
     */
    @Override
    public List<DeliveryPolicyAdminReadResponseDto> getDeliveryPolicies() {
        HttpEntity<Void> entity = new HttpEntity<>(createHeader());
        ResponseEntity<List<DeliveryPolicyAdminReadResponseDto>> exchange = restTemplate.exchange(
            gatewayProperties.getUrl() + "/api/admin/delivery-policies",
            HttpMethod.GET,
            entity,
            new ParameterizedTypeReference<>() {
            });

        return exchange.getBody();
    }

    /**
     * {@inheritDoc}
     *
     * @param requestDto DeliveryPolicyCreateRequestDto
     */
    @Override
    public void createDeliveryPolicy(DeliveryPolicyCreateRequestDto requestDto) {
        HttpEntity<DeliveryPolicyCreateRequestDto> entity = new HttpEntity<>(requestDto,
            createHeader());
        restTemplate.exchange(gatewayProperties.getUrl() + "/api/admin/delivery-policies",
            HttpMethod.POST,
            entity,
            new ParameterizedTypeReference<Void>() {
            });
    }
}

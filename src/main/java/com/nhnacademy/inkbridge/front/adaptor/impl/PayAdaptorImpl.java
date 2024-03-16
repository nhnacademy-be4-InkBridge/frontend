package com.nhnacademy.inkbridge.front.adaptor.impl;

import static com.nhnacademy.inkbridge.front.utils.CommonUtils.createHeader;

import com.nhnacademy.inkbridge.front.adaptor.PayAdaptor;
import com.nhnacademy.inkbridge.front.dto.pay.PayCreateRequestDto;
import com.nhnacademy.inkbridge.front.property.GatewayProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * class: PayAdaptorImpl.
 *
 * @author jangjaehun
 * @version 2024/03/16
 */
@Component
@RequiredArgsConstructor
public class PayAdaptorImpl implements PayAdaptor {

    private final RestTemplate restTemplate;
    private final GatewayProperties gatewayProperties;

    @Override
    public void doPay(PayCreateRequestDto payCreateRequestDto) {

        HttpEntity<PayCreateRequestDto> entity = new HttpEntity<>(payCreateRequestDto, createHeader());

        restTemplate.exchange(gatewayProperties.getUrl() + "/api/pays",
            HttpMethod.POST,
            entity,
            Void.class);
    }
}

package com.nhnacademy.inkbridge.front.adaptor.impl;

import static com.nhnacademy.inkbridge.front.utils.CommonUtils.createHeader;

import com.nhnacademy.inkbridge.front.adaptor.OrderAdaptor;
import com.nhnacademy.inkbridge.front.dto.order.OrderCreateRequestDto;
import com.nhnacademy.inkbridge.front.property.GatewayProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * class: OrderAdaptorImpl.
 *
 * @author jangjaehun
 * @version 2024/03/05
 */
@Component
@RequiredArgsConstructor
public class OrderAdaptorImpl implements OrderAdaptor {

    private final RestTemplate restTemplate;
    private final GatewayProperties gatewayProperties;


    @Override
    public String createOrder(OrderCreateRequestDto requestDto) {
        HttpEntity<OrderCreateRequestDto> entity = new HttpEntity<>(requestDto, createHeader());
        ResponseEntity<String> exchange = restTemplate.exchange(
            gatewayProperties.getUrl() + "/api/orders",
            HttpMethod.POST,
            entity,
            new ParameterizedTypeReference<>() {
            });

        return exchange.getBody();
    }
}

package com.nhnacademy.inkbridge.front.adaptor.impl;

import static com.nhnacademy.inkbridge.front.utils.CommonUtils.createHeader;

import com.nhnacademy.inkbridge.front.adaptor.OrderAdaptor;
import com.nhnacademy.inkbridge.front.dto.order.OrderCreateResponseDto;
import com.nhnacademy.inkbridge.front.dto.order.OrderPaymentInfoReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.order.OrderCreateRequestDto;
import com.nhnacademy.inkbridge.front.property.GatewayProperties;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

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

    /**
     * {@inheritDoc}
     *
     * @param requestDto 주문 정보
     * @return 주문 번호
     */
    @Override
    public OrderCreateResponseDto createOrder(OrderCreateRequestDto requestDto) {
        HttpEntity<OrderCreateRequestDto> entity = new HttpEntity<>(requestDto, createHeader());
        ResponseEntity<OrderCreateResponseDto> exchange = restTemplate.exchange(
            gatewayProperties.getUrl() + "/api/orders",
            HttpMethod.POST,
            entity,
            new ParameterizedTypeReference<>() {
            });

        return exchange.getBody();
    }

    /**
     * {@inheritDoc}
     *
     * @param orderCode 주문 번호
     * @return 주문 결제 정보
     */
    @Override
    public OrderPaymentInfoReadResponseDto getOrderPaymentInfo(String orderCode) {
        HttpEntity<Void> entity = new HttpEntity<>(createHeader());

        URI uri = UriComponentsBuilder
            .fromUriString(gatewayProperties.getUrl())
            .encode()
            .path("/api/orders/{orderCode}/order-pays")
            .build()
            .expand(orderCode)
            .toUri();

        ResponseEntity<OrderPaymentInfoReadResponseDto> exchange = restTemplate.exchange(
            uri,
            HttpMethod.GET,
            entity,
            new ParameterizedTypeReference<>() {
            });

        return exchange.getBody();
    }
}

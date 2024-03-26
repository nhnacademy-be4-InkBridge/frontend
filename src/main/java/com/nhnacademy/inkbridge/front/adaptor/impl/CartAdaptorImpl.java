package com.nhnacademy.inkbridge.front.adaptor.impl;

import com.nhnacademy.inkbridge.front.adaptor.CartAdaptor;
import com.nhnacademy.inkbridge.front.dto.cart.CartCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.cart.CartReadResponseDto;
import com.nhnacademy.inkbridge.front.property.GatewayProperties;
import com.nhnacademy.inkbridge.front.utils.CommonUtils;
import java.net.URI;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * class: CartAdaptorImpl.
 *
 * @author minm063
 * @version 2024/03/09
 */
@Slf4j
@Component
public class CartAdaptorImpl implements CartAdaptor {

    private final RestTemplate restTemplate;
    private final GatewayProperties gatewayProperties;

    public CartAdaptorImpl(RestTemplate restTemplate, GatewayProperties gatewayProperties) {
        this.restTemplate = restTemplate;
        this.gatewayProperties = gatewayProperties;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveCart(List<CartCreateRequestDto> cartList) {
        HttpHeaders httpHeaders = CommonUtils.createHeader();
        URI uri = UriComponentsBuilder
            .fromUriString(gatewayProperties.getUrl())
            .path("/api/carts")
            .encode()
            .build().toUri();

        restTemplate.postForEntity(uri,
            new HttpEntity<>(cartList, httpHeaders), HttpStatus.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CartReadResponseDto> getCart(Long memberId) {
        HttpHeaders httpHeaders = CommonUtils.createHeader();
        URI uri = UriComponentsBuilder
            .fromUriString(gatewayProperties.getUrl())
            .path("/api/carts/{memberId}")
            .encode()
            .buildAndExpand(memberId).toUri();

        ResponseEntity<List<CartReadResponseDto>> exchange = restTemplate.exchange(
            uri,
            HttpMethod.GET,
            new HttpEntity<>(httpHeaders),
            new ParameterizedTypeReference<>() {
            });
        return exchange.getBody();
    }
}

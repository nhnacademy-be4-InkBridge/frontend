package com.nhnacademy.inkbridge.front.adaptor.impl;

import com.nhnacademy.inkbridge.front.adaptor.CartAdaptor;
import com.nhnacademy.inkbridge.front.dto.cart.CartBookReadResponseDto;
import com.nhnacademy.inkbridge.front.property.GatewayProperties;
import com.nhnacademy.inkbridge.front.utils.CommonUtils;
import java.net.URI;
import java.util.List;
import java.util.Set;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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
@Component
public class CartAdaptorImpl implements CartAdaptor {

    private final RestTemplate restTemplate;
    private final GatewayProperties gatewayProperties;

    public CartAdaptorImpl(RestTemplate restTemplate, GatewayProperties gatewayProperties) {
        this.restTemplate = restTemplate;
        this.gatewayProperties = gatewayProperties;
    }

    @Override
    public List<CartBookReadResponseDto> getBook(Set<String> bookIdList) {
        HttpHeaders httpHeaders = CommonUtils.createHeader();

        URI uri = UriComponentsBuilder
            .fromUriString(gatewayProperties.getUrl())
            .path("/api/carts")
            .queryParam("bookIdList", bookIdList)
            .encode()
            .build()
            .toUri();

        ResponseEntity<List<CartBookReadResponseDto>> exchange = restTemplate.exchange(uri,
            HttpMethod.GET,
            new HttpEntity<>(httpHeaders),
            new ParameterizedTypeReference<>() {
            });

        return exchange.getBody();
    }
}

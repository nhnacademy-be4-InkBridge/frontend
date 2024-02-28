package com.nhnacademy.inkbridge.front.adaptor.impl;

import com.nhnacademy.inkbridge.front.adaptor.BookStatusAdaptor;
import com.nhnacademy.inkbridge.front.dto.bookstatus.BookStatusReadResponseDto;
import com.nhnacademy.inkbridge.front.property.GatewayProperties;
import java.net.URI;
import java.util.List;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * class: BookStatusAdaptorImpl.
 *
 * @author minm063
 * @version 2024/02/27
 */
@Component
public class BookStatusAdaptorImpl implements BookStatusAdaptor {

    private final RestTemplate restTemplate;
    private final GatewayProperties gatewayProperties;

    public BookStatusAdaptorImpl(RestTemplate restTemplate, GatewayProperties gatewayProperties) {
        this.restTemplate = restTemplate;
        this.gatewayProperties = gatewayProperties;
    }

    @Override
    public List<BookStatusReadResponseDto> getBookStatuses() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<HttpHeaders> httpEntity = new HttpEntity<>(httpHeaders);

        URI uri = UriComponentsBuilder
            .fromUriString(gatewayProperties.getUrl())
            .path("/api/admin/statuses")
            .encode()
            .build()
            .toUri();

        ResponseEntity<List<BookStatusReadResponseDto>> exchange = restTemplate.exchange(
            uri,
            HttpMethod.GET,
            httpEntity,
            new ParameterizedTypeReference<>() {
            }
        );
        return exchange.getBody();
    }
}

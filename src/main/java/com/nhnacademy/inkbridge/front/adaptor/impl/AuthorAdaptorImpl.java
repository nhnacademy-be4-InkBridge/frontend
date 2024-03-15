package com.nhnacademy.inkbridge.front.adaptor.impl;

import com.nhnacademy.inkbridge.front.adaptor.AuthorAdaptor;
import com.nhnacademy.inkbridge.front.dto.author.AuthorReadResponseDto;
import com.nhnacademy.inkbridge.front.property.GatewayProperties;
import com.nhnacademy.inkbridge.front.utils.CommonUtils;
import java.net.URI;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * class: AuthorAdaptorImpl.
 *
 * @author minm063
 * @version 2024/03/14
 */
@Component
public class AuthorAdaptorImpl implements AuthorAdaptor {

    private final GatewayProperties gatewayProperties;
    private final RestTemplate restTemplate;
    private static final String PATH = "/api/authors";

    public AuthorAdaptorImpl(GatewayProperties gatewayProperties, RestTemplate restTemplate) {
        this.gatewayProperties = gatewayProperties;
        this.restTemplate = restTemplate;
    }

    @Override
    public AuthorReadResponseDto getAuthor(Long authorId, Long page) {
        URI uri = UriComponentsBuilder
            .fromUriString(gatewayProperties.getUrl())
            .path(PATH)
            .path("/{authorId}")
            .queryParam("page", page)
            .encode()
            .build()
            .expand(authorId)
            .toUri();

        ResponseEntity<AuthorReadResponseDto> exchange = restTemplate.exchange(uri, HttpMethod.GET,
            new HttpEntity<>(CommonUtils.createHeader()),
            new ParameterizedTypeReference<>() {
            });
        if (exchange.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }

        return exchange.getBody();
    }
}

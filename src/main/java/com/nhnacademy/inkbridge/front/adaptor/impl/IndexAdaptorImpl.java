package com.nhnacademy.inkbridge.front.adaptor.impl;

import com.nhnacademy.inkbridge.front.adaptor.IndexAdaptor;
import com.nhnacademy.inkbridge.front.dto.PageRequestDto;
import com.nhnacademy.inkbridge.front.dto.book.BookReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.book.BooksReadResponseDto;
import com.nhnacademy.inkbridge.front.property.GatewayProperties;
import com.nhnacademy.inkbridge.front.utils.CommonUtils;
import java.net.URI;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * class: IndexAdaptorImpl.
 *
 * @author minm063
 * @version 2024/02/26
 */
@Component
public class IndexAdaptorImpl implements IndexAdaptor {

    private final RestTemplate restTemplate;
    private final GatewayProperties gatewayProperties;
    private static final String DEFAULT_PATH = "/api/books";

    public IndexAdaptorImpl(RestTemplate restTemplate, GatewayProperties gatewayProperties) {
        this.restTemplate = restTemplate;
        this.gatewayProperties = gatewayProperties;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PageRequestDto<BooksReadResponseDto> getBooks(Long page) {
        URI uri = UriComponentsBuilder
            .fromUriString(gatewayProperties.getUrl())
            .path(DEFAULT_PATH)
            .queryParam("page", "{page}")
            .encode()
            .build()
            .expand(page)
            .toUri();

        HttpHeaders httpHeaders = CommonUtils.createHeader();
        ResponseEntity<PageRequestDto<BooksReadResponseDto>> exchange = restTemplate.exchange(
            uri,
            HttpMethod.GET,
            new HttpEntity<>(httpHeaders),
            new ParameterizedTypeReference<>() {
            }
        );

        return exchange.getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BookReadResponseDto getBook(Long bookId) {
        URI uri = UriComponentsBuilder
            .fromUriString(gatewayProperties.getUrl())
            .path(DEFAULT_PATH)
            .path("/{bookId}")
            .encode()
            .build()
            .expand(bookId)
            .toUri();

        HttpHeaders httpHeaders = CommonUtils.createHeader();

        ResponseEntity<BookReadResponseDto> exchange = restTemplate.exchange(
            uri,
            HttpMethod.GET,
            new HttpEntity<>(httpHeaders),
            new ParameterizedTypeReference<>() {
            });

        return exchange.getBody();
    }
}

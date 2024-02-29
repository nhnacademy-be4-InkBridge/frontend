package com.nhnacademy.inkbridge.front.adaptor.impl;

import com.nhnacademy.inkbridge.front.adaptor.BookCategoryAdaptor;
import com.nhnacademy.inkbridge.front.dto.bookCategory.BookCategoryReadResponseDto;
import com.nhnacademy.inkbridge.front.property.GatewayProperties;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * class: BookCategoryAdaptorImpl.
 *
 * @author choijaehun
 * @version 2024/02/28
 */

@Component
@RequiredArgsConstructor
public class BookCategoryAdaptorImpl implements BookCategoryAdaptor {

    private final RestTemplate restTemplate;
    private final GatewayProperties gatewayProperties;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<BookCategoryReadResponseDto> readBookCategories(Long bookId) {
        URI uri = UriComponentsBuilder
            .fromUriString(gatewayProperties.getUrl())
            .path("api/book-category/{bookId}")
            .encode()
            .build()
            .expand(bookId)
            .toUri();

        ResponseEntity<List<BookCategoryReadResponseDto>> responseEntity = restTemplate.exchange(
            uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
            });
        return responseEntity.getBody();
    }
}

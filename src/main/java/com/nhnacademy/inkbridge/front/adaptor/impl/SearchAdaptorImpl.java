package com.nhnacademy.inkbridge.front.adaptor.impl;

import com.nhnacademy.inkbridge.front.adaptor.SearchAdaptor;
import com.nhnacademy.inkbridge.front.dto.search.BookSearchResponseDto;
import com.nhnacademy.inkbridge.front.property.GatewayProperties;
import com.nhnacademy.inkbridge.front.utils.CommonUtils;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * class: SearchAdaptorImpl.
 *
 * @author choijaehun
 * @version 2024/03/15
 */

@Component
@RequiredArgsConstructor
public class SearchAdaptorImpl implements SearchAdaptor {

    private final RestTemplate restTemplate;

    private final GatewayProperties gatewayProperties;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<BookSearchResponseDto> searchByText(String text, Pageable pageable) {
        HttpHeaders httpHeaders = CommonUtils.createHeader();
        String sortStr = String.join(",",pageable.getSort().toString().split(": "));
        if (pageable.getSort() == Sort.unsorted()) {
            sortStr = "view,desc";
        }

        URI uri = UriComponentsBuilder
            .fromUriString(gatewayProperties.getUrl())
            .path("api/search")
            .queryParam("text", text)
            .queryParam("page", pageable.getPageNumber())
            .queryParam("size", pageable.getPageSize())
            .queryParam("sort", sortStr)
            .encode()
            .build()
            .toUri();
        RequestEntity<Void> requestEntity = RequestEntity
            .get(uri)
            .headers(httpHeaders)
            .build();
        ResponseEntity<List<BookSearchResponseDto>> responseEntity = restTemplate.exchange(
            requestEntity,
            new ParameterizedTypeReference<>() {
            });
        return responseEntity.getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<BookSearchResponseDto> searchByAll(String field, Pageable pageable) {
        HttpHeaders httpHeaders = CommonUtils.createHeader();
        URI uri = UriComponentsBuilder
            .fromUriString(gatewayProperties.getUrl())
            .path("api/books/filter")
            .queryParam("field", field)
            .queryParam("page", pageable.getPageNumber())
            .queryParam("size", pageable.getPageSize())
            .queryParam("sort", pageable.getSort().toString())
            .encode()
            .build()
            .toUri();

        RequestEntity<Void> requestEntity = RequestEntity
            .get(uri)
            .headers(httpHeaders)
            .build();

        ResponseEntity<List<BookSearchResponseDto>> responseEntity = restTemplate.exchange(
            requestEntity,
            new ParameterizedTypeReference<>() {
            });

        return responseEntity.getBody();
    }
}

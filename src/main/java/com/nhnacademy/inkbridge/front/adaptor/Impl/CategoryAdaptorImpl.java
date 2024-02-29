package com.nhnacademy.inkbridge.front.adaptor.Impl;

import com.nhnacademy.inkbridge.front.adaptor.CategoryAdaptor;
import com.nhnacademy.inkbridge.front.dto.category.CategoryCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.category.CategoryUpdateRequestDto;
import com.nhnacademy.inkbridge.front.dto.category.ParentCategoryReadResponseDto;
import com.nhnacademy.inkbridge.front.property.GatewayProperties;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * class: CategoryAdaptorImpl.
 *
 * @author choijaehun
 * @version 2024/02/24
 */
@Component
@RequiredArgsConstructor
public class CategoryAdaptorImpl implements CategoryAdaptor {

    private final RestTemplate restTemplate;
    private final GatewayProperties gatewayProperties;


    /**
     * {@inheritDoc}
     */
    @Override
    public void createCategory(CategoryCreateRequestDto requestDto) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        URI uri = UriComponentsBuilder
            .fromUriString(gatewayProperties.getUrl())
            .path("/api/category")
            .encode()
            .build()
            .toUri();

        RequestEntity<CategoryCreateRequestDto> requestEntity = RequestEntity
            .post(uri)
            .headers(httpHeaders)
            .body(requestDto);

        restTemplate.exchange(requestEntity, CategoryCreateRequestDto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ParentCategoryReadResponseDto> readCategories() {
        URI uri = UriComponentsBuilder
            .fromUriString(gatewayProperties.getUrl())
            .path("api/category")
            .encode()
            .build()
            .toUri();

        ResponseEntity<List<ParentCategoryReadResponseDto>> responseEntity = restTemplate.exchange(
            uri,
            HttpMethod.GET, null, new ParameterizedTypeReference<>() {
            });
        return responseEntity.getBody();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void updateCategory(Long categoryId, CategoryUpdateRequestDto requestDto) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        URI uri = UriComponentsBuilder
            .fromUriString(gatewayProperties.getUrl())
            .path("api/category/{categoryId}")
            .encode()
            .build()
            .expand(categoryId)
            .toUri();

        RequestEntity<CategoryUpdateRequestDto> requestEntity = RequestEntity
            .put(uri)
            .headers(httpHeaders)
            .body(requestDto);

        restTemplate.exchange(requestEntity, CategoryUpdateRequestDto.class);
    }
}

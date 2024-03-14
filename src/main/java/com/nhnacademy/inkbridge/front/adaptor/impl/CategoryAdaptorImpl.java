package com.nhnacademy.inkbridge.front.adaptor.impl;

import com.nhnacademy.inkbridge.front.adaptor.CategoryAdaptor;
import com.nhnacademy.inkbridge.front.dto.category.CategoryCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.category.CategoryUpdateRequestDto;
import com.nhnacademy.inkbridge.front.dto.category.ParentCategoryReadResponseDto;
import com.nhnacademy.inkbridge.front.property.GatewayProperties;
import com.nhnacademy.inkbridge.front.utils.CommonUtils;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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

    private static final String DEFAULT_PATH = "api/categories";
    private final RestTemplate restTemplate;
    private final GatewayProperties gatewayProperties;

    /**
     * {@inheritDoc}
     */
    @Override
    public void createCategory(CategoryCreateRequestDto requestDto) {
        HttpHeaders httpHeaders = CommonUtils.createHeader();
        URI uri = buildUriComponents(DEFAULT_PATH).build().toUri();

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
        HttpHeaders httpHeaders = CommonUtils.createHeader();

        URI uri = buildUriComponents(DEFAULT_PATH).build().toUri();

        ResponseEntity<List<ParentCategoryReadResponseDto>> responseEntity = restTemplate.exchange(
            uri,
            HttpMethod.GET, new HttpEntity<>(httpHeaders), new ParameterizedTypeReference<>() {
            });
        return responseEntity.getBody();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void updateCategory(Long categoryId, CategoryUpdateRequestDto requestDto) {
        HttpHeaders httpHeaders = CommonUtils.createHeader();
        URI uri = buildUriComponents(DEFAULT_PATH).path("/{categoryId}").build().expand(categoryId)
            .toUri();

        RequestEntity<CategoryUpdateRequestDto> requestEntity = RequestEntity
            .put(uri)
            .headers(httpHeaders)
            .body(requestDto);

        restTemplate.exchange(requestEntity, CategoryUpdateRequestDto.class);
    }

    /**
     * 공통 경로를 만들어주는 메소드입니다.
     *
     * @param path 개별 경로
     * @return UriComponentsBuilder
     */
    private UriComponentsBuilder buildUriComponents(String path) {
        return UriComponentsBuilder
            .fromUriString(gatewayProperties.getUrl())
            .path(path)
            .encode();
    }
}

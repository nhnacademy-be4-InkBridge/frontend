package com.nhnacademy.inkbridge.front.adaptor.Impl;

import com.nhnacademy.inkbridge.front.adaptor.CategoryAdaptor;
import com.nhnacademy.inkbridge.front.dto.category.CategoryCreateRequestDto;
import com.nhnacademy.inkbridge.front.property.GatewayProperties;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
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
     * 카테고리를 생성하는 메소드입니다.
     *
     * @param requestDto CategoryCreateRequestDto
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
}

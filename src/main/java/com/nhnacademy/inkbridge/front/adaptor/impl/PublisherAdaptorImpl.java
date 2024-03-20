package com.nhnacademy.inkbridge.front.adaptor.impl;

import com.nhnacademy.inkbridge.front.adaptor.PublisherAdaptor;
import com.nhnacademy.inkbridge.front.dto.PageRequestDto;
import com.nhnacademy.inkbridge.front.dto.publisher.PublisherCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.publisher.PublisherReadResponseDto;
import com.nhnacademy.inkbridge.front.property.GatewayProperties;
import com.nhnacademy.inkbridge.front.utils.CommonUtils;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * class: PublisherAdaptorImpl.
 *
 * @author choijaehun
 * @version 2024/03/20
 */

@Component
@RequiredArgsConstructor
public class PublisherAdaptorImpl implements PublisherAdaptor {

    private static final String DEFAULT_PATH = "api/admin";

    private final RestTemplate restTemplate;

    private final GatewayProperties gatewayProperties;

    /**
     * {@inheritDoc}
     */
    @Override
    public void createPublisher(PublisherCreateRequestDto request) {
        HttpHeaders httpHeaders = CommonUtils.createHeader();
        URI uri = UriComponentsBuilder
            .fromUriString(gatewayProperties.getUrl())
            .path(DEFAULT_PATH)
            .path("/publisher")
            .encode()
            .build()
            .toUri();

        RequestEntity<PublisherCreateRequestDto> requestEntity = RequestEntity
            .post(uri)
            .headers(httpHeaders)
            .body(request);

        restTemplate.exchange(requestEntity, PublisherCreateRequestDto.class);
    }

    /**
     * {@inheritDoc}
     * @return 출판사 리스트
     */
    @Override
    public List<PublisherReadResponseDto> readPublishers() {
        HttpHeaders httpHeaders = CommonUtils.createHeader();
        URI uri =UriComponentsBuilder
            .fromUriString(gatewayProperties.getUrl())
            .path(DEFAULT_PATH)
            .path("/publishers")
            .encode()
            .build()
            .toUri();

        RequestEntity<Void> requestEntity = RequestEntity
            .get(uri)
            .headers(httpHeaders)
            .build();

         return restTemplate.exchange(requestEntity,
             new ParameterizedTypeReference<>() {
             });
    }
}

package com.nhnacademy.inkbridge.front.adaptor.impl;

import com.nhnacademy.inkbridge.front.adaptor.ReviewAdaptor;
import com.nhnacademy.inkbridge.front.dto.review.ReviewCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.review.ReviewReadResponseDto;
import com.nhnacademy.inkbridge.front.property.GatewayProperties;
import com.nhnacademy.inkbridge.front.utils.CommonUtils;
import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * class: ReviewAdaptorImpl.
 *
 * @author minm063
 * @version 2024/03/19
 */
@Slf4j
@Component
public class ReviewAdaptorImpl implements ReviewAdaptor {

    private final RestTemplate restTemplate;
    private final GatewayProperties gatewayProperties;
    private static final String PATH = "/api/reviews";

    public ReviewAdaptorImpl(RestTemplate restTemplate, GatewayProperties gatewayProperties) {
        this.restTemplate = restTemplate;
        this.gatewayProperties = gatewayProperties;
    }

    @Override
    public ReviewReadResponseDto getReviews(Long memberId) {
        URI uri = UriComponentsBuilder
            .fromUriString(gatewayProperties.getUrl())
            .path(PATH)
            .queryParam("memberId", memberId)
            .encode()
            .build()
            .toUri();

        ResponseEntity<ReviewReadResponseDto> exchange = restTemplate.exchange(uri, HttpMethod.GET,
            new HttpEntity<>(CommonUtils.createHeader()),
            new ParameterizedTypeReference<>() {
            });
        if (exchange.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }
        return exchange.getBody();
    }

    @Override
    public ReviewReadResponseDto getReviewsByBookId(Long bookId) {
        URI uri = UriComponentsBuilder
            .fromUriString(gatewayProperties.getUrl())
            .path(PATH)
            .path("/books/{bookId}")
            .encode()
            .build()
            .expand(bookId)
            .toUri();

        ResponseEntity<ReviewReadResponseDto> exchange = restTemplate.exchange(uri, HttpMethod.GET,
            new HttpEntity<>(CommonUtils.createHeader()),
            new ParameterizedTypeReference<>() {
            });
        if (exchange.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }

        return exchange.getBody();
    }

    @Override
    public void createReview(MultipartFile[] reviewFiles,
        Long memberId, ReviewCreateRequestDto reviewCreateRequestDto) {
        URI uri = UriComponentsBuilder
            .fromUriString(gatewayProperties.getUrl())
            .path(PATH)
            .queryParam("memberId", memberId)
            .encode()
            .build()
            .toUri();

        MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();
        HttpHeaders httpHeaders = CommonUtils.createHeader(MediaType.MULTIPART_FORM_DATA);
        for (MultipartFile file : reviewFiles) {
            try {
                // MultipartFile을 byte 배열로 변환하여 추가
                byte[] fileBytes = file.getBytes();
                ByteArrayResource resource = new ByteArrayResource(fileBytes) {
                    @Override
                    public String getFilename() {
                        return file.getOriginalFilename();
                    }
                };
                multiValueMap.add("images", resource);
            } catch (IOException e) {
                e.printStackTrace();
                // 예외 처리
            }
        }
        multiValueMap.add("review", reviewCreateRequestDto);

        log.info("create1");

        ResponseEntity<HttpStatus> response = restTemplate.postForEntity(uri,
            new HttpEntity<>(multiValueMap, httpHeaders),
            HttpStatus.class);

        log.info("create2");
        if (response.getStatusCode() != HttpStatus.CREATED) {
            throw new RuntimeException();
        }
    }

    @Override
    public void updateReview(Long memberId, Long reviewId,
        ReviewCreateRequestDto reviewCreateRequestDto, List<MultipartFile> reviewFiles) {
        URI uri = UriComponentsBuilder
            .fromUriString(gatewayProperties.getUrl())
            .path(PATH)
            .path("/{reviewId}")
            .queryParam("memberId", memberId)
            .encode()
            .build()
            .expand(reviewId)
            .toUri();

        MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();
        HttpHeaders httpHeaders = CommonUtils.createHeader(MediaType.MULTIPART_FORM_DATA);
        List<Resource> resourceFiles = reviewFiles.stream()
            .map(MultipartFile::getResource)
            .collect(Collectors.toList());
        multiValueMap.add("images", resourceFiles);
        multiValueMap.add("review", reviewCreateRequestDto);

        ResponseEntity<HttpStatus> exchange = restTemplate.exchange(
            uri,
            HttpMethod.PUT,
            new HttpEntity<>(multiValueMap, httpHeaders),
            HttpStatus.class);

        if (exchange.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }
    }
}

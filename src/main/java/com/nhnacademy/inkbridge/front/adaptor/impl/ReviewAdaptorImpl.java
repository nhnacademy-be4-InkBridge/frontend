package com.nhnacademy.inkbridge.front.adaptor.impl;

import com.nhnacademy.inkbridge.front.adaptor.ReviewAdaptor;
import com.nhnacademy.inkbridge.front.dto.review.ReviewBookReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.review.ReviewCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.review.ReviewMemberReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.review.ReviewUpdateRequestDto;
import com.nhnacademy.inkbridge.front.property.GatewayProperties;
import com.nhnacademy.inkbridge.front.utils.CommonUtils;
import java.io.IOException;
import java.net.URI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ByteArrayResource;
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
    private static final String PATH = "/api/auth/reviews";
    private static final String MEMBER_QUERY_PARAM = "memberId";

    public ReviewAdaptorImpl(RestTemplate restTemplate, GatewayProperties gatewayProperties) {
        this.restTemplate = restTemplate;
        this.gatewayProperties = gatewayProperties;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ReviewMemberReadResponseDto getReviews(Long memberId, Long size, Long page) {
        URI uri = UriComponentsBuilder
            .fromUriString(gatewayProperties.getUrl())
            .path(PATH)
            .queryParam(MEMBER_QUERY_PARAM, memberId)
            .queryParam("size", size)
            .queryParam("page", page)
            .encode()
            .build()
            .toUri();

        ResponseEntity<ReviewMemberReadResponseDto> exchange = restTemplate.exchange(uri,
            HttpMethod.GET,
            new HttpEntity<>(CommonUtils.createHeader()),
            new ParameterizedTypeReference<>() {
            });

        return exchange.getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ReviewBookReadResponseDto getReviewsByBookId(Long bookId) {
        URI uri = UriComponentsBuilder
            .fromUriString(gatewayProperties.getUrl())
            .path(PATH)
            .path("/books/{bookId}")
            .encode()
            .build()
            .expand(bookId)
            .toUri();

        ResponseEntity<ReviewBookReadResponseDto> exchange = restTemplate.exchange(uri,
            HttpMethod.GET,
            new HttpEntity<>(CommonUtils.createHeader()),
            new ParameterizedTypeReference<>() {
            });

        return exchange.getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createReview(MultipartFile[] reviewFiles,
        Long memberId, ReviewCreateRequestDto reviewCreateRequestDto) throws IOException {
        URI uri = UriComponentsBuilder
            .fromUriString(gatewayProperties.getUrl())
            .path(PATH)
            .queryParam(MEMBER_QUERY_PARAM, memberId)
            .encode()
            .build()
            .toUri();

        MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();
        HttpHeaders httpHeaders = CommonUtils.createHeader(MediaType.MULTIPART_FORM_DATA);
        for (MultipartFile file : reviewFiles) {
            byte[] fileBytes = file.getBytes();
            ByteArrayResource resource = new ByteArrayResource(fileBytes) {
                @Override
                public String getFilename() {
                    return file.getOriginalFilename();
                }
            };
            multiValueMap.add("images", resource);
        }
        multiValueMap.add("review", reviewCreateRequestDto);

        restTemplate.postForEntity(uri,
            new HttpEntity<>(multiValueMap, httpHeaders),
            HttpStatus.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateReview(Long memberId, Long reviewId,
        ReviewUpdateRequestDto reviewUpdateRequestDto, MultipartFile[] reviewFiles)
        throws IOException {
        URI uri = UriComponentsBuilder
            .fromUriString(gatewayProperties.getUrl())
            .path(PATH)
            .path("/{reviewId}")
            .queryParam(MEMBER_QUERY_PARAM, memberId)
            .encode()
            .build()
            .expand(reviewId)
            .toUri();

        MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();
        HttpHeaders httpHeaders = CommonUtils.createHeader(MediaType.MULTIPART_FORM_DATA);
        for (MultipartFile file : reviewFiles) {
            byte[] fileBytes = file.getBytes();
            ByteArrayResource resource = new ByteArrayResource(fileBytes) {
                @Override
                public String getFilename() {
                    return file.getOriginalFilename();
                }
            };
            multiValueMap.add("images", resource);
        }
        multiValueMap.add("review", reviewUpdateRequestDto);

        restTemplate.exchange(
            uri,
            HttpMethod.PUT,
            new HttpEntity<>(multiValueMap, httpHeaders),
            HttpStatus.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ReviewBookReadResponseDto getReviewPagination(Long page, Long size, Long bookId) {
        URI uri = UriComponentsBuilder
            .fromUriString(gatewayProperties.getUrl())
            .path(PATH)
            .path("/books/{bookId}")
            .queryParam("page", page)
            .queryParam("size", size)
            .encode()
            .build()
            .expand(bookId)
            .toUri();

        ResponseEntity<ReviewBookReadResponseDto> exchange = restTemplate.exchange(uri,
            HttpMethod.GET, new HttpEntity<>(CommonUtils.createHeader()),
            ReviewBookReadResponseDto.class);

        return exchange.getBody();
    }
}

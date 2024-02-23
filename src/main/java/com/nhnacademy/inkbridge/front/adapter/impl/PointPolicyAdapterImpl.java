package com.nhnacademy.inkbridge.front.adapter.impl;

import com.nhnacademy.inkbridge.front.adapter.PointPolicyAdapter;
import com.nhnacademy.inkbridge.front.dto.pointpolicy.PointPolicyCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.pointpolicy.PointPolicyReadResponseDto;
import com.nhnacademy.inkbridge.front.property.GatewayProperties;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * class: PointPolicyAdapter.
 *
 * @author jangjaehun
 * @version 2024/02/22
 */
@Component
@RequiredArgsConstructor
public class PointPolicyAdapterImpl implements PointPolicyAdapter {

    private final RestTemplate restTemplate;
    private final GatewayProperties gatewayProperties;


    /**
     * 현재 적용되는 포인트 정책 리스트를 호출하는 메소드입니다.
     *
     * @return List - PointPolicyReadResponseDto
     */
    @Override
    public List<PointPolicyReadResponseDto> getCurrentPointPolicies() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<Void> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<List<PointPolicyReadResponseDto>> exchange =
            restTemplate.exchange(gatewayProperties.getUrl() + "/api/point-policies/current",
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<>() {
                });

        return exchange.getBody();
    }

    /**
     * 전체 포인트 정책 변경 내역을 호출하는 메소드입니다.
     *
     * @return List - PointPolicyReadResponseDto
     */
    @Override
    public List<PointPolicyReadResponseDto> getPointPolicies() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<Void> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<List<PointPolicyReadResponseDto>> exchange =
            restTemplate.exchange(gatewayProperties.getUrl() + "/api/point-policies",
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<>() {
                });

        return exchange.getBody();
    }

    /**
     * 포인트 정책을 생성하는 메소드입니다.
     *
     * @param requestDto PointPolicyCreateRequestDto
     */
    @Override
    public void createPointPolicy(PointPolicyCreateRequestDto requestDto) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<PointPolicyCreateRequestDto> entity = new HttpEntity<>(requestDto,
            httpHeaders);
        restTemplate.exchange(gatewayProperties.getUrl() + "/api/point-policies",
            HttpMethod.POST,
            entity,
            new ParameterizedTypeReference<Void>() {
            });
    }

    /**
     * 포인트 정책 유형의 변경 내역 리스트를 호출하는 메소드입니다
     *
     * @param pointPolicyTypeId Integer
     * @return List - PointPolicyReadResponseDto
     */
    @Override
    public List<PointPolicyReadResponseDto> getPointPoliciesByTypeId(Integer pointPolicyTypeId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<Void> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<List<PointPolicyReadResponseDto>> exchange =
            restTemplate.exchange(
                gatewayProperties.getUrl() + "/api/point-policies/{pointPolicyTypeId}",
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<>() {
                }, pointPolicyTypeId);

        return exchange.getBody();
    }
}

package com.nhnacademy.inkbridge.front.adaptor.impl;

import static com.nhnacademy.inkbridge.front.utils.CommonUtils.createHeader;

import com.nhnacademy.inkbridge.front.adaptor.PointPolicyAdaptor;
import com.nhnacademy.inkbridge.front.dto.pointpolicy.PointPolicyAdminReadResponseDto;
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
public class PointPolicyAdaptorImpl implements PointPolicyAdaptor {

    private final RestTemplate restTemplate;
    private final GatewayProperties gatewayProperties;


    /**
     * 현재 적용되는 포인트 정책 리스트를 호출하는 메소드입니다.
     *
     * @return List - PointPolicyReadResponseDto
     */
    @Override
    public List<PointPolicyAdminReadResponseDto> getCurrentPointPolicies() {
        HttpEntity<Void> entity = new HttpEntity<>(createHeader());
        ResponseEntity<List<PointPolicyAdminReadResponseDto>> exchange =
            restTemplate.exchange(gatewayProperties.getUrl() + "/api/admin/point-policies/current",
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
    public List<PointPolicyAdminReadResponseDto> getPointPolicies() {
        HttpEntity<Void> entity = new HttpEntity<>(createHeader());
        ResponseEntity<List<PointPolicyAdminReadResponseDto>> exchange =
            restTemplate.exchange(gatewayProperties.getUrl() + "/api/admin/point-policies",
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
        HttpEntity<PointPolicyCreateRequestDto> entity = new HttpEntity<>(requestDto,
            createHeader());
        restTemplate.exchange(gatewayProperties.getUrl() + "/api/admin/point-policies",
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
    public List<PointPolicyAdminReadResponseDto> getPointPoliciesByTypeId(
        Integer pointPolicyTypeId) {
        HttpEntity<Void> entity = new HttpEntity<>(createHeader());
        ResponseEntity<List<PointPolicyAdminReadResponseDto>> exchange =
            restTemplate.exchange(
                gatewayProperties.getUrl() + "/api/admin/point-policies/{pointPolicyTypeId}",
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<>() {
                }, pointPolicyTypeId);

        return exchange.getBody();
    }

    /**
     * 포인트 정책 유형 번호로 적용중인 정책을 조회하는 메소드입니다.
     *
     * @param pointPolicyTypeId 포인트 정책 유형 번호
     * @return 적용 포인트 정책
     */
    @Override
    public PointPolicyReadResponseDto getCurrentPointPolicyById(Integer pointPolicyTypeId) {
        HttpEntity<Void> entity = new HttpEntity<>(createHeader());
        ResponseEntity<PointPolicyReadResponseDto> exchange =
            restTemplate.exchange(
                gatewayProperties.getUrl() + "/api/point-policies/current/{pointPolicyTypeId}",
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<>() {
                }, pointPolicyTypeId);

        return exchange.getBody();
    }
}

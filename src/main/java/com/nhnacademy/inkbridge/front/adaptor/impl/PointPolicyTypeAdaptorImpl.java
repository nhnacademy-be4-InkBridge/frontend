package com.nhnacademy.inkbridge.front.adaptor.impl;

import static com.nhnacademy.inkbridge.front.utils.CommonUtils.createHeader;

import com.nhnacademy.inkbridge.front.adaptor.PointPolicyTypeAdaptor;
import com.nhnacademy.inkbridge.front.dto.pointpolicytype.PointPolicyTypeCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.pointpolicytype.PointPolicyTypeReadResponseDto;
import com.nhnacademy.inkbridge.front.property.GatewayProperties;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * class: PointPolicyTypeAdaptorImpl.
 *
 * @author jangjaehun
 * @version 2024/02/22
 */
@Component
@RequiredArgsConstructor
public class PointPolicyTypeAdaptorImpl implements PointPolicyTypeAdaptor {

    private final RestTemplate restTemplate;

    private final GatewayProperties gatewayProperties;


    /**
     * 포인트 정책 유형을 생성하는 메소드입니다.
     *
     * @param requestDto PointPolicyTypeCreateRequestDto
     */
    @Override
    public void createPointPolicyType(PointPolicyTypeCreateRequestDto requestDto) {
        HttpEntity<PointPolicyTypeCreateRequestDto> entity = new HttpEntity<>(requestDto,
            createHeader());
        restTemplate.exchange(gatewayProperties.getUrl() + "/api/admin/point-policy-types",
            HttpMethod.POST,
            entity,
            new ParameterizedTypeReference<Void>() {
            });
    }

    /**
     * 포인트 정책 유형 리스트를 호출하는 메소드입니다.
     *
     * @return List - PointPolicyTypeReadResponseDto
     */
    @Override
    public List<PointPolicyTypeReadResponseDto> getPointPolicyTypes() {
        HttpEntity<Void> entity = new HttpEntity<>(createHeader());
        ResponseEntity<List<PointPolicyTypeReadResponseDto>> exchange = restTemplate.exchange(
            gatewayProperties.getUrl() + "/api/point-policy-types",
            HttpMethod.GET,
            entity,
            new ParameterizedTypeReference<>() {
            });

        return exchange.getBody();
    }

}
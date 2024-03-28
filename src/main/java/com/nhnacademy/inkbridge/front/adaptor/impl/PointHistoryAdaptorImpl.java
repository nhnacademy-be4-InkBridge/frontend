package com.nhnacademy.inkbridge.front.adaptor.impl;

import static com.nhnacademy.inkbridge.front.utils.CommonUtils.createHeader;

import com.nhnacademy.inkbridge.front.adaptor.PointHistoryAdaptor;
import com.nhnacademy.inkbridge.front.dto.point.PointHistoryReadResponseDto;
import com.nhnacademy.inkbridge.front.property.GatewayProperties;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * class: PointHistoryAdaptorImpl.
 *
 * @author jeongbyeonghun
 * @version 3/22/24
 */

@Component
@RequiredArgsConstructor
@Slf4j
public class PointHistoryAdaptorImpl implements PointHistoryAdaptor {

    private final RestTemplate restTemplate;

    private final GatewayProperties gatewayProperties;

    @Override
    public List<PointHistoryReadResponseDto> getPointHistoryList() {
        HttpHeaders httpHeaders = createHeader();

        HttpEntity<Void> entity = new HttpEntity<>(httpHeaders);

        ResponseEntity<List<PointHistoryReadResponseDto>> responseEntity = restTemplate.exchange(
            gatewayProperties.getUrl() + "/api/mypage/pointHistory", HttpMethod.GET, entity,
            new ParameterizedTypeReference<>() {
            });
        return responseEntity.getBody();
    }
}

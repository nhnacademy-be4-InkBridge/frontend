package com.nhnacademy.inkbridge.front.adaptor.impl;

import com.nhnacademy.inkbridge.front.adaptor.CouponAdaptor;
import com.nhnacademy.inkbridge.front.dto.PageRequestDto;
import com.nhnacademy.inkbridge.front.dto.coupon.CouponReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.coupon.CouponType;
import com.nhnacademy.inkbridge.front.property.GatewayProperties;
import java.util.List;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * class: CouponAdaptorImpl.
 *
 * @author JBum
 * @version 2024/02/23
 */
@Component
public class CouponAdaptorImpl implements CouponAdaptor {

    private final RestTemplate restTemplate;
    private final GatewayProperties gatewayProperties;

    public CouponAdaptorImpl(RestTemplate restTemplate, GatewayProperties gatewayProperties) {
        this.restTemplate = restTemplate;
        this.gatewayProperties = gatewayProperties;
    }

    @Override
    public PageRequestDto<CouponReadResponseDto> getAdminCoupons(Integer couponStatusId,
        Integer page, Integer size) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        String url = String.format("%s?coupon-status-id=%d&page=%d&size=%d",
            gatewayProperties.getUrl() + "/api/admin/coupons", couponStatusId, page, size);
        System.out.println(url);
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<PageRequestDto<CouponReadResponseDto>> exchange =
            restTemplate.exchange(url,
                HttpMethod.GET, httpEntity,
                new ParameterizedTypeReference<PageRequestDto<CouponReadResponseDto>>() {
                });
        if (!exchange.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Failed to retrieve coupons");
        }

        return exchange.getBody();
    }
}

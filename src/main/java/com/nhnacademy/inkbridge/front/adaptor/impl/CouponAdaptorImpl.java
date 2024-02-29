package com.nhnacademy.inkbridge.front.adaptor.impl;

import com.nhnacademy.inkbridge.front.adaptor.CouponAdaptor;
import com.nhnacademy.inkbridge.front.dto.PageRequestDto;
import com.nhnacademy.inkbridge.front.dto.coupon.CouponCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.coupon.CouponReadResponseDto;
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

    /**
     * 쿠폰 어댑터에 필요한 컴포넌트들 호출.
     *
     * @param restTemplate      WAS를 호출할 restTemplate
     * @param gatewayProperties 게이트웨이설정
     */
    public CouponAdaptorImpl(RestTemplate restTemplate, GatewayProperties gatewayProperties) {
        this.restTemplate = restTemplate;
        this.gatewayProperties = gatewayProperties;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PageRequestDto<CouponReadResponseDto> getAdminCoupons(Integer couponStatusId,
        Integer page, Integer size) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        String url = String.format("%s?coupon-status-id=%d&page=%d&size=%d",
            gatewayProperties.getUrl() + "/api/admin/coupons", couponStatusId, page, size);
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

    @Override
    public void setCoupons(CouponCreateRequestDto couponCreateRequestDto) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        String url = gatewayProperties.getUrl()+"/api/admin/coupons";
        HttpEntity<CouponCreateRequestDto> httpEntity = new HttpEntity<>(couponCreateRequestDto, httpHeaders);
        ResponseEntity<PageRequestDto<CouponReadResponseDto>> exchange = restTemplate.exchange(
            url,
            HttpMethod.POST,
            httpEntity,
            new ParameterizedTypeReference<PageRequestDto<CouponReadResponseDto>>() {}
        );
        if (!exchange.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Failed to retrieve coupons");
        }
    }
}
package com.nhnacademy.inkbridge.front.adaptor.impl;

import static com.nhnacademy.inkbridge.front.utils.CommonUtils.createHeader;

import com.nhnacademy.inkbridge.front.adaptor.CouponAdaptor;
import com.nhnacademy.inkbridge.front.dto.PageRequestDto;
import com.nhnacademy.inkbridge.front.dto.coupon.CouponCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.coupon.CouponReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.coupon.OrderCouponReadResponseDto;
import com.nhnacademy.inkbridge.front.property.GatewayProperties;
import java.net.URI;
import java.util.List;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

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
        HttpHeaders httpHeaders = createHeader();

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
        HttpHeaders httpHeaders = createHeader();

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

    @Override
    public List<OrderCouponReadResponseDto> getOrderCoupons(Long memberId, List<String> bookIds) {
        HttpEntity<Void> httpEntity = new HttpEntity<>(createHeader());

        URI uri = UriComponentsBuilder
            .fromUriString(gatewayProperties.getUrl())
            .path("/api/members/{memberId}/order-coupons")
            .queryParam("book-id", String.join(",", bookIds))
            .encode()
            .build()
            .expand(memberId)
            .toUri();

        ResponseEntity<List<OrderCouponReadResponseDto>> exchange = restTemplate.exchange(
            uri,
            HttpMethod.GET,
            httpEntity,
            new ParameterizedTypeReference<>() {
            }
        );

        return exchange.getBody();
    }
}

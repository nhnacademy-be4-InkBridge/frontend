package com.nhnacademy.inkbridge.front.adaptor.impl;

import static com.nhnacademy.inkbridge.front.utils.CommonUtils.createHeader;

import com.nhnacademy.inkbridge.front.adaptor.OrderAdaptor;
import com.nhnacademy.inkbridge.front.dto.PageRequestDto;
import com.nhnacademy.inkbridge.front.dto.order.BookOrderViewResponseDto;
import com.nhnacademy.inkbridge.front.dto.order.OrderCreateResponseDto;
import com.nhnacademy.inkbridge.front.dto.order.OrderPaymentInfoReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.order.OrderCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.order.OrderReadResponseDto;
import com.nhnacademy.inkbridge.front.property.GatewayProperties;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * class: OrderAdaptorImpl.
 *
 * @author jangjaehun
 * @version 2024/03/05
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class OrderAdaptorImpl implements OrderAdaptor {

    private final RestTemplate restTemplate;
    private final GatewayProperties gatewayProperties;

    /**
     * {@inheritDoc}
     *
     * @param requestDto 주문 정보
     * @return 주문 번호
     */
    @Override
    public OrderCreateResponseDto createOrder(OrderCreateRequestDto requestDto) {
        HttpEntity<OrderCreateRequestDto> entity = new HttpEntity<>(requestDto, createHeader());
        ResponseEntity<OrderCreateResponseDto> exchange = restTemplate.exchange(
            gatewayProperties.getUrl() + "/api/orders",
            HttpMethod.POST,
            entity,
            new ParameterizedTypeReference<>() {
            });

        return exchange.getBody();
    }

    /**
     * {@inheritDoc}
     *
     * @param orderCode 주문 번호
     * @return 주문 결제 정보
     */
    @Override
    public OrderPaymentInfoReadResponseDto getOrderPaymentInfo(String orderCode) {
        HttpEntity<Void> entity = new HttpEntity<>(createHeader());

        URI uri = UriComponentsBuilder
            .fromUriString(gatewayProperties.getUrl())
            .encode()
            .path("/api/orders/{orderCode}/order-pays")
            .build()
            .expand(orderCode)
            .toUri();

        ResponseEntity<OrderPaymentInfoReadResponseDto> exchange = restTemplate.exchange(
            uri,
            HttpMethod.GET,
            entity,
            new ParameterizedTypeReference<>() {
            });

        return exchange.getBody();
    }

    /**
     * {@inheritDoc}
     *
     * @param pageable 페이지 정보
     * @return 주문 목록
     */
    @Override
    public PageRequestDto<OrderReadResponseDto> getOrderPages(Pageable pageable) {
        HttpEntity<Void> entity = new HttpEntity<>(createHeader());

        URI uri = UriComponentsBuilder
            .fromUriString(gatewayProperties.getUrl())
            .encode()
            .path("/api/admin/orders")
            .queryParam("page", pageable.getPageNumber())
            .queryParam("size", pageable.getPageSize())
            .build()
            .toUri();

        ResponseEntity<PageRequestDto<OrderReadResponseDto>> exchange = restTemplate.exchange(
            uri,
            HttpMethod.GET,
            entity,
            new ParameterizedTypeReference<>() {
            }
        );

        return exchange.getBody();
    }

    /**
     * {@inheritDoc}
     *
     * @param orderId 주문 번호
     * @return 주문 상세 내역
     */
    @Override
    public BookOrderViewResponseDto getOrderInfo(Long orderId) {
        HttpEntity<Void> entity = new HttpEntity<>(createHeader());

        URI uri = UriComponentsBuilder
            .fromUriString(gatewayProperties.getUrl())
            .encode()
            .path("/api/admin/orders/{order-id}")
            .build()
            .expand(orderId)
            .toUri();

        ResponseEntity<BookOrderViewResponseDto> exchage = restTemplate.exchange(
            uri,
            HttpMethod.GET,
            entity,
            new ParameterizedTypeReference<>() {
            }
        );

        return exchage.getBody();
    }

    /**
     * {@inheritDoc}
     *
     * @param orderId 주문 번호
     */
    @Override
    public void updateOrderStatus(Long orderId) {
        HttpEntity<Void> entity = new HttpEntity<>(createHeader());

        URI uri = UriComponentsBuilder
            .fromUriString(gatewayProperties.getUrl())
            .encode()
            .path("/api/admin/orders/{order-id}")
            .build()
            .expand(orderId)
            .toUri();

        restTemplate.exchange(
            uri,
            HttpMethod.PUT,
            entity,
            Void.class
        );
    }

    /**
     * {@inheritDoc}
     *
     * @param pageable 페이지 정보
     * @param memberId 회원 번호
     * @return 주문 목록
     */
    @Override
    public PageRequestDto<OrderReadResponseDto> getMyOrderPages(Pageable pageable, Long memberId) {
        HttpEntity<Void> entity = new HttpEntity<>(createHeader());

        URI uri = UriComponentsBuilder
            .fromUriString(gatewayProperties.getUrl())
            .encode()
            .path("/api/members/{memberId}/orders")
            .queryParam("page", pageable.getPageNumber())
            .queryParam("size", pageable.getPageSize())
            .build()
            .expand(memberId)
            .toUri();

        ResponseEntity<PageRequestDto<OrderReadResponseDto>> exchange = restTemplate.exchange(
            uri,
            HttpMethod.GET,
            entity,
            new ParameterizedTypeReference<>() {
            }
        );

        return exchange.getBody();
    }

    /**
     * {@inheritDoc}
     *
     * @param orderCode 주문 코드
     * @param memberId 회원 번호
     * @return 주문 상세 내역
     */
    @Override
    public BookOrderViewResponseDto getMyOrderInfo(String orderCode, Long memberId) {
        HttpEntity<Void> entity = new HttpEntity<>(createHeader());

        ResponseEntity<BookOrderViewResponseDto> exchange = restTemplate.exchange(
            gatewayProperties.getUrl() + "/api/members/{memberId}/orders/{orderCode}",
            HttpMethod.GET,
            entity,
            new ParameterizedTypeReference<>() {
            }, memberId, orderCode
        );

        return exchange.getBody();
    }
}

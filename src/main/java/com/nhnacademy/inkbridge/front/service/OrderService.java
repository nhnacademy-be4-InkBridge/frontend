package com.nhnacademy.inkbridge.front.service;

import com.nhnacademy.inkbridge.front.dto.order.OrderBookInfoReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.order.OrderBookReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.order.OrderPaymentInfoReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.order.OrderCreateRequestDto;
import java.util.List;
import java.util.Set;

/**
 * class: OrderService.
 *
 * @author jangjaehun
 * @version 2024/03/05
 */
public interface OrderService {

    /**
     * 주문 요청을 처리하는 메소드입니다.
     *
     * @param requestDto 주문 요청 정보
     * @return 주문 번호
     */
    String createOrder(OrderCreateRequestDto requestDto);

    /**
     * 주문 결제 정보를  조회하는 메소드입니다.
     *
     * @param orderId 주문 번호
     * @return  주문 결제 정보
     */
    OrderPaymentInfoReadResponseDto getOrderPaymentInfo(String orderId);

    List<OrderBookReadResponseDto> getOrderBooks(Set<OrderBookInfoReadResponseDto> bookInfo);
}

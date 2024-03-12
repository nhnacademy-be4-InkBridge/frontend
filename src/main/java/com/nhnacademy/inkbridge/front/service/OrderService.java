package com.nhnacademy.inkbridge.front.service;

import com.nhnacademy.inkbridge.front.dto.order.OrderPaymentInfoReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.order.OrderCreateRequestDto;

/**
 * class: OrderService.
 *
 * @author jangjaehun
 * @version 2024/03/05
 */
public interface OrderService {

    String createOrder(OrderCreateRequestDto requestDto);

    OrderPaymentInfoReadResponseDto getOrderPaymentInfo(String orderId);
}

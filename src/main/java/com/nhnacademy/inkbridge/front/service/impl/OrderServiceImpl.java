package com.nhnacademy.inkbridge.front.service.impl;

import com.nhnacademy.inkbridge.front.adaptor.OrderAdaptor;
import com.nhnacademy.inkbridge.front.dto.order.OrderPaymentInfoReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.order.OrderCreateRequestDto;
import com.nhnacademy.inkbridge.front.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * class: OrderService.
 *
 * @author jangjaehun
 * @version 2024/03/05
 */
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderAdaptor orderAdaptor;

    @Override
    public String createOrder(OrderCreateRequestDto requestDto) {
        return orderAdaptor.createOrder(requestDto);
    }

    @Override
    public OrderPaymentInfoReadResponseDto getOrderPaymentInfo(String orderId) {
        return orderAdaptor.getOrderPaymentInfo(orderId);
    }
}

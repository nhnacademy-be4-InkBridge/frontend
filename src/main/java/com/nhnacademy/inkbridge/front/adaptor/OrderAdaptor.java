package com.nhnacademy.inkbridge.front.adaptor;

import com.nhnacademy.inkbridge.front.dto.order.OrderCreateRequestDto;

/**
 * class: OrderAdaptor.
 *
 * @author jangjaehun
 * @version 2024/03/05
 */
public interface OrderAdaptor {

    String createOrder(OrderCreateRequestDto requestDto);
}

package com.nhnacademy.inkbridge.front.adaptor;

import com.nhnacademy.inkbridge.front.dto.order.OrderCreateRequestDto;

/**
 * class: OrderAdaptor.
 *
 * @author jangjaehun
 * @version 2024/03/05
 */
public interface OrderAdaptor {

    /**
     * 주문 정보 저장 요청을 보내고 주문 번호를 받아옵니다.
     *
     * @param requestDto 주문 정보
     * @return 주문 번호
     */
    String createOrder(OrderCreateRequestDto requestDto);
}

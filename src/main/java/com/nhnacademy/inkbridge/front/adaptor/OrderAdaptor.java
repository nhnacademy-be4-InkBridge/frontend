package com.nhnacademy.inkbridge.front.adaptor;

import com.nhnacademy.inkbridge.front.dto.OrderBooksIdResponseDto;
import com.nhnacademy.inkbridge.front.dto.order.OrderCreateResponseDto;
import com.nhnacademy.inkbridge.front.dto.order.OrderPaymentInfoReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.order.OrderCreateRequestDto;
import java.util.List;

/**
 * class: OrderAdaptor.
 *
 * @author jangjaehun
 * @version 2024/03/05
 */
public interface OrderAdaptor {

    /**
     * 주문 정보 저장 요청을 보내는 메소드입니다.
     *
     * @param requestDto 주문 정보
     * @return 주문 번호
     */
    OrderCreateResponseDto createOrder(OrderCreateRequestDto requestDto);

    /**
     * 주문번호에 맞는 결제에 필요한 정보를 조회하는 메소드입니다.
     *
     * @param orderCode 주문 번호
     * @return 주문 결제 정보
     */
    OrderPaymentInfoReadResponseDto getOrderPaymentInfo(String orderCode);

    /**
     * 주문한 도서 번호를 조회합니다.
     *
     * @param orderCode 주문 코드
     * @return 도서 번호 목록
     */
    List<OrderBooksIdResponseDto> getOrderBooksIdByOrderCode(String orderCode);
}

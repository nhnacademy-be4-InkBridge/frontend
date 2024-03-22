package com.nhnacademy.inkbridge.front.adaptor;

import com.nhnacademy.inkbridge.front.dto.OrderBooksIdResponseDto;
import com.nhnacademy.inkbridge.front.dto.PageRequestDto;
import com.nhnacademy.inkbridge.front.dto.order.BookOrderViewResponseDto;
import com.nhnacademy.inkbridge.front.dto.order.OrderCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.order.OrderCreateResponseDto;
import com.nhnacademy.inkbridge.front.dto.order.OrderPaymentInfoReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.order.OrderCreateRequestDto;
import java.util.List;
import com.nhnacademy.inkbridge.front.dto.order.OrderReadResponseDto;
import org.springframework.data.domain.Pageable;

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

    /**
     * 주문 목록 정보를 조회합니다.
     *
     * @param pageable 페이지 정보
     * @return 주문 목록
     */
    PageRequestDto<OrderReadResponseDto> getOrderPages(Pageable pageable);

    /**
     * 주문 상세 내역 정보를 조회합니다.
     *
     * @param orderId 주문 번호
     * @return 주문 상세 내역
     */
    BookOrderViewResponseDto getOrderInfo(Long orderId);

    /**
     * 주문 상태를 변경합니다.
     *
     * @param orderId 주문 번호
     */
    void updateOrderStatus(Long orderId);

    /**
     * 회원 주문 목록 정보를 조회합니다.
     *
     * @param pageable 페이지 정보
     * @param memberId 회원 번호
     * @return 주문 목록
     */
    PageRequestDto<OrderReadResponseDto> getMyOrderPages(Pageable pageable, Long memberId);

    /**
     * 회원 주문 상세 내역을 조회합니다.
     *
     * @param orderCode 주문 코드
     * @param memberId 회원 번호
     * @return 주문 상세 내역
     */
    BookOrderViewResponseDto getMyOrderInfo(String orderCode, Long memberId);
}

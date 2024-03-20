package com.nhnacademy.inkbridge.front.service;

import com.nhnacademy.inkbridge.front.dto.PageRequestDto;
import com.nhnacademy.inkbridge.front.dto.order.BookOrderViewResponseDto;
import com.nhnacademy.inkbridge.front.dto.order.OrderBookInfoReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.order.OrderBookReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.order.OrderDetailInfoResponseDto;
import com.nhnacademy.inkbridge.front.dto.order.OrderPaymentInfoReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.order.OrderCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.order.OrderReadResponseDto;
import java.util.List;
import java.util.Set;
import org.springframework.data.domain.Pageable;

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
     * @param orderCode 주문 번호
     * @return  주문 결제 정보
     */
    OrderPaymentInfoReadResponseDto getOrderPaymentInfo(String orderCode);

    /**
     * 주문할 도서 정보를 조회합니다.
     * @param bookInfo 도서 번호 및 수량
     * @return 주문 도서 정보
     */
    List<OrderBookReadResponseDto> getOrderBooks(Set<OrderBookInfoReadResponseDto> bookInfo);

    /**
     * 주문 내역 정보를 조회합니다.
     *
     * @param pageable 페이지 정보
     * @return 주문 내역 정보
     */
    PageRequestDto<OrderReadResponseDto> getOrderPages(Pageable pageable);

    /**
     * 주문 상세 내역 정보를 조회합니다.
     * @param orderId 주문 번호
     * @return 주문 상세 내역 정보
     */
    BookOrderViewResponseDto getOrderInfo(Long orderId);

    /**
     * 주문에서 할인된 쿠폰 가격을 계산합니다.
     *
     * @param orderDetailInfoList 주문 상세 목록
     * @return 쿠폰 할인 가격
     */
    List<Long> getCouponDiscountPrice(List<OrderDetailInfoResponseDto> orderDetailInfoList);

    /**
     * 주문 상태를 변경합니다.
     *
     * @param orderId 주문 번호
     */
    void updateOrderStatus(Long orderId);

    /**
     * 회원 주문 목록을 조회합니다.
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

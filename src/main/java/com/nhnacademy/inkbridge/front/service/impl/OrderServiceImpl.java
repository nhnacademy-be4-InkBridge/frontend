package com.nhnacademy.inkbridge.front.service.impl;

import com.nhnacademy.inkbridge.front.adaptor.OrderAdaptor;
import com.nhnacademy.inkbridge.front.dto.OrderBooksIdResponseDto;
import com.nhnacademy.inkbridge.front.dto.PageRequestDto;
import com.nhnacademy.inkbridge.front.dto.order.BookOrderViewResponseDto;
import com.nhnacademy.inkbridge.front.dto.order.OrderBookInfoReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.order.OrderBookReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.order.OrderCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.order.OrderCreateResponseDto;
import com.nhnacademy.inkbridge.front.dto.order.OrderDetailInfoResponseDto;
import com.nhnacademy.inkbridge.front.dto.order.OrderPaymentInfoReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.order.OrderReadResponseDto;
import com.nhnacademy.inkbridge.front.enums.CouponType;
import com.nhnacademy.inkbridge.front.service.CartService;
import com.nhnacademy.inkbridge.front.service.OrderService;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
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
    private final CartService cartService;

    /**
     * {@inheritDoc}
     *
     * @param requestDto 주문 요청 정보
     * @return 주문 번호
     */
    @Override
    public String createOrder(OrderCreateRequestDto requestDto) {
        OrderCreateResponseDto order = orderAdaptor.createOrder(requestDto);
        return order.getOrderCode();
    }

    /**
     * {@inheritDoc}
     *
     * @param orderCode 주문 번호
     * @return 주문 결제 정보
     */
    @Override
    public OrderPaymentInfoReadResponseDto getOrderPaymentInfo(String orderCode) {
        return orderAdaptor.getOrderPaymentInfo(orderCode);
    }

    /**
     * {@inheritDoc}
     *
     * @param bookInfo 도서 수량 정보
     * @return 도서 구매 정보
     */
    @Override
    public List<OrderBookReadResponseDto> getOrderBooks(
        Set<OrderBookInfoReadResponseDto> bookInfo) {
        return cartService.getCartBookInfo(
                bookInfo.stream()
                    .map(OrderBookInfoReadResponseDto::getBookId)
                    .collect(Collectors.toSet()))
            .stream()
            .map(book -> OrderBookReadResponseDto.builder()
                .bookId(book.getBookId())
                .thumbnail(book.getThumbnail())
                .bookTitle(book.getBookTitle())
                .price(book.getPrice())
                .regularPrice(book.getRegularPrice())
                .isPackagable(book.getIsPackagable())
                .amount(getAmountForBook(bookInfo, book.getBookId().toString()))
                .build())
            .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     *
     * @param orderCode 주문
     * @return 도서 번호 목록
     */
    @Override
    public List<OrderBooksIdResponseDto> getOrderBookIds(String orderCode) {
        return orderAdaptor.getOrderBooksIdByOrderCode(orderCode);
    }

    /**
     * {@inheritDoc}
     *
     * @param pageable 페이지 정보
     * @return 주문 목록
     */
    @Override
    public PageRequestDto<OrderReadResponseDto> getOrderPages(Pageable pageable) {
        return orderAdaptor.getOrderPages(pageable);
    }

    /**
     * {@inheritDoc}
     *
     * @param orderId 주문 번호
     * @return 주문 상세 내역
     */
    @Override
    public BookOrderViewResponseDto getOrderInfo(Long orderId) {
        return orderAdaptor.getOrderInfo(orderId);
    }

    /**
     * {@inheritDoc}
     *
     * @param orderDetailInfoList 주문 상세 목록
     * @return 쿠폰 할인 가격
     */
    @Override
    public List<Long> getCouponDiscountPrice(List<OrderDetailInfoResponseDto> orderDetailInfoList) {
        return orderDetailInfoList.stream()
            .map(orderDetail -> {
                if (Objects.isNull(orderDetail.getCouponName())) {
                    return 0L;
                }

                if (CouponType.MONEY.name().equals(orderDetail.getCouponTypeName())) {
                    return orderDetail.getDiscountPrice();
                }

                long discountPrice = Math.round(
                    (double) orderDetail.getBookPrice() * orderDetail.getAmount()
                        * orderDetail.getDiscountPrice() / 100);

                return discountPrice >= orderDetail.getMaxDiscountPrice()
                    ? orderDetail.getMaxDiscountPrice() : discountPrice;
            }).collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     *
     * @param orderId 주문 번호
     */
    @Override
    public void updateOrderStatus(Long orderId) {
        orderAdaptor.updateOrderStatus(orderId);
    }

    /**
     * {@inheritDoc}
     *
     * @param pageable 페이지 정보
     * @param memberId 회원 번호
     * @return 회원 주문 목록
     */
    @Override
    public PageRequestDto<OrderReadResponseDto> getMyOrderPages(Pageable pageable, Long memberId) {
        return orderAdaptor.getMyOrderPages(pageable, memberId);
    }

    /**
     * {@inheritDoc}
     *
     * @param orderCode 주문 코드
     * @param memberId  회원 번호
     * @return 회원 주문 상세 내역
     */
    @Override
    public BookOrderViewResponseDto getMyOrderInfo(String orderCode, Long memberId) {
        return orderAdaptor.getMyOrderInfo(orderCode, memberId);
    }

    /**
     * 도서 정보에서 bookId에 맞는 amount를 찾는 메소드입니다.
     *
     * @param bookInfo 도서 정보
     * @param bookId   도서번호
     * @return 수량
     */
    private Integer getAmountForBook(Set<OrderBookInfoReadResponseDto> bookInfo, String bookId) {
        return bookInfo.stream()
            .filter(info -> info.getBookId().equals(bookId))
            .map(OrderBookInfoReadResponseDto::getAmount)
            .findFirst()
            .orElse(0);
    }

}

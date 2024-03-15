package com.nhnacademy.inkbridge.front.service.impl;

import com.nhnacademy.inkbridge.front.adaptor.OrderAdaptor;
import com.nhnacademy.inkbridge.front.dto.order.OrderBookInfoReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.order.OrderBookReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.order.OrderCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.order.OrderCreateResponseDto;
import com.nhnacademy.inkbridge.front.dto.order.OrderPaymentInfoReadResponseDto;
import com.nhnacademy.inkbridge.front.service.CartService;
import com.nhnacademy.inkbridge.front.service.OrderService;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
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

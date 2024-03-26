package com.nhnacademy.inkbridge.front.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.inkbridge.front.dto.order.OrderBookInfoReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.order.OrderBookReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.order.OrderCreateRequestDto;
import com.nhnacademy.inkbridge.front.service.AccumulationRatePolicyService;
import com.nhnacademy.inkbridge.front.service.AddressService;
import com.nhnacademy.inkbridge.front.service.CartService;
import com.nhnacademy.inkbridge.front.service.CouponService;
import com.nhnacademy.inkbridge.front.service.DeliveryPolicyService;
import com.nhnacademy.inkbridge.front.service.MemberService;
import com.nhnacademy.inkbridge.front.service.OrderService;
import com.nhnacademy.inkbridge.front.service.WrappingService;
import com.nhnacademy.inkbridge.front.utils.CommonUtils;
import com.nhnacademy.inkbridge.front.utils.CookieUtils;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * class: OrderController.
 *
 * @author jangjaehun
 * @version 2024/02/26
 */
@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;
    private final AccumulationRatePolicyService accumulationRatePolicyService;
    private final DeliveryPolicyService deliveryPolicyService;
    private final WrappingService wrappingService;
    private final ObjectMapper objectMapper;
    private final CouponService couponService;
    private final AddressService addressService;
    private final MemberService memberService;
    private final CartService cartService;

    /**
     * 주문서 작성 페이지 호출 요청을 처리하는 메소드입니다.
     *
     * @param model Model
     * @return 주문서 작성 페이지, Cookie에 도서 정보가 존재하지 않는다면 메인페이지로 이동
     */
    @GetMapping
    public String orderView(Model model,
        @CookieValue(value = "info", required = false) String booksInfo,
        HttpServletResponse response) {

        if (Objects.isNull(booksInfo)) {
            return "redirect:/";
        }

        Long memberId = CommonUtils.getMemberId();

        List<OrderBookReadResponseDto> orderBooks = orderService.getOrderBooks(
            getOrderBookReadResponseDtos(booksInfo));

        CookieUtils.deleteCookie(response, "info");

        model.addAttribute("orderBooks", orderBooks);
        model.addAttribute("deliveryPolicy", deliveryPolicyService.getCurrentPolicy());
        model.addAttribute("accumulationRatePolicy",
            accumulationRatePolicyService.getCurrentPolicy());
        model.addAttribute("wrappingList", wrappingService.getWrappingList(true));
        model.addAttribute("memberId", memberId);

        if (Objects.nonNull(memberId)) {
            model.addAttribute("couponList", couponService.getOrderCoupons(memberId,
                orderBooks.stream().map(book -> book.getBookId().toString())
                    .collect(Collectors.toList())));
//            주소록 가져옴
//            멤버 포인트 가져오기
            model.addAttribute("addressList", addressService.getAddresses());
            model.addAttribute("userPoint", memberService.getPoint());
        }
        return "order/orders";
    }

    /**
     * 주문 요청을 처리하고 결제 페이지로 이동하는 메소드입니다.
     *
     * @param requestDto 주문 정보
     * @return 결제 페이지
     */
    @PostMapping
    public String createOrder(@ModelAttribute OrderCreateRequestDto requestDto) {
        String orderCode = orderService.createOrder(requestDto);

        return "redirect:/pays?order-code=" + orderCode;
    }

    /**
     * JSON 형태의 도서 정보 문자열을 객체로 변환하는 메소드입니다.
     *
     * @param booksInfo 도서 정보 문자열
     * @return OrderBookReadResponseDto 객체
     */
    private Set<OrderBookInfoReadResponseDto> getOrderBookReadResponseDtos(String booksInfo) {
        try {
            return objectMapper.readValue(booksInfo,
                new TypeReference<>() {
                });
        } catch (JsonProcessingException ignore) {
            return Collections.emptySet();
        }
    }

}

package com.nhnacademy.inkbridge.front.controller;

import static com.nhnacademy.inkbridge.front.utils.CommonUtils.getMemberId;

import com.nhnacademy.inkbridge.front.dto.PageRequestDto;
import com.nhnacademy.inkbridge.front.dto.order.BookOrderViewResponseDto;
import com.nhnacademy.inkbridge.front.dto.order.OrderReadResponseDto;
import com.nhnacademy.inkbridge.front.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * class: OrderMemberController.
 *
 * @author jangjaehun
 * @version 2024/03/20
 */
@Controller
@RequestMapping("/mypage/orders")
@RequiredArgsConstructor
public class OrderMemberController {


    private final OrderService orderService;

    /**
     * 관리자 주문 목록 조회 페이지를 호출하는 메소드입니다.
     *
     * @param pageable 페이지 정보
     * @param model    Model
     * @return 관리자 주문 목록 조회 페이지
     */
    @GetMapping
    public String adminOrderList(@PageableDefault Pageable pageable, Model model) {

        PageRequestDto<OrderReadResponseDto> orderPages = orderService.getMyOrderPages(pageable,
            getMemberId());

        model.addAttribute("orderList", orderPages);
        return "member/orders_list";
    }

    /**
     * 관리자 주문 상세 조회 페이지를 호출하는 메소드입니다.
     *
     * @param orderCode 주문 코드
     * @param model     Model
     * @return 관리자 주문 상세 조회 페이지
     */
    @GetMapping("/{order-code}")
    public String adminOrderDetailView(@PathVariable("order-code") String orderCode, Model model) {

        BookOrderViewResponseDto response = orderService.getMyOrderInfo(orderCode, getMemberId());

        model.addAttribute("order", response);
        model.addAttribute("couponDiscountList",
            orderService.getCouponDiscountPrice(response.getOrderDetailInfoList()));

        return "member/orders_detail";
    }

}

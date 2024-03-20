package com.nhnacademy.inkbridge.front.controller;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * class: OrderAdminController.
 *
 * @author jangjaehun
 * @version 2024/03/19
 */
@Controller
@RequestMapping("/admin/orders")
@RequiredArgsConstructor
public class OrderAdminController {

    private final OrderService orderService;

    /**
     * 관리자 주문 목록 조회 페이지를 호출하는 메소드입니다.
     *
     * @param pageable 페이지 정보
     * @param model Model
     * @return 관리자 주문 목록 조회 페이지
     */
    @GetMapping
    public String adminOrderList(@PageableDefault Pageable pageable, Model model) {

        PageRequestDto<OrderReadResponseDto> orderPages = orderService.getOrderPages(pageable);

        model.addAttribute("orderList", orderPages);
        return "admin/orders_list";
    }

    /**
     * 관리자 주문 상세 조회 페이지를 호출하는 메소드입니다.
     *
     * @param orderId 주문 번호
     * @param model Model
     * @return 관리자 주문 상세 조회 페이지
     */
    @GetMapping("/{order-id}")
    public String adminOrderDetailView(@PathVariable("order-id") Long orderId, Model model) {

        BookOrderViewResponseDto response = orderService.getOrderInfo(orderId);

        model.addAttribute("order", response);
        model.addAttribute("couponDiscountList", orderService.getCouponDiscountPrice(response.getOrderDetailInfoList()));

        return "admin/orders_detail";
    }

    @PutMapping("/{order-id}")
    public String updateOrderStatus(@PathVariable("order-id") Long orderId) {

        orderService.updateOrderStatus(orderId);

        return "redirect:/admin/orders/" + orderId;
    }


}

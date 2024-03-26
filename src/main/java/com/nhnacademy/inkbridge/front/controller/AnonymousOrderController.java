package com.nhnacademy.inkbridge.front.controller;

import static com.nhnacademy.inkbridge.front.utils.CommonUtils.getMemberId;

import com.nhnacademy.inkbridge.front.dto.order.BookOrderViewResponseDto;
import com.nhnacademy.inkbridge.front.service.OrderService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * class: AnonymousOrderController.
 *
 * @author jangjaehun
 * @version 2024/03/24
 */
@Controller
@RequestMapping("/anonymous-orders")
@RequiredArgsConstructor
@Slf4j
public class AnonymousOrderController {

    private final OrderService orderService;

    @GetMapping
    public String orderFindView() {
        if (Objects.nonNull(getMemberId())) {
            return "redirect:/";
        }

        return "order/anonymous-find";
    }

    @GetMapping("/{orderCode}")
    public String orderDetailView(@PathVariable("orderCode") String orderCode, Model model) {

        BookOrderViewResponseDto response = orderService.getOrderInfoByOrderCode(orderCode);

        log.debug("anonymous order view start");

        model.addAttribute("order", response);

        return "order/orders_detail";
    }
}

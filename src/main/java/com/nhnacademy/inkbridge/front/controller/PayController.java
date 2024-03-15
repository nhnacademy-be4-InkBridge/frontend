package com.nhnacademy.inkbridge.front.controller;

import com.nhnacademy.inkbridge.front.property.TossProperties;
import com.nhnacademy.inkbridge.front.service.OrderService;
import com.nhnacademy.inkbridge.front.utils.CommonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * class: PayController.
 *
 * @author jangjaehun
 * @version 2024/03/06
 */
@Controller
@RequestMapping("/pays")
@Slf4j
@RequiredArgsConstructor
public class PayController {

    private final OrderService orderService;
    private final TossProperties tossProperties;

    /**
     * 결제 페이지를 호출합니다.
     *
     * @param model 결제 정보
     * @return 결제 페이지
     */
    @GetMapping
    public String payView(Model model, @RequestParam("order-code") String orderCode) {
        Long memberId = CommonUtils.getMemberId();

        model.addAttribute("payInfo", orderService.getOrderPaymentInfo(orderCode));
        model.addAttribute("memberId", memberId);
        String clientKey = tossProperties.getClientKey();
        model.addAttribute("clientKey", clientKey);

        return "order/pays";
    }

    /**
     * 결제 정보를 저장하고 결제 승인 페이지를 호출합니다.
     *
     * @return 결제 성공 페이지
     */
    @GetMapping("/success")
    public String paymentRequest(@RequestParam("paymentKey") String paymentKey,
        @RequestParam("orderId") String orderId, @RequestParam("amount") Long amount) {

        return "order/pay_success";
    }

    /**
     * 결제 실패 페이지를 호출합니다.
     *
     * @return 결제 실패 페이지
     */
    @GetMapping("/fail")
    public String failPayment() {

        return "order/pay_fail";
    }
}

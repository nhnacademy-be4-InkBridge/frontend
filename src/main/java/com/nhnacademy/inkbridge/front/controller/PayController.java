package com.nhnacademy.inkbridge.front.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
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
public class PayController {

    /**
     * 결제 페이지를 호출합니다.
     *
     * @param model 결제 정보
     * @return 결제 페이지
     */
    @GetMapping
    public String payView(Model model, @RequestParam("order-id") String orderId) {
        log.info("pay pay pay pay");

        String memberId = (String) SecurityContextHolder.getContext().getAuthentication()
            .getPrincipal();

//        model.addAttribute("orderInfo", model.getAttribute("orderInfo"));
//        model.addAttribute("orderInfo", responseDto);
        model.addAttribute("memberId", memberId);
        model.addAttribute("clientKey", "test_ck_yL0qZ4G1VO52EWQzLjbO8oWb2MQY");

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

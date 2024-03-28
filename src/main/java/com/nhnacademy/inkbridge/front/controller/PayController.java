package com.nhnacademy.inkbridge.front.controller;

import static com.nhnacademy.inkbridge.front.utils.CommonUtils.getMemberId;

import com.nhnacademy.inkbridge.front.dto.pay.PayCancelInfoDto;
import com.nhnacademy.inkbridge.front.dto.pay.PayConfirmRequestDto;
import com.nhnacademy.inkbridge.front.property.TossProperties;
import com.nhnacademy.inkbridge.front.service.OrderService;
import com.nhnacademy.inkbridge.front.service.PayService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
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
    private final PayService payService;

    /**
     * 결제 페이지를 호출합니다.
     *
     * @param model 결제 정보
     * @return 결제 페이지
     */
    @GetMapping
    public String payView(Model model, @RequestParam("order-code") String orderCode) {
        Long memberId = getMemberId();

        model.addAttribute("payInfo", orderService.getOrderPaymentInfo(orderCode));
        model.addAttribute("memberId", memberId);
        String clientKey = tossProperties.getClientKey();
        model.addAttribute("clientKey", clientKey);

        return "order/pays";
    }

    /**
     * 결제를 취소합니다.
     * <p>
     * 주문 상세 내역 페이지
     */
    @PutMapping("/{paymentKey}")
    public String cancelPay(@ModelAttribute PayCancelInfoDto requestDto,
        @PathVariable("paymentKey") String paymentKey, @RequestParam("provider") String provider, @RequestParam("order-code") String orderCode) {

        payService.doCancel(paymentKey, requestDto, provider);

        return Objects.nonNull(getMemberId()) ? "redirect:/mypage/orders/"
            + orderCode : "redirect:/anonymous-orders/" + orderCode;
    }

    /**
     * 결제 승인 요청을 보내고 결제 내용을 반영 한 후 결제 성공 페이지를 호출합니다.
     *
     * @return 결제 성공 페이지
     */
    @GetMapping("/success")
    public String paymentRequest(@ModelAttribute PayConfirmRequestDto requestDto) {
        payService.doPayment(requestDto, "toss");

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

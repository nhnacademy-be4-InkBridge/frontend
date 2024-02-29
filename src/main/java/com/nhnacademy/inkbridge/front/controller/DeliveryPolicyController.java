package com.nhnacademy.inkbridge.front.controller;

import com.nhnacademy.inkbridge.front.dto.deliverypolicy.DeliveryPolicyCreateRequestDto;
import com.nhnacademy.inkbridge.front.service.DeliveryPolicyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * class: DeliveryPolicyController.
 *
 * @author jangjaehun
 * @version 2024/02/25
 */
@Controller
@RequestMapping("/admin/delivery-policy")
@RequiredArgsConstructor
public class DeliveryPolicyController {


    private final DeliveryPolicyService deliveryPolicyService;

    /**
     * 배송비 정책 관리 페이지 호출 요청을 처리하는 메소드입니다.
     *
     * @param model Model
     * @return 배송비 정책 관리 페이지
     */
    @GetMapping
    public String deliveryPolicyView(Model model) {
        model.addAttribute("currentPolicy", deliveryPolicyService.getCurrentPolicy());
        model.addAttribute("deliveryPolicies", deliveryPolicyService.getDeliveryPolicies());
        return "admin/delivery_policies";
    }

    /**
     * 배송비 정책 생성 요청을 처리하는 메소드입니다.
     *
     * @param requestDto DeliveryPolicyCreateRequestDto
     * @return 배송비 정책 관리 페이지
     */
    @PostMapping
    public String createDeliveryPolicy(DeliveryPolicyCreateRequestDto requestDto) {
        deliveryPolicyService.createDeliceryPolicy(requestDto);
        return "redirect:/admin/delivery-policy";
    }
}

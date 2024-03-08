package com.nhnacademy.inkbridge.front.controller;

import com.nhnacademy.inkbridge.front.dto.accumulationratepolicy.AccumulationRatePolicyCreateRequestDto;
import com.nhnacademy.inkbridge.front.service.AccumulationRatePolicyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * class: AccumulationRatePolicyController.
 *
 * @author jangjaehun
 * @version 2024/02/25
 */
@Controller
@RequestMapping("/admin/accumulation-policy")
@RequiredArgsConstructor
public class AccumulationRatePolicyAdminController {

    private final AccumulationRatePolicyService accumulationRatePolicyService;

    /**
     * 적립률 정책 관리 페이지 호출 요청을 처리하는 메소드입니다.
     *
     * @param model Model
     * @return 적립률 정책 관리 페이지
     */
    @GetMapping
    public String accumulationRatePolicyView(Model model) {

        model.addAttribute("currentPolicy", accumulationRatePolicyService.getCurrentPolicy());
        model.addAttribute("accumulationRatePolicies",
            accumulationRatePolicyService.getAccumulationRatePolicies());

        return "admin/accumulation_policies";
    }

    /**
     * 적립률 정책 생성 요청을 처리하는 메소드입니다.
     *
     * @param requestDto AccumulationRatePolicyCreateRequestDto
     * @return 적립률 정책 관리 페이지
     */
    @PostMapping
    public String createAccumulationPolicy(AccumulationRatePolicyCreateRequestDto requestDto) {

        accumulationRatePolicyService.createAccumulationRatePolicy(requestDto);
        return "redirect:/admin/accumulation-policy";
    }

}

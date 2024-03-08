package com.nhnacademy.inkbridge.front.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping
    public String payView(Model model) {
        String memberId = (String) SecurityContextHolder.getContext().getAuthentication()
            .getPrincipal();

        model.addAttribute("orderInfo", model.getAttribute("orderInfo"));
        model.addAttribute("memberId", memberId);
        model.addAttribute("clientKey", "test_ck_yL0qZ4G1VO52EWQzLjbO8oWb2MQY");

        return "order/pays";
    }
}

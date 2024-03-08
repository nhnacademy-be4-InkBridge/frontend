package com.nhnacademy.inkbridge.front.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * class: CartController.
 *
 * @author minm063
 * @version 2024/03/03
 */
@Controller
@RequestMapping("/cart")
public class CartController {

    @GetMapping
    public String getCart() {
        return "member/cart";
    }
}

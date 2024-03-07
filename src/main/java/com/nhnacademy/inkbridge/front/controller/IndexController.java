package com.nhnacademy.inkbridge.front.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * class: IndexController.
 *
 * @author jangjaehun
 * @version 2024/02/14
 */
@Controller
@RequestMapping("/")
@Slf4j
public class IndexController {

    @GetMapping
    public String index() {
        return "member/index";
    }
}

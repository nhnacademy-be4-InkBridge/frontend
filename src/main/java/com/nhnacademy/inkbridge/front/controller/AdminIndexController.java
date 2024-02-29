package com.nhnacademy.inkbridge.front.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * class: AdminController.
 *
 * @author minm063
 * @version 2024/02/19
 */
@Controller
@RequestMapping("/admin")
public class AdminIndexController {

    @GetMapping
    public String admin() {
        return "admin/charts";
    }

}

package com.nhnacademy.inkbridge.front.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * class: AdminController.
 *
 * @author minm063
 * @version 2024/02/19
 */
@Controller
public class AdminIndexController {

    @GetMapping("/admin")
    public String admin() {
        return "admin/charts";
    }

}

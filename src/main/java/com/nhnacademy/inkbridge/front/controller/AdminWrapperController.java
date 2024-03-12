package com.nhnacademy.inkbridge.front.controller;

import com.nhnacademy.inkbridge.front.service.WrappingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * class: AdminWrapperController.
 *
 * @author JBum
 * @version 2024/03/12
 */
@Controller
@RequestMapping("/admin/wrappings")
public class AdminWrapperController {

    private final WrappingService wrappingService;

    public AdminWrapperController(WrappingService wrappingService) {
        this.wrappingService = wrappingService;
    }

    @GetMapping
    public String getWrappings(
        @RequestParam(value = "is_active", defaultValue = "true") boolean isActive, Model model) {
        model.addAttribute("wrappings", wrappingService.getWrappingList(isActive));

        return "admin/wrapping_list";
    }
}

package com.nhnacademy.inkbridge.front.controller;

import com.nhnacademy.inkbridge.front.dto.wrapping.WrappingCreateRequestDto;
import com.nhnacademy.inkbridge.front.service.WrappingService;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/update-view/{wrappingId}")
    public String showUpdateForm(@PathVariable("wrappingId") Long wrappingId, Model model) {
        model.addAttribute("wrapping", wrappingService.getWrapping(wrappingId));
        return "admin/wrapping_create";
    }

    @GetMapping("/create-view")
    public String showCreateForm() {
        return "admin/wrapping_create";
    }

    @PostMapping("/register")
    public String registerWrapping(
        @Valid @ModelAttribute WrappingCreateRequestDto wrappingCreateRequestDto) {
        wrappingService.registerWrapping(wrappingCreateRequestDto);
        return "redirect:/admin/wrappings";
    }

    @PostMapping("/update/{wrappingId}")
    public String updateWrapping(@PathVariable("wrappingId") Long wrappingId,
        @ModelAttribute WrappingCreateRequestDto wrappingCreateRequestDto) {
        wrappingService.updateWrapping(wrappingId, wrappingCreateRequestDto);
        return "redirect:/admin/wrappings";
    }
}

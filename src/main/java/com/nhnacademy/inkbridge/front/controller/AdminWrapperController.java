package com.nhnacademy.inkbridge.front.controller;

import com.nhnacademy.inkbridge.front.dto.wrapping.WrappingCreateRequestDto;
import com.nhnacademy.inkbridge.front.service.WrappingService;
import javax.validation.Valid;
import javax.validation.ValidationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    /**
     * 포장지 리스트 관리를 위한 리스트 가져오기.
     *
     * @param isActive 활성 여부
     * @param model    htmlForm으로 넘길 데이터
     * @return 포장지 리스트 관리 페이지
     */
    @GetMapping
    public String getWrappings(
        @RequestParam(value = "is_active", defaultValue = "true") boolean isActive, Model model) {
        model.addAttribute("wrappings", wrappingService.getWrappingList(isActive));
        return "admin/wrapping_list";
    }

    /**
     * 포장지 업데이트를 위한 Form을 부르는 메소드.
     *
     * @param wrappingId 업데이트 할 포장지
     * @param model      포장지 정보
     * @return 포장지 업데이트 페이지
     */
    @GetMapping("/update-view/{wrappingId}")
    public String showUpdateForm(@PathVariable("wrappingId") Long wrappingId, Model model) {
        model.addAttribute("wrapping", wrappingService.getWrapping(wrappingId));
        return "admin/wrapping_create";
    }

    /**
     * 포장지 생성을 위한 Form을 부르는 메소드.
     *
     * @return 포장지 생성 페이지
     */
    @GetMapping("/create-view")
    public String showCreateForm() {
        return "admin/wrapping_create";
    }

    /**
     * 포장지 생성.
     *
     * @param wrappingCreateRequestDto 포장지 데이터
     * @param bindingResult            validation
     * @return 생성 후 포장지 리스트로 이동
     */
    @PostMapping("/register")
    public String registerWrapping(
        @Valid @ModelAttribute WrappingCreateRequestDto wrappingCreateRequestDto,
        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getFieldError().getDefaultMessage());
        }
        wrappingService.registerWrapping(wrappingCreateRequestDto);
        return "redirect:/admin/wrappings";
    }

    /**
     * 포장지 업데이트.
     *
     * @param wrappingId               업데이트할 포장지
     * @param wrappingCreateRequestDto 업데이트할 내용
     * @param bindingResult            validation
     * @return 생성 후 포장지 리스트로 이동
     */
    @PostMapping("/update/{wrappingId}")
    public String updateWrapping(@PathVariable("wrappingId") Long wrappingId,
        @ModelAttribute WrappingCreateRequestDto wrappingCreateRequestDto,
        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getFieldError().getDefaultMessage());
        }
        wrappingService.updateWrapping(wrappingId, wrappingCreateRequestDto);
        return "redirect:/admin/wrappings";
    }
}

package com.nhnacademy.inkbridge.front.controller;

import com.nhnacademy.inkbridge.front.dto.category.CategoryCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.category.CategoryReadResponseDto;
import com.nhnacademy.inkbridge.front.service.CategoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * class: CategoryController.
 *
 * @author choijaehun
 * @version 2024/02/24
 */

@Controller
@RequestMapping("/admin/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;


    /**
     * 카테고리를 생성하는 메소드입니다.
     *
     * @param requestDto CategoryCreateRequestDto
     * @return redirect - 카테고리 목록 페이지
     */
    @PostMapping
    public String createCategory(@ModelAttribute CategoryCreateRequestDto requestDto){
        categoryService.createCategory(requestDto);
        return "redirect:/admin/category";
    }


    /**
     *
     * @return
     */
    @GetMapping
    public String readCategories(Model model){
        List< CategoryReadResponseDto> categories =categoryService.readCategory();
        return null;
    }
}

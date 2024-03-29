package com.nhnacademy.inkbridge.front.controller;

import com.nhnacademy.inkbridge.front.dto.bookCategory.BookCategoryReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.category.CategoryCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.category.CategoryUpdateRequestDto;
import com.nhnacademy.inkbridge.front.dto.category.ParentCategoryReadResponseDto;
import com.nhnacademy.inkbridge.front.service.BookCategoryService;
import com.nhnacademy.inkbridge.front.service.CategoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * class: CategoryController.
 *
 * @author choijaehun
 * @version 2024/02/24
 */

@Controller
@RequestMapping("/admin/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final BookCategoryService bookCategoryService;

    /**
     * 카테고리를 생성하는 메소드입니다.
     *
     * @param requestDto CategoryCreateRequestDto
     * @return redirect - 카테고리 목록 페이지
     */
    @PostMapping
    public String createCategory(@ModelAttribute CategoryCreateRequestDto requestDto) {
        categoryService.createCategory(requestDto);
        return "redirect:/admin/categories";
    }

    /**
     * 카테고리 목록을 보여주는 메소드입니다.
     *
     * @param model 모든 카테고리를 전달할 model
     * @return 카테고리 목록 페이지
     */
    @GetMapping
    public String readCategories(Model model) {
        List<ParentCategoryReadResponseDto> parentCategories = categoryService.readCategory();
        model.addAttribute("parentCategories", parentCategories);
        return "admin/categories";
    }

    /**
     * 카테고리 수정하는 메소드입니다.
     *
     * @param categoryId Long
     * @param requestDto CategoryUpdateRequestDto
     * @return redirect - 카테고리 목록 페이지
     */
    @PutMapping("/{categoryId}")
    public String updateCategory(@PathVariable Long categoryId, @ModelAttribute
    CategoryUpdateRequestDto requestDto) {
        categoryService.updateCategory(categoryId, requestDto);
        return "redirect:/admin/categories";
    }

    /**
     * 첵이 속한 카테고리를 반환해주는 메소드입니다.
     *
     * @param bookId 책 번호
     * @param model  책이 속한 카테고리들의 dtoList가 담긴 model
     * @return
     */
    @GetMapping("/{bookId}")
    public String readBookCategories(@PathVariable Long bookId, Model model) {
        List<BookCategoryReadResponseDto> bookCategories = bookCategoryService.readBookCategories(
            bookId);
        model.addAttribute("bookCategories", bookCategories);
        return null; //view 경로 작성해주세요. javadoc return도 부탁드려요. 하고 inheritdoc으로 바꿀게요
    }
}

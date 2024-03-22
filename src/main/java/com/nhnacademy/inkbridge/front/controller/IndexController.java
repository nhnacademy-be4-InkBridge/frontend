package com.nhnacademy.inkbridge.front.controller;


import com.nhnacademy.inkbridge.front.dto.book.BookReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.book.BooksByCategoryReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.book.BooksReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.category.ParentCategoryReadResponseDto;
import com.nhnacademy.inkbridge.front.service.CategoryService;
import com.nhnacademy.inkbridge.front.service.IndexService;
import com.nhnacademy.inkbridge.front.utils.CommonUtils;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * class: IndexController.
 *
 * @author jangjaehun
 * @version 2024/02/14
 * @modifiedBy minm063
 * @modifiedAt 2024/03/02
 * @modificationReason - index 메서드 수정, bookDetail 메서드 추가
 */
@Controller
@RequiredArgsConstructor
@Slf4j
public class IndexController {

    private final IndexService indexService;
    private final CategoryService categoryService;
    private static final Long NOT_MEMBER = 0L;

    /**
     * 메인 페이지를 조회하는 메서드입니다.
     *
     * @param model Model
     * @param page  Long
     * @return html
     */
    @GetMapping
    public String index(Model model, @RequestParam(defaultValue = "0") Long page) {
        BooksReadResponseDto books = indexService.getBooks(page);
        model.addAttribute("books", books.getBooksPaginationReadResponseDtos());
        model.addAttribute("authors", books.getAuthorPaginationReadResponseDto());
        log.info("context -> {}", SecurityContextHolder.getContext().getAuthentication());

        List<ParentCategoryReadResponseDto> parentCategories = categoryService.readCategory();
        model.addAttribute("parentCategories", parentCategories);
        return "member/index";
    }

    /**
     * 도서 상세 정보를 조회하는 메서드입니다.
     *
     * @param model  Model
     * @param bookId Long
     * @return html
     */
    @GetMapping("/book")
    public String bookDetail(Model model, @RequestParam(name = "id") Long bookId) {
        Long memberId = CommonUtils.getMemberId();
        memberId = Objects.equals(null, memberId) ? NOT_MEMBER : memberId;
        BookReadResponseDto book = indexService.getBook(bookId, memberId);
        model.addAttribute("bookId", bookId);
        model.addAttribute("book", book);
        return "member/book";
    }

    /**
     * 카테고리에 따라 도서 목록을 조회하는 메서드입니다.
     *
     * @param model      Model
     * @param categoryId Long
     * @param page       Long
     * @return html
     */
    @GetMapping("/books/{categoryId}")
    public String indexByCategory(Model model, @PathVariable Long categoryId,
        @RequestParam(defaultValue = "0") Long page) {
        BooksByCategoryReadResponseDto booksByCategory = indexService.getBooksByCategory(page,
            categoryId);
        model.addAttribute("books", booksByCategory.getBooksPaginationReadResponseDtos());
        model.addAttribute("authors", booksByCategory.getAuthorPaginationReadResponseDto());
        model.addAttribute("category", booksByCategory.getCategoryNameReadResponseDto());

        List<ParentCategoryReadResponseDto> parentCategories = categoryService.readCategory();
        model.addAttribute("parentCategories", parentCategories);

        return "member/index";
    }
}

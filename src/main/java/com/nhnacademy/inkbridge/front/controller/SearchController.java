package com.nhnacademy.inkbridge.front.controller;

import com.nhnacademy.inkbridge.front.dto.PageRequestDto;
import com.nhnacademy.inkbridge.front.dto.search.BookSearchResponseDto;
import com.nhnacademy.inkbridge.front.service.SearchService;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * class: SearchController.
 *
 * @author choijaehun
 * @version 2024/03/15
 */

@Controller
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    /**
     * 키워드로 검색하는 메소드
     *
     * @param text     키워드
     * @param pageable 페이징처리
     * @param model    모델
     * @return 경로
     */
    @GetMapping("search")
    public String searchByText(@RequestParam String text,
        @PageableDefault(size = 10) Pageable pageable, Model model) {
        PageRequestDto<BookSearchResponseDto> pageableBooks = searchService.searchByText(text,
            pageable);
        String sort = pageable.getSort().toString();
        sort = "UNSORTED".equals(sort) ? "" : sort.split(": ")[1];
        model.addAttribute("books", pageableBooks);
        model.addAttribute("text", text);
        model.addAttribute("sort", sort);
        model.addAttribute("count", pageableBooks.getTotalElements());
        model.addAttribute("isSearch", true);
        return "search/search";
    }

    /**
     * 필드에 해당하는 도서 조회하는 메소드
     *
     * @param field    필드명
     * @param pageable 페이징처리
     * @param model    모델
     * @param request  request
     * @return 경로
     */
    @GetMapping("/{field}")
    public String searchByAll(@PathVariable String field,
        @PageableDefault(size = 10) Pageable pageable, Model model,
        HttpServletRequest request) {
        if (field.equals("popular-books")) {
            field = "view";
        } else if (field.equals("new-books")) {
            field = "publicatedAt";
        } else {
            throw new IllegalArgumentException();
        }
//        String sort = pageable.getSort().toString();
//        sort = "UNSORTED".equals(sort) ? "" : sort.split(": ")[1];
        PageRequestDto<BookSearchResponseDto> pageableBooks = searchService.searchByAll(field,
            pageable);

        model.addAttribute("currentURI", request.getRequestURI());
        model.addAttribute("books", pageableBooks);
//        model.addAttribute("sort", sort);
        model.addAttribute("isSearch", false);
        return "search/search";
    }

    /**
     * 카테고리명으로 도서 조회하는 메소드
     *
     * @param model    모델
     * @param category 카테고리명
     * @param pageable 페이징 처리
     * @return 경로
     */
    @GetMapping("/categories/{category}/books")
    public String booksByCategory(Model model, @PathVariable String category,
        @PageableDefault(size = 10) Pageable pageable) {
        PageRequestDto<BookSearchResponseDto> pageableBooks = searchService.readByCategory(category,
            pageable);
        String sort = pageable.getSort().toString();
        sort = "UNSORTED".equals(sort) ? "" : sort.split(": ")[1];
        model.addAttribute("books", pageableBooks);
        model.addAttribute("sort", sort);
        model.addAttribute("isCategory", true);
        return "search/search";
    }
}


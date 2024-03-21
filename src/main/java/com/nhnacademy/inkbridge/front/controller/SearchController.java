package com.nhnacademy.inkbridge.front.controller;

import com.nhnacademy.inkbridge.front.dto.PageRequestDto;
import com.nhnacademy.inkbridge.front.dto.search.BookSearchPageResponseDto;
import com.nhnacademy.inkbridge.front.dto.search.BookSearchResponseDto;
import com.nhnacademy.inkbridge.front.service.SearchService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping("search")
    public String searchByText(@RequestParam String text, @PageableDefault(size=10) Pageable pageable, Model model) {
        PageRequestDto<BookSearchResponseDto> pageableBooks = searchService.searchByText(text, pageable);
        model.addAttribute("books", pageableBooks);
        model.addAttribute("text",text);
        model.addAttribute("count",pageableBooks.getTotalElements());
        model.addAttribute("isSearch",true);
        return "search/search";
    }

    @GetMapping("/{field}")
    public String searchByAll(@PathVariable String field, Pageable pageable, Model model){
        if(field.equals("popular-books")){
            field = "view";
        }else if(field.equals("new-books")){
            field = "publicatedAt";
        } else{
            throw new IllegalArgumentException();
        }
        PageRequestDto<BookSearchResponseDto> pageableBooks = searchService.searchByAll(field,pageable);
        model.addAttribute("books",pageableBooks);
        model.addAttribute("isSearch",false);
        return "search/search";
    }
}


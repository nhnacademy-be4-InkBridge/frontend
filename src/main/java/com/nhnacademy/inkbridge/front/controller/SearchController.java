package com.nhnacademy.inkbridge.front.controller;

import com.nhnacademy.inkbridge.front.dto.search.BookSearchResponseDto;
import com.nhnacademy.inkbridge.front.service.SearchService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/search")
public class SearchController {

    private final SearchService searchService;

    @GetMapping
    public String searchByText(@RequestParam String text, Pageable pageable, Model model) {
        List<BookSearchResponseDto> books = searchService.searchByText(text, pageable);
        model.addAttribute("books", books);
        model.addAttribute("text",text);
        model.addAttribute("count",books.size());
        return "search/search";
    }
}


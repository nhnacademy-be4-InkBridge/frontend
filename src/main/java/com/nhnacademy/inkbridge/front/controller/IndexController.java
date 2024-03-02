package com.nhnacademy.inkbridge.front.controller;

import com.nhnacademy.inkbridge.front.dto.book.BookReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.book.BooksReadResponseDto;
import com.nhnacademy.inkbridge.front.service.IndexService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * class: IndexController.
 *
 * @author jangjaehun
 * @version 2024/02/14
 */
@Controller
@RequestMapping("/")
public class IndexController {

    private final IndexService indexService;

    public IndexController(IndexService indexService) {
        this.indexService = indexService;
    }

    @GetMapping
    public String index(Model model) {
        List<BooksReadResponseDto> books = indexService.getBooks();
        model.addAttribute("books", books);
        return "member/index";
    }

    @GetMapping("book")
    public String bookDetail(Model model, @RequestParam(name = "id") Long bookId) {
        BookReadResponseDto book = indexService.getBook(bookId);
        model.addAttribute("book", book);
        return "member/book";
    }
}

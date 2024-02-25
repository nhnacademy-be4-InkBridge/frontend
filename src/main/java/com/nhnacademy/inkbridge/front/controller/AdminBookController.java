package com.nhnacademy.inkbridge.front.controller;

import com.nhnacademy.inkbridge.front.dto.BookAdminReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.BookAdminRequestDto;
import com.nhnacademy.inkbridge.front.dto.BooksAdminReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.TagResponse;
import com.nhnacademy.inkbridge.front.service.BookService;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * class: AdminBookController.
 *
 * @author minm063
 * @version 2024/02/22
 */
@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminBookController {

    private final BookService bookService;

    public AdminBookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public String getBooks(Model model) {
        List<BooksAdminReadResponseDto> books = bookService.getBooks();
        model.addAttribute("books", books);
        return "admin/books";
    }

    // 상세, 수정까지
    @GetMapping("/book/{bookId}")
    public String getBook(Model model, @PathVariable Long bookId) {
        BookAdminReadResponseDto book = bookService.getBook(bookId);
        model.addAttribute("book", book);
        model.addAttribute("tags",
            List.of(TagResponse.builder().tagId(1L).tagName("이달의 도서").build(),
                TagResponse.builder().tagId(2L).tagName("test2").build(),
                TagResponse.builder().tagId(3L).tagName("test3").build()));
        model.addAttribute("savedTags", List.of(TagResponse.builder().tagId(2L).build(), TagResponse.builder().tagId(3L).build()));
        model.addAttribute("now", LocalDate.now());
        return "admin/book_form_value";
    }

    @GetMapping("/book/create")
    public String createBook(Model model) {
        model.addAttribute("tags",
            List.of(TagResponse.builder().tagId(1L).tagName("이달의 도서").build(),
                TagResponse.builder().tagId(2L).tagName("test2").build()));
        model.addAttribute("now", LocalDate.now());
        return "admin/book_form";
    }

    @PostMapping("/book/create")
    public String createBook(@RequestParam("thumbnail") MultipartFile thumbnail,
        @RequestParam(value = "bookImages", required = false) MultipartFile[] bookImages,
        @ModelAttribute BookAdminRequestDto bookAdminRequestDto) {
        try {
            bookService.createBook(thumbnail, bookImages, bookAdminRequestDto);
        } catch (IOException e) {
            log.debug("book create error: {}", e.getMessage());
            return "error";
        }
        return "redirect:/admin/books";
    }

    @PostMapping("/book/update")
    public String updateBook(@RequestParam("thumbnail") MultipartFile thumbnail,
        @RequestParam(value = "bookImages", required = false) MultipartFile[] bookImages,
        @ModelAttribute BookAdminRequestDto bookAdminRequestDto) {
        bookService.updateBook(thumbnail, bookImages, bookAdminRequestDto);
        return "redirect:/admin/books";
    }

}

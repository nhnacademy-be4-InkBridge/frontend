package com.nhnacademy.inkbridge.front.controller;

import com.nhnacademy.inkbridge.front.dto.BookAdminReadResponse;
import com.nhnacademy.inkbridge.front.dto.BooksAdminReadResponse;
import com.nhnacademy.inkbridge.front.service.BookService;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * class: AdminBookController.
 *
 * @author minm063
 * @version 2024/02/22
 */
@Controller
@RequestMapping("/admin")
public class AdminBookController {

    private final BookService bookService;

    public AdminBookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public String getBooks(Model model) {
        List<BooksAdminReadResponse> books = bookService.getBooks();
        model.addAttribute("books", books);
        return "admin/books";
    }

    @GetMapping("/book/{bookId}")
    public String getBook(Model model, @PathVariable Long bookId) {
        BookAdminReadResponse book = bookService.getBook(bookId);
        System.out.println("hello!! " + book.getPrice());
        model.addAttribute("book", book);
        return "admin/book_form";
    }

    @GetMapping("/book/create")
    public String createBook() {
        return "admin/book_form";
    }
}

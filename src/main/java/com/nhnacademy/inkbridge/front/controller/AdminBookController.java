package com.nhnacademy.inkbridge.front.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/books")
    public String getBooks() {
        return "admin/books";
    }

    @GetMapping("/book/create")
    public String createBook() {
        return "admin/book_form";
    }
}

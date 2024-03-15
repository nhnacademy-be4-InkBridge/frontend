package com.nhnacademy.inkbridge.front.controller;

import com.nhnacademy.inkbridge.front.dto.author.AuthorReadResponseDto;
import com.nhnacademy.inkbridge.front.service.AuthorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * class: AuthorController.
 *
 * @author minm063
 * @version 2024/03/14
 */
@Slf4j
@Controller
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/author/{authorId}")
    public String getAuthor(@PathVariable Long authorId, @RequestParam(defaultValue = "0") Long page, Model model) {
        log.debug("hello!!!");
        AuthorReadResponseDto authors = authorService.getAuthor(authorId, page);
        log.debug("hello!!!");
        model.addAttribute("authorInfo", authors.getAuthorInfoReadResponseDto());
        model.addAttribute("authorBooks", authors.getBooksPaginationReadResponseDtos());

        return "member/author";
    }
}

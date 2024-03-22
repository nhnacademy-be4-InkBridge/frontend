package com.nhnacademy.inkbridge.front.controller;

import com.nhnacademy.inkbridge.front.dto.PageRequestDto;
import com.nhnacademy.inkbridge.front.dto.author.AuthorCreateUpdateRequestDto;
import com.nhnacademy.inkbridge.front.dto.author.AuthorInfoReadResponseDto;
import com.nhnacademy.inkbridge.front.exception.ValidationException;
import com.nhnacademy.inkbridge.front.service.AuthorService;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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

    /**
     * 작가 아이디에 따른 작가 상세 조회 메서드입니다.
     *
     * @param authorId Long
     * @param page     Long
     * @param size     Long
     * @param model    Model
     * @return html
     */
    @GetMapping("/author/{authorId}")
    public String getAuthor(@PathVariable Long authorId,
        @RequestParam(defaultValue = "0") Long page, @RequestParam(defaultValue = "0") Long size,
        Model model) {
        AuthorInfoReadResponseDto authors = authorService.getAuthor(authorId, page, size);
        model.addAttribute("authorInfo", authors);

        return "member/author";
    }

    /**
     * 관리자 페이지에서 작가 전체 목록을 조회하는 메서드입니다.
     *
     * @param model Model
     * @param page  Long
     * @param size  Long
     * @return html
     */
    @GetMapping("/admin/authors")
    public String getAuthors(Model model, @RequestParam(defaultValue = "0") Long page,
        @RequestParam(defaultValue = "10") Long size) {
        PageRequestDto<AuthorInfoReadResponseDto> authors = authorService.getAuthors(page, size);
        model.addAttribute("authors", authors);

        return "admin/authors";
    }

    /**
     * 관리자 페이지에서 작가를 생성하는 메서드입니다.
     *
     * @param authorFile                   MultipartFile
     * @param authorCreateUpdateRequestDto AuthorCreateUpdateRequestDto
     * @param bindingResult                BindingResult
     * @return html
     */
    @PostMapping("/admin/authors")
    public String createAuthor(@RequestParam("authorFile") MultipartFile authorFile,
        @Valid @ModelAttribute AuthorCreateUpdateRequestDto authorCreateUpdateRequestDto,
        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.debug("error: {}", bindingResult.getFieldError().getDefaultMessage());
            throw new ValidationException(bindingResult.getFieldError().getDefaultMessage());
        }
        authorService.createAuthor(authorFile, authorCreateUpdateRequestDto);
        return "redirect:/admin/authors";
    }

    /**
     * 관리자 페이지에서 작가를 수정하는 메서드입니다.
     *
     * @param authorId                     Long
     * @param authorFile                   MultipartFile
     * @param authorCreateUpdateRequestDto AuthorCreateUpdateRequestDto
     * @param bindingResult                BindingResult
     * @return html
     */
    @PostMapping("/admin/authors/{authorId}")
    public String updateAuthor(@PathVariable Long authorId,
        @RequestParam("authorFile") MultipartFile authorFile,
        @Valid @ModelAttribute AuthorCreateUpdateRequestDto authorCreateUpdateRequestDto,
        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.debug("error: {}", bindingResult.getFieldError().getDefaultMessage());
            throw new ValidationException(bindingResult.getFieldError().getDefaultMessage());
        }
        authorService.updateAuthor(authorFile, authorCreateUpdateRequestDto, authorId);
        return "redirect:/admin/authors";
    }
}

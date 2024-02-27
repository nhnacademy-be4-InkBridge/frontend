package com.nhnacademy.inkbridge.front.controller;

import com.nhnacademy.inkbridge.front.dto.BookAdminReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.BookAdminCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.BooksAdminReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.PageRequestDto;
import com.nhnacademy.inkbridge.front.dto.TagResponse;
import com.nhnacademy.inkbridge.front.dto.category.ParentCategoryReadResponseDto;
import com.nhnacademy.inkbridge.front.service.BookService;
import com.nhnacademy.inkbridge.front.service.CategoryService;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    private static final String BOOK_FORM_PAGE = "admin/book_form";
    private final BookService bookService;
    private final CategoryService categoryService;

    public AdminBookController(BookService bookService, CategoryService categoryService) {
        this.bookService = bookService;
        this.categoryService = categoryService;
    }

    /**
     * 관리자 페이지에서 등록된 도서 전체 리스트를 조회하는 메서드입니다.
     *
     * @param model Model
     * @param page page
     * @param size size
     * @return html
     */
    @GetMapping("/books")
    public String getBooks(Model model,
        @RequestParam(name = "page", defaultValue = "0") Integer page,
        @RequestParam(name = "size", defaultValue = "10") Integer size) {
        PageRequestDto<BooksAdminReadResponseDto> books = bookService.getBooksAdmin(page, size);
        model.addAttribute("books", books);
        return "admin/books";
    }

    /**
     * 관리자 페이지에서 등록된 도서 상세 정보를 조회하는 메서드입니다.
     *
     * @param model Model
     * @param bookId Long
     * @return html
     */
    @GetMapping("/book/{bookId}")
    public String getBook(Model model, @PathVariable Long bookId) {
        BookAdminReadResponseDto book = bookService.getBook(bookId);
        setForm(model);
        model.addAttribute("book", book);
        model.addAttribute("savedTags",
            List.of(TagResponse.builder().tagId(1L).tagName("이달의 도서").build()));
        return "admin/book_form_value";
    }

    /**
     * 도서를 등록하는 페이지를 호출하는 메서드입니다.
     *
     * @param model Model
     * @return html
     */
    @GetMapping("/book/create")
    public String createBook(Model model) {
        setForm(model);
        return BOOK_FORM_PAGE;
    }

    /**
     * 입력 값을 도서에 등록하는 메서드입니다.
     *
     * @param thumbnail MultipartFile
     * @param bookAdminCreateRequestDto BookAdminRequestDto
     * @param bindingResult BindingResult
     * @param model Model
     * @return html
     */
    @PostMapping("/book/create")
    public String createBook(@RequestParam("thumbnail") MultipartFile thumbnail,
        @Valid @ModelAttribute BookAdminCreateRequestDto bookAdminCreateRequestDto,
        BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", true);
            return BOOK_FORM_PAGE;
        }
        try {
            bookService.createBook(thumbnail, bookAdminCreateRequestDto);
        } catch (IOException e) {
            log.debug("book create error: {}", e.getMessage());
            return "error";
        }
        return "redirect:/admin/books";
    }

    /**
     * 등록된 도서 정보를 수정하는 메서드입니다.
     *
     * @param thumbnail MultipartFile
     * @param bookAdminCreateRequestDto BookAdminRequestDto
     * @param bindingResult BindingResult
     * @param model Model
     * @return html
     */
    @PostMapping("/book/update")
    public String updateBook(@RequestParam("thumbnail") MultipartFile thumbnail,
        @Valid @ModelAttribute BookAdminCreateRequestDto bookAdminCreateRequestDto,
        BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", true);
            return BOOK_FORM_PAGE;
        }

        try {
            bookService.updateBook(thumbnail, bookAdminCreateRequestDto);
        } catch (IOException e) {
            log.debug("book update error: {}", e.getMessage());
            return "error";
        }
        return "redirect:/admin/books";
    }

    /**
     * 등록과 조회 페이지에서 공유하는 attribute를 처리하는 메서드입니다.
     *
     * @param model Model
     */
    private void setForm(Model model) {
        List<ParentCategoryReadResponseDto> categories = categoryService.readCategory();

        model.addAttribute("categories", categories);
        model.addAttribute("tags",
            List.of(TagResponse.builder().tagId(1L).tagName("이달의 도서").build(),
                TagResponse.builder().tagId(2L).tagName("test2").build()));
        model.addAttribute("now", LocalDate.now());
    }
}

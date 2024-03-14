package com.nhnacademy.inkbridge.front.controller;

import com.nhnacademy.inkbridge.front.dto.PageRequestDto;
import com.nhnacademy.inkbridge.front.dto.book.BookAdminCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.book.BookAdminDetailReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.book.BookAdminReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.book.BookAdminUpdateRequestDto;
import com.nhnacademy.inkbridge.front.dto.book.BooksAdminReadResponseDto;
import com.nhnacademy.inkbridge.front.service.BookService;
import java.io.IOException;
import java.time.LocalDate;
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

    public AdminBookController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * 관리자 페이지에서 등록된 도서 전체 리스트를 조회하는 메서드입니다.
     *
     * @param model Model
     * @param page  page
     * @param size  size
     * @return html
     */
    @GetMapping("/books")
    public String getBooks(Model model,
        @RequestParam(name = "page", defaultValue = "0") Integer page,
        @RequestParam(name = "size", defaultValue = "10") Integer size) {
        BooksAdminReadResponseDto books = bookService.getBooksAdmin(page, size);
        model.addAttribute("books", books.getBooksAdminPaginationReadResponseDtos());
        model.addAttribute("authors", books.getAuthorPaginationReadResponseDtos());
        return "admin/books";
    }

    /**
     * 관리자 페이지에서 등록된 도서 상세 정보를 조회하는 메서드입니다.
     *
     * @param model  Model
     * @param bookId Long
     * @return html
     */
    @GetMapping("/book/{bookId}")
    public String getBook(Model model, @PathVariable Long bookId) {
        BookAdminDetailReadResponseDto bookAdminDetailReadResponseDto = bookService.getBook(bookId);

        model.addAttribute("bookId", bookId);
        model.addAttribute("book",
            bookAdminDetailReadResponseDto.getAdminSelectedReadResponseDto());

        model.addAttribute("categories",
            bookAdminDetailReadResponseDto.getParentCategoryReadResponseDtoList());
        model.addAttribute("publishers",
            bookAdminDetailReadResponseDto.getPublisherReadResponseDtoList());
        model.addAttribute("authors",
            bookAdminDetailReadResponseDto.getAuthorReadResponseDtoList());
        model.addAttribute("statuses",
            bookAdminDetailReadResponseDto.getBookStatusReadResponseDtoList());
        model.addAttribute("tags", bookAdminDetailReadResponseDto.getTagReadResponseDtoList());
        model.addAttribute("now", LocalDate.now());
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
        BookAdminReadResponseDto bookAdminReadResponseDto = bookService.getBook();

        model.addAttribute("categories",
            bookAdminReadResponseDto.getParentCategoryReadResponseDtoList());
        model.addAttribute("publishers",
            bookAdminReadResponseDto.getPublisherReadResponseDtoList());
        model.addAttribute("authors", bookAdminReadResponseDto.getAuthorReadResponseDtoList());
        model.addAttribute("statuses", bookAdminReadResponseDto.getBookStatusReadResponseDtoList());
        model.addAttribute("tags", bookAdminReadResponseDto.getTagReadResponseDtoList());
        model.addAttribute("now", LocalDate.now());

        return BOOK_FORM_PAGE;
    }

    /**
     * 입력 값을 도서에 등록하는 메서드입니다.
     *
     * @param thumbnail                 MultipartFile
     * @param bookAdminCreateRequestDto BookAdminRequestDto
     * @param bindingResult             BindingResult
     * @return html
     */
    @PostMapping("/book/create")
    public String createBook(@RequestParam("thumbnail") MultipartFile thumbnail,
        @Valid @ModelAttribute BookAdminCreateRequestDto bookAdminCreateRequestDto,
        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.debug("error!!!: {}", bindingResult.getFieldErrors().get(0).getDefaultMessage());
            return BOOK_FORM_PAGE;
        }
        try {
            bookService.createBook(thumbnail, bookAdminCreateRequestDto);
        } catch (IOException e) {
            log.debug("book create error: {}", e.getMessage());
        }
        return "redirect:/admin/books";
    }

    /**
     * 등록된 도서 정보를 수정하는 메서드입니다.
     *
     * @param thumbnail                 MultipartFile
     * @param bookAdminUpdateRequestDto BookAdminRequestDto
     * @param bindingResult             BindingResult
     * @return html
     */
    @PostMapping("/book/update/{bookId}")
    public String updateBook(@PathVariable Long bookId,
        @RequestParam(name = "thumbnail", required = false) MultipartFile thumbnail,
        @Valid @ModelAttribute BookAdminUpdateRequestDto bookAdminUpdateRequestDto,
        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return BOOK_FORM_PAGE;
        }

        try {
            bookService.updateBook(bookId, thumbnail, bookAdminUpdateRequestDto);
        } catch (IOException e) {
            log.debug("book update error: {}", e.getMessage());
        }
        return "redirect:/admin/books";
    }
}

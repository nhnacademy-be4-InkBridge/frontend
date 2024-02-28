package com.nhnacademy.inkbridge.front.controller;

import com.nhnacademy.inkbridge.front.dto.AuthorResponse;
import com.nhnacademy.inkbridge.front.dto.PageRequestDto;
import com.nhnacademy.inkbridge.front.dto.PublisherResponse;
import com.nhnacademy.inkbridge.front.dto.TagResponse;
import com.nhnacademy.inkbridge.front.dto.book.BookAdminCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.book.BookAdminReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.book.BookAdminUpdateRequestDto;
import com.nhnacademy.inkbridge.front.dto.book.BooksAdminReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.bookCategory.BookCategoryReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.bookstatus.BookStatusReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.category.ParentCategoryReadResponseDto;
import com.nhnacademy.inkbridge.front.service.BookCategoryService;
import com.nhnacademy.inkbridge.front.service.BookService;
import com.nhnacademy.inkbridge.front.service.BookStatusService;
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
    private final BookStatusService bookStatusService;
    private final BookCategoryService bookCategoryService;

    public AdminBookController(BookService bookService, CategoryService categoryService,
        BookStatusService bookStatusService, BookCategoryService bookCategoryService) {
        this.bookService = bookService;
        this.categoryService = categoryService;
        this.bookStatusService = bookStatusService;
        this.bookCategoryService = bookCategoryService;
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
        PageRequestDto<BooksAdminReadResponseDto> books = bookService.getBooksAdmin(page, size);
        model.addAttribute("books", books);
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
        setForm(model);

        BookAdminReadResponseDto book = bookService.getBook(bookId);
        List<BookStatusReadResponseDto> bookStatuses = bookStatusService.getBookStatuses();
        List<BookCategoryReadResponseDto> bookCategories = bookCategoryService.readBookCategories(
            bookId);
        // author, publisher

        model.addAttribute("bookId", bookId);
        model.addAttribute("book", book);
        model.addAttribute("savedTags", List.of(1L));
        model.addAttribute("savedCategories", bookCategories);
        model.addAttribute("statuses", bookStatuses);
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

    /**
     * 등록과 조회 페이지에서 공유하는 attribute를 처리하는 메서드입니다.
     *
     * @param model Model
     */
    private void setForm(Model model) {
        List<ParentCategoryReadResponseDto> categories = categoryService.readCategory();

        model.addAttribute("categories", categories);
        // 임시
        model.addAttribute("tags",
            List.of(TagResponse.builder().tagId(1L).tagName("이달의 도서").build(),
                TagResponse.builder().tagId(2L).tagName("test2").build()));
        model.addAttribute("authors",
            List.of(AuthorResponse.builder().authorId(1L).authorName("작가1").build(),
                AuthorResponse.builder().authorId(2L).authorName("작가2").build()));
        model.addAttribute("publishers",
            List.of(PublisherResponse.builder().publisherId(1L).publisherName("출판사1").build(),
                PublisherResponse.builder().publisherId(2L).publisherName("출판사2").build()));

        model.addAttribute("now", LocalDate.now());
    }
}

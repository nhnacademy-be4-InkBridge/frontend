package com.nhnacademy.inkbridge.front.controller;


import com.nhnacademy.inkbridge.front.dto.book.BookReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.book.BooksByCategoryReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.category.ParentCategoryReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.review.ReviewBookReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.search.BookSearchResponseDto;
import com.nhnacademy.inkbridge.front.service.CategoryService;
import com.nhnacademy.inkbridge.front.service.IndexService;
import com.nhnacademy.inkbridge.front.service.ReviewService;
import com.nhnacademy.inkbridge.front.service.SearchService;
import com.nhnacademy.inkbridge.front.utils.CommonUtils;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * class: IndexController.
 *
 * @author jangjaehun
 * @version 2024/02/14
 * @modifiedBy minm063
 * @modifiedAt 2024/03/02
 * @modificationReason - index 메서드 수정, bookDetail 메서드 추가
 */
@Controller
@Slf4j
public class IndexController {

    private final IndexService indexService;
    private final CategoryService categoryService;
    private final ReviewService reviewService;
    private final SearchService searchService;
    private static final Long NOT_MEMBER = 0L;

    public IndexController(IndexService indexService, CategoryService categoryService,
        ReviewService reviewService, SearchService searchService) {
        this.indexService = indexService;
        this.categoryService = categoryService;
        this.reviewService = reviewService;
        this.searchService = searchService;
    }


    /**
     * 메인페이지 로드하는 메소드입니다.
     *
     * @param model 페이지에 넘겨줄 데이터들
     * @return 인덱스 페이지
     */
    @GetMapping
    public String index(Model model) {
        List<BookSearchResponseDto> hotBooks = searchService.searchByAll("view",
            PageRequest.of(0, 8, Sort.by("view").descending())).getContent();
        List<BookSearchResponseDto> latestBooks = searchService.searchByAll("publicatedAt",
            PageRequest.of(0, 8, Sort.by("publicatedAt").ascending())).getContent();
        List<BookSearchResponseDto> discountedBooks = searchService.searchByAll("discountRatio",
            PageRequest.of(0, 8, Sort.by("discountRatio").descending())).getContent();
        List<BookSearchResponseDto> ratingBooks = searchService.searchByAll("score",
            PageRequest.of(0, 8, Sort.by("score").descending())).getContent();

        model.addAttribute("hotBooks", hotBooks);
        model.addAttribute("latestBooks", latestBooks);
        model.addAttribute("discountedBooks", discountedBooks);
        model.addAttribute("ratingBooks", ratingBooks);

        return "main/index";
    }

    /**
     * 도서 상세 정보를 조회하는 메서드입니다.
     *
     * @param model  Model
     * @param bookId Long
     * @return html
     */
    @GetMapping("/book")
    public String bookDetail(Model model, @RequestParam(name = "id") Long bookId) {
        Long memberId = CommonUtils.getMemberId();
        memberId = Objects.equals(null, memberId) ? NOT_MEMBER : memberId;

        BookReadResponseDto book = indexService.getBook(bookId, memberId);
        ReviewBookReadResponseDto reviews = reviewService.getReviewsByBookId(bookId);

        model.addAttribute("bookId", bookId);
        model.addAttribute("book", book.getBookDetailReadResponseDto());
        model.addAttribute("reviews", reviews.getReviewDetailReadResponseDtos());
        model.addAttribute("reviewFiles", reviews.getReviewFiles());
        model.addAttribute("reviewNumber", book.getReviewAverageReadResponseDto());

        return "main/book";
    }

    /**
     * 카테고리에 따라 도서 목록을 조회하는 메서드입니다.
     *
     * @param model      Model
     * @param categoryId Long
     * @param page       Long
     * @return html
     */
    @GetMapping("/books/{categoryId}")
    public String indexByCategory(Model model, @PathVariable Long categoryId,
        @RequestParam(defaultValue = "0") Long page) {
        BooksByCategoryReadResponseDto booksByCategory = indexService.getBooksByCategory(page,
            categoryId);
        model.addAttribute("books", booksByCategory.getBooksPaginationReadResponseDtos());
        model.addAttribute("authors", booksByCategory.getAuthorPaginationReadResponseDto());
        model.addAttribute("category", booksByCategory.getCategoryNameReadResponseDto());

        List<ParentCategoryReadResponseDto> parentCategories = categoryService.readCategory();
        model.addAttribute("parentCategories", parentCategories);

        return "main/index";
    }

    @GetMapping("/api")
    public String getApi() {
        return "api";
    }
}

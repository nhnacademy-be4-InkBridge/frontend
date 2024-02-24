package com.nhnacademy.inkbridge.front.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * class: BookAdminCreateRequest.
 *
 * @author minm063
 * @version 2024/02/23
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookAdminRequest {

    private String bookTitle;
    private String bookIndex;
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate publicatedAt;
    private String isbn;
    private Long regularPrice;
    private Long price;
    private BigDecimal discountRatio;
    private Integer stock;
    private Boolean isPackagable;
    private Long publisherId;
    private List<Long> categories = new ArrayList<>();
    private List<Long> tags = new ArrayList<>();
    private List<Long> authors = new ArrayList<>();

    @Builder
    public BookAdminRequest(String bookTitle, String bookIndex, String description,
        LocalDate publicatedAt, String isbn, Long regularPrice, Long price,
        BigDecimal discountRatio, Integer stock, Boolean isPackagable, Long publisherId,
        List<Long> tags, List<Long> authors, List<Long> categories) {
        this.bookTitle = bookTitle;
        this.bookIndex = bookIndex;
        this.description = description;
        this.publicatedAt = publicatedAt;
        this.isbn = isbn;
        this.regularPrice = regularPrice;
        this.price = price;
        this.discountRatio = discountRatio;
        this.stock = stock;
        this.isPackagable = isPackagable;
        this.publisherId = publisherId;
        this.tags.addAll(tags == null ? new ArrayList<>() : tags);
        this.authors.addAll(authors);
        this.categories.addAll(categories);
    }
}

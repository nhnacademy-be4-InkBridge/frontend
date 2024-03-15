package com.nhnacademy.inkbridge.front.dto.book;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * class: BookAdminReadResponseDto.
 *
 * @author minm063
 * @version 2024/02/15
 */
@Getter
@NoArgsConstructor
public class BookAdminSelectedReadResponseDto {

    private String bookTitle;

    private String bookIndex;

    private String description;

    private LocalDate publicatedAt;

    private String isbn;

    private Long regularPrice;

    private Long price;

    private BigDecimal discountRatio;

    private Integer stock;

    private Boolean isPackagable;

    private List<Long> authorIdList;

    private Long publisherId;

    private Long statusId;

    private String url;

    private List<Long> categoryIdList;

    private List<Long> tagIdList;

    @Builder
    public BookAdminSelectedReadResponseDto(String bookTitle, String bookIndex, String description,
        LocalDate publicatedAt, String isbn, Long regularPrice, Long price,
        BigDecimal discountRatio, Integer stock, Boolean isPackagable,
        List<Long> authorIdList, Long publisherId, Long statusId, String url, List<Long> categoryIdList,
        List<Long> tagIdList) {
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
        this.authorIdList = authorIdList;
        this.publisherId = publisherId;
        this.statusId = statusId;
        this.url = url;
        this.categoryIdList = categoryIdList;
        this.tagIdList = tagIdList;
    }
}

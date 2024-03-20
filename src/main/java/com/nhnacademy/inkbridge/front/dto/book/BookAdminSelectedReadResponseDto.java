package com.nhnacademy.inkbridge.front.dto.book;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
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
}

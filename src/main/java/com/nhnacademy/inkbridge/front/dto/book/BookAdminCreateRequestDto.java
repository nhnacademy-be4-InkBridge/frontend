package com.nhnacademy.inkbridge.front.dto.book;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
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
public class BookAdminCreateRequestDto {

    @NotBlank(message = "제목에 빈 값이 들어올 수 없습니다.")
    private String bookTitle;
    @NotBlank(message = "목차에 빈 값이 들어올 수 없습니다.")
    private String bookIndex;
    @NotBlank(message = "설명에 빈 값이 들어올 수 없습니다.")
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate publicatedAt;
    @Digits(integer = 13, fraction = 0, message = "isbn은 13자리 정수여야 합니다.")
    private String isbn;
    @Max(value = Long.MAX_VALUE - 1)
    @Min(value = 0)
    private Long regularPrice;
    @Max(value = Long.MAX_VALUE - 1)
    @Min(value = 0)
    private Long price;
    private BigDecimal discountRatio;
    @Max(value = Integer.MAX_VALUE - 1)
    @Min(value = 0)
    private Integer stock;
    private Boolean isPackagable;
    private Long publisherId;
    private List<Long> authorIdList;
    private Set<Long> categories;
    private List<Long> tags = new ArrayList<>();
    private List<Long> fileIdList;

    @Builder
    public BookAdminCreateRequestDto(String bookTitle, String bookIndex, String description,
        LocalDate publicatedAt, String isbn, Long regularPrice, Long price,
        BigDecimal discountRatio, Integer stock, Boolean isPackagable, Long publisherId,
        List<Long> authorIdList, List<Long> tags, Set<Long> categories, List<Long> fileIdList) {
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
        this.tags = (tags == null ? new ArrayList<>() : tags);
        this.authorIdList = authorIdList;
        this.categories = categories;
        this.fileIdList = fileIdList;
    }
}

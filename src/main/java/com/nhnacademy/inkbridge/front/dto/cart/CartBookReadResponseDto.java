package com.nhnacademy.inkbridge.front.dto.cart;

import java.math.BigDecimal;
import lombok.Getter;

/**
 * class: CartBookReadResponseDto.
 *
 * @author minm063
 * @version 2024/03/10
 */
@Getter
public class CartBookReadResponseDto {

    private Long bookId;
    private String bookTitle;
    private Long regularPrice;
    private Long price;
    private BigDecimal discountRatio;
    private Integer stock;
    private Boolean isPackagable;
    private String thumbnail;

}

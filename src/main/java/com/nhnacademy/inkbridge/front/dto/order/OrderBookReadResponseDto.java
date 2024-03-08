package com.nhnacademy.inkbridge.front.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * class: OrderBookReadResponseDto.
 *
 * @author jangjaehun
 * @version 2024/02/28
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class OrderBookReadResponseDto {

    private Long bookId;
    private String thumbnail;
    private String bookTitle;
    private Long price;
    private Long regularPrice;
    private Integer amount;
    private Boolean isPackagable;
}

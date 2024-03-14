package com.nhnacademy.inkbridge.front.dto.order;

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
@Setter
@NoArgsConstructor
public class OrderBookInfoReadResponseDto {
    private String bookId;
    private Integer amount;
}

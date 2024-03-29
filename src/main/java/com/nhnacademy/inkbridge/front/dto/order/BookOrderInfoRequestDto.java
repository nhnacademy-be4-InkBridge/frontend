package com.nhnacademy.inkbridge.front.dto.order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * class: BookOrderList.
 *
 * @author jangjaehun
 * @version 2024/03/04
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class BookOrderInfoRequestDto {

    private Long bookId;
    private Long price;
    private Integer amount;
    private Long wrappingId;
    private String couponId;
    private Long wrappingPrice;
}
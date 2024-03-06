package com.nhnacademy.inkbridge.front.dto.order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * class: BookOrderList.
 *
 * @author jangjaehun
 * @version 2024/03/04
 */
@Getter
@Setter
@NoArgsConstructor
public class BookOrderList {

    private Long bookId;
    private Long price;
    private Integer amount;
    private Long wrappingId;
    private Long couponId;

}
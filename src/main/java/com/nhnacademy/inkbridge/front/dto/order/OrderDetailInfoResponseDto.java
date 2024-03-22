package com.nhnacademy.inkbridge.front.dto.order;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * class: OrderDetailInfoResponseDto.
 *
 * @author jangjaehun
 * @version 2024/03/20
 */
@NoArgsConstructor
@Getter
public class OrderDetailInfoResponseDto {

    private Long orderDetailId;
    private Long bookPrice;
    private Long wrappingPrice;
    private Integer amount;
    private String wrappingName;
    private String orderStatus;
    private Long bookId;
    private String thumbnailUrl;
    private String bookTitle;
    private String couponTypeName;
    private String couponName;
    private Long maxDiscountPrice;
    private Long discountPrice;
}

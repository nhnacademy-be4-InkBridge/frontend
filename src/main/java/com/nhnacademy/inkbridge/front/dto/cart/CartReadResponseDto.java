package com.nhnacademy.inkbridge.front.dto.cart;

import java.util.List;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;

/**
 * class: CartReadResponseDto.
 *
 * @author minm063
 * @version 2024/03/09
 */
@Getter
public class CartReadResponseDto {

    private final List<CartBookReadResponseDto> bookInfo;
    private final Map<String, String> bookId;

    @Builder
    public CartReadResponseDto(List<CartBookReadResponseDto> bookInfo, Map<String, String> bookId) {
        this.bookInfo = bookInfo;
        this.bookId = bookId;
    }
}

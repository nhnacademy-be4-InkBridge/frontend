package com.nhnacademy.inkbridge.front.dto.order;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * class: OrderReadResponseDto.
 *
 * @author jangjaehun
 * @version 2024/03/19
 */
@Getter
@NoArgsConstructor
public class OrderReadResponseDto {

    private Long orderId;
    private String orderCode;
    private String orderName;
    private LocalDateTime orderAt;
    private LocalDate deliveryDate;
    private Long totalPrice;
}


package com.nhnacademy.inkbridge.front.dto.order;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * class: OrderInfoReadResponseDto.
 *
 * @author jangjaehun
 * @version 2024/03/20
 */
@Getter
@NoArgsConstructor
public class OrderInfoReadResponseDto {

    private String orderCode;
    private String orderName;
    private String receiverName;
    private String receiverPhoneNumber;
    private String zipCode;
    private String address;
    private String detailAddress;
    private String senderName;
    private String senderPhoneNumber;
    private String senderEmail;
    private LocalDate deliveryDate;
    private Long usePoint;
    private Long payAmount;
    private Long deliveryPrice;
    private LocalDateTime orderAt;
    private LocalDate shipDate;
}

package com.nhnacademy.inkbridge.front.dto.order;

import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * class: BookOrderCreateRequestDto.
 *
 * @author jangjaehun
 * @version 2024/03/11
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class BookOrderCreateRequestDto {

    private String orderName;
    private String receiverName;
    private String receiverPhoneNumber;
    private String zipCode;
    private String address;
    private String detailAddress;
    private String senderName;
    private String senderPhoneNumber;
    private String senderEmail;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate deliveryDate;
    private Long usePoint;
    private String memberId;
    private Long payAmount;
    private Long deliveryPrice;
}
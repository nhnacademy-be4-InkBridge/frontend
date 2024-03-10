package com.nhnacademy.inkbridge.front.dto.order;

import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * class: OrderCreateRequestDto.
 *
 * @author jangjaehun
 * @version 2024/03/04
 */

@Setter
@NoArgsConstructor
@Getter
@ToString
public class OrderCreateRequestDto {

    private List<BookOrderInfoRequestDto> bookOrderList;
    private String orderId;
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
    private Long payAmount;
    private String memberId;
    private Long deliveryPrice;
}

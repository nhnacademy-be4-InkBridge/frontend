package com.nhnacademy.inkbridge.front.dto.order;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * class: OrderCreateRequestDto.
 *
 * @author jangjaehun
 * @version 2024/03/04
 */
@Getter
@NoArgsConstructor
@Setter
@ToString
public class OrderCreateRequestDto {

    private List<BookOrderInfoRequestDto> bookOrderList;
    private BookOrderCreateRequestDto bookOrder;
}

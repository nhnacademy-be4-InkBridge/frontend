package com.nhnacademy.inkbridge.front.dto.order;

import com.nhnacademy.inkbridge.front.dto.pay.PayInfoResponseDto;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * class: BookOrderViewResponseDto.
 *
 * @author jangjaehun
 * @version 2024/03/20
 */
@NoArgsConstructor
@Getter
public class BookOrderViewResponseDto {

    private OrderInfoReadResponseDto orderInfo;
    private PayInfoResponseDto payInfo;
    private List<OrderDetailInfoResponseDto> orderDetailInfoList;

}

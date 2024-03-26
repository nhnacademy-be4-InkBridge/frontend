package com.nhnacademy.inkbridge.front.dto.pay;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * class: PayCancelInfoDto.
 *
 * @author jangjaehun
 * @version 2024/03/24
 */
@AllArgsConstructor
@Getter
public class PayCancelInfoDto {

    private String cancelReason;
    private Long cancelAmount;
}

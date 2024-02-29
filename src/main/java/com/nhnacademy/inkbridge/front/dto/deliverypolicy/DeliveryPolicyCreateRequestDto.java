package com.nhnacademy.inkbridge.front.dto.deliverypolicy;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * class: DeliveryPolicyCreateRequestDto.
 *
 * @author jangjaehun
 * @version 2024/02/25
 */
@Getter
@RequiredArgsConstructor
public class DeliveryPolicyCreateRequestDto {

    @NotNull(message = "배송비는 필수 입력 항목입니다.")
    @PositiveOrZero(message = "배송비는 음수일 수 없습니다.")
    private final Long deliveryPrice;

    @NotNull(message = "무료 배송금액은 필수 입력 항목입니다.")
    @PositiveOrZero(message = "무료 배송금액은 음수일 수 없습니다.")
    private final Long freeDeliveryPrice;
}

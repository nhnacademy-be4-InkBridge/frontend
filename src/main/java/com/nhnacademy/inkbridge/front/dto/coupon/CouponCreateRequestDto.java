package com.nhnacademy.inkbridge.front.dto.coupon;

import java.time.LocalDate;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * class: CouponCreateRequestDTO.
 *
 * @author JBum
 * @version 2024/02/15
 */
@Getter
@NoArgsConstructor
@Setter
@ToString
public class CouponCreateRequestDto {

    @NotNull(message = "쿠폰이름을 지정하지 않았습니다.")
    private String couponName;
    @NotNull
    private Long minPrice;
    private Long maxDiscountPrice;
    @NotNull(message = "할인 가격을 지정하지 않았습니다")
    private Long discountPrice;
    @NotNull(message = "쿠폰발급시작일을 지정하지 않았습니다.")
    @FutureOrPresent(message = "쿠폰발급시작일이 과거입니다.")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate basicIssuedDate;
    @NotNull(message = "쿠폰발급만료일을 지정하지 않았습니다.")
    @FutureOrPresent(message = "쿠폰발급만료일이 과거입니다.")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate basicExpiredDate;
    @NotNull(message = "쿠폰유효기간을 지정하지 않았습니다")
    @Min(value = 0, message = "쿠폰유효기간이 0일보다 작습니다.")
    private Integer validity;
    @NotNull(message = "쿠폰이 어떤 타입인지 고르지 않았습니다.")
    private Integer couponTypeId;

    @Builder
    public CouponCreateRequestDto(String couponName, Long minPrice, Long maxDiscountPrice,
        Long discountPrice, LocalDate basicIssuedDate, LocalDate basicExpiredDate, Integer validity,
        Integer couponTypeId) {
        this.couponName = couponName;
        this.minPrice = minPrice;
        this.maxDiscountPrice = maxDiscountPrice;
        this.discountPrice = discountPrice;
        this.basicIssuedDate = basicIssuedDate;
        this.basicExpiredDate = basicExpiredDate;
        this.validity = validity;
        this.couponTypeId = couponTypeId;
    }


}
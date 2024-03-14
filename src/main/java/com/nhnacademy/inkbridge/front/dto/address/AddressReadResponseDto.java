package com.nhnacademy.inkbridge.front.dto.address;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * class: AddressReadResponseDto.
 *
 * @author jeongbyeonghun
 * @version 3/13/24
 */

@Getter
@NoArgsConstructor
@Setter
public class AddressReadResponseDto {
    Long addressId;

    String zipCode;

    String address;

    String alias;

    String addressDetail;

    String receiverName;

    String receiverNumber;

    @Builder
    public AddressReadResponseDto(Long addressId, String zipCode, String address, String alias,
        String addressDetail, String receiverName, String receiverNumber) {
        this.addressId = addressId;
        this.zipCode = zipCode;
        this.address = address;
        this.alias = alias;
        this.addressDetail = addressDetail;
        this.receiverName = receiverName;
        this.receiverNumber = receiverNumber;
    }
}

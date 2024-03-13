package com.nhnacademy.inkbridge.front.dto.address;

import lombok.Getter;
import lombok.Setter;

/**
 * class: AddressCreateRequstDto.
 *
 * @author jeongbyeonghun
 * @version 3/13/24
 */
@Getter
@Setter
public class AddressCreateRequestDto {
    String zipCode;

    String address;

    String alias;

    String addressDetail;

    String receiverName;

    String receiverNumber;
}

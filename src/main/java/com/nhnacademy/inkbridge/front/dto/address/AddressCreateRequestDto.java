package com.nhnacademy.inkbridge.front.dto.address;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * class: AddressCreateRequstDto.
 *
 * @author jeongbyeonghun
 * @version 3/13/24
 */
@Getter
@NoArgsConstructor
@Setter
public class AddressCreateRequestDto {

    @NotBlank
    @Size(max = 5)
    private String zipCode;

    @NotBlank
    private String address;

    @NotBlank
    @Size(max = 20)
    private String alias;

    @NotBlank
    @Size(max = 200)
    private String addressDetail;

    @NotBlank
    @Size(max = 20)
    private String receiverName;

    @NotBlank
    @Size(max = 11)
    private String receiverNumber;

}

package com.nhnacademy.inkbridge.front.adaptor;

import com.nhnacademy.inkbridge.front.dto.address.AddressCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.address.AddressReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.address.AddressUpdateRequestDto;
import java.util.List;

/**
 * class: AddressAdaptor.
 *
 * @author jeongbyeonghun
 * @version 3/13/24
 */
public interface AddressAdaptor {

    List<AddressReadResponseDto> getAddresses();

    AddressReadResponseDto getAddress(Long addressId);

    void createAddress(AddressCreateRequestDto addressCreateRequestDto);

    void deleteAddress(Long addressId);

    void updateAddress(AddressUpdateRequestDto addressUpdateRequestDto);
}

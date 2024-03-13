package com.nhnacademy.inkbridge.front.service;

import com.nhnacademy.inkbridge.front.dto.address.AddressCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.address.AddressReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.address.AddressUpdateRequestDto;
import java.util.List;

/**
 * class: AddressService.
 *
 * @author jeongbyeonghun
 * @version 3/13/24
 */
public interface AddressService {

    List<AddressReadResponseDto> getAddresses();

    void createAddress(AddressCreateRequestDto addressCreateRequestDto);

    void deleteAddress(Long addressId);

    AddressReadResponseDto getAddress(Long addressId);

    void updateAddress(AddressUpdateRequestDto addressUpdateRequestDto);
}

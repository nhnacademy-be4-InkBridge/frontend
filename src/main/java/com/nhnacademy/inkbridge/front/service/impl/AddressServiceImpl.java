package com.nhnacademy.inkbridge.front.service.impl;

import com.nhnacademy.inkbridge.front.adaptor.AddressAdaptor;
import com.nhnacademy.inkbridge.front.dto.address.AddressCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.address.AddressReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.address.AddressUpdateRequestDto;
import com.nhnacademy.inkbridge.front.service.AddressService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * class: AddressServiceImpl.
 *
 * @author jeongbyeonghun
 * @version 3/13/24
 */
@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressAdaptor addressAdaptor;


    @Override
    public List<AddressReadResponseDto> getAddresses() {
        return addressAdaptor.getAddresses();
    }

    @Override
    public void createAddress(AddressCreateRequestDto addressCreateRequestDto) {
        addressAdaptor.createAddress(addressCreateRequestDto);
    }

    @Override
    public void deleteAddress(Long addressId) {
        addressAdaptor.deleteAddress(addressId);
    }

    @Override
    public AddressReadResponseDto getAddress(Long addressId) {
        return addressAdaptor.getAddress(addressId);
    }

    @Override
    public void updateAddress(AddressUpdateRequestDto addressUpdateRequestDto) {
        addressAdaptor.updateAddress(addressUpdateRequestDto);
    }
}

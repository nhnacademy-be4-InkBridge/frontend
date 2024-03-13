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


    /**
     * 사용자의 모든 주소 정보를 조회합니다.
     *
     * @return 사용자의 주소 정보 리스트
     */
    @Override
    public List<AddressReadResponseDto> getAddresses() {
        return addressAdaptor.getAddresses();
    }

    /**
     * 새로운 주소 정보를 생성합니다.
     *
     * @param addressCreateRequestDto 생성할 주소 정보
     */
    @Override
    public void createAddress(AddressCreateRequestDto addressCreateRequestDto) {
        addressAdaptor.createAddress(addressCreateRequestDto);
    }

    /**
     * 지정된 ID의 주소 정보를 삭제합니다.
     *
     * @param addressId 삭제할 주소의 ID
     */
    @Override
    public void deleteAddress(Long addressId) {
        addressAdaptor.deleteAddress(addressId);
    }

    /**
     * 지정된 ID의 주소 정보를 조회합니다.
     *
     * @param addressId 조회할 주소의 ID
     * @return 조회된 주소 정보
     */
    @Override
    public AddressReadResponseDto getAddress(Long addressId) {
        return addressAdaptor.getAddress(addressId);
    }

    /**
     * 주소 정보를 업데이트합니다.
     *
     * @param addressUpdateRequestDto 업데이트할 주소 정보
     */
    @Override
    public void updateAddress(AddressUpdateRequestDto addressUpdateRequestDto) {
        addressAdaptor.updateAddress(addressUpdateRequestDto);
    }
}

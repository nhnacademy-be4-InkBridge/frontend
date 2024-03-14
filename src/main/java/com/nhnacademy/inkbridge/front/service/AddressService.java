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

    /**
     * 사용자의 모든 주소 정보를 조회합니다.
     *
     * @return 사용자의 주소 정보 리스트
     */
    List<AddressReadResponseDto> getAddresses();

    /**
     * 새로운 주소 정보를 생성합니다.
     *
     * @param addressCreateRequestDto 생성할 주소 정보
     */
    void createAddress(AddressCreateRequestDto addressCreateRequestDto);

    /**
     * 지정된 ID의 주소 정보를 삭제합니다.
     *
     * @param addressId 삭제할 주소의 ID
     */
    void deleteAddress(Long addressId);

    /**
     * 지정된 ID의 주소 정보를 조회합니다.
     *
     * @param addressId 조회할 주소의 ID
     * @return 조회된 주소 정보
     */
    AddressReadResponseDto getAddress(Long addressId);

    /**
     * 주소 정보를 업데이트합니다.
     *
     * @param addressUpdateRequestDto 업데이트할 주소 정보
     */
    void updateAddress(AddressUpdateRequestDto addressUpdateRequestDto);
}

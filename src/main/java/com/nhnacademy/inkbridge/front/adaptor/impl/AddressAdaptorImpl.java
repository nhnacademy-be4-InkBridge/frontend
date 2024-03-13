package com.nhnacademy.inkbridge.front.adaptor.impl;

import static com.nhnacademy.inkbridge.front.utils.CommonUtils.createHeader;

import com.nhnacademy.inkbridge.front.adaptor.AddressAdaptor;
import com.nhnacademy.inkbridge.front.dto.address.AddressCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.address.AddressReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.address.AddressUpdateRequestDto;
import com.nhnacademy.inkbridge.front.property.GatewayProperties;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * class: AddressAdaptorImpl.
 *
 * @author jeongbyeonghun
 * @version 3/13/24
 */
@Component
@RequiredArgsConstructor
public class AddressAdaptorImpl implements AddressAdaptor {

    private final RestTemplate restTemplate;

    private final GatewayProperties gatewayProperties;

    private static final String ADDRESS_PATH = "api/mypage/address";

    /**
     * 사용자의 모든 주소 정보를 조회합니다.
     *
     * @return 사용자 주소 정보 리스트
     */
    @Override
    public List<AddressReadResponseDto> getAddresses() {
        HttpHeaders httpHeaders = createHeader();
        URI uri = UriComponentsBuilder
            .fromUriString(gatewayProperties.getUrl())
            .path(ADDRESS_PATH)
            .encode()
            .build()
            .toUri();

        HttpEntity<Void> entity = new HttpEntity<>(httpHeaders);

        ResponseEntity<List<AddressReadResponseDto>> responseEntity = restTemplate.exchange(
            uri, HttpMethod.GET, entity, new ParameterizedTypeReference<>() {
            });
        return responseEntity.getBody();
    }


    /**
     * 특정 주소 정보를 조회합니다.
     *
     * @param addressId 조회할 주소의 ID
     * @return 조회된 주소 정보
     */
    @Override
    public AddressReadResponseDto getAddress(Long addressId) {
        HttpHeaders httpHeaders = createHeader();
        URI uri = UriComponentsBuilder
            .fromUriString(gatewayProperties.getUrl())
            .path(ADDRESS_PATH)
            .path("/{addressId}")
            .encode()
            .build()
            .expand(addressId)
            .toUri();

        HttpEntity<Void> entity = new HttpEntity<>(httpHeaders);

        ResponseEntity<AddressReadResponseDto> responseEntity = restTemplate.exchange(
            uri, HttpMethod.GET, entity, new ParameterizedTypeReference<>() {
            });
        return responseEntity.getBody();
    }

    /**
     * 새로운 주소를 생성합니다.
     *
     * @param addressCreateRequestDto 생성할 주소의 정보
     */
    @Override
    public void createAddress(AddressCreateRequestDto addressCreateRequestDto) {
        HttpHeaders httpHeaders = createHeader();
        URI uri = UriComponentsBuilder
            .fromUriString(gatewayProperties.getUrl())
            .path(ADDRESS_PATH)
            .encode()
            .build()
            .toUri();

        HttpEntity<AddressCreateRequestDto> entity = new HttpEntity<>(addressCreateRequestDto,
            httpHeaders);

        restTemplate.exchange(uri, HttpMethod.POST, entity, new ParameterizedTypeReference<>() {
        });


    }

    /**
     * 특정 주소를 삭제합니다.
     *
     * @param addressId 삭제할 주소의 ID
     */
    @Override
    public void deleteAddress(Long addressId) {
        HttpHeaders httpHeaders = createHeader();
        URI uri = UriComponentsBuilder
            .fromUriString(gatewayProperties.getUrl())
            .path(ADDRESS_PATH)
            .path("/{addressId}")
            .encode()
            .build()
            .expand(addressId)
            .toUri();

        HttpEntity<Void> entity = new HttpEntity<>(httpHeaders);

        restTemplate.exchange(uri, HttpMethod.DELETE, entity, new ParameterizedTypeReference<>() {
        });

    }

    /**
     * 주소 정보를 업데이트합니다.
     *
     * @param addressUpdateRequestDto 업데이트할 주소의 정보
     */
    @Override
    public void updateAddress(AddressUpdateRequestDto addressUpdateRequestDto) {
        HttpHeaders httpHeaders = createHeader();
        URI uri = UriComponentsBuilder
            .fromUriString(gatewayProperties.getUrl())
            .path(ADDRESS_PATH)
            .encode()
            .build()
            .toUri();

        HttpEntity<AddressUpdateRequestDto> entity = new HttpEntity<>(addressUpdateRequestDto,
            httpHeaders);

        restTemplate.exchange(uri, HttpMethod.PUT, entity, new ParameterizedTypeReference<>() {
        });


    }
}

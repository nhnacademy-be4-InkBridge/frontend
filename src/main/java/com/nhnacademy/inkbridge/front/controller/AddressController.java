package com.nhnacademy.inkbridge.front.controller;

import com.nhnacademy.inkbridge.front.dto.address.AddressCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.address.AddressUpdateRequestDto;
import com.nhnacademy.inkbridge.front.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * class: AddressController.
 *
 * @author jeongbyeonghun
 * @version 3/12/24
 */
@Controller
@RequestMapping("/mypage/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    private static final String ADDRESS_URL = "redirect:/api/mypage/address";

    @GetMapping
    String getAddressList(Model model) {
        model.addAttribute("addressList", addressService.getAddresses());
        return "/member/address/address";
    }

    @GetMapping("/register")
    String createAddressForm() {
        return "/member/address/addressRegister";
    }

    @PostMapping("/register")
    String createAddress(@RequestBody AddressCreateRequestDto addressCreateRequestDto) {
        addressService.createAddress(addressCreateRequestDto);
        return ADDRESS_URL;
    }

    @PostMapping("/delete/{addressId}")
    String deleteAddress(@PathVariable("addressId") Long addressId) {
        addressService.deleteAddress(addressId);
        return ADDRESS_URL;
    }

    @GetMapping("/update/{addressId}")
    String updateAddress(@PathVariable("addressId") Long addressId, Model model) {
        model.addAttribute("address", addressService.getAddress(addressId));
        return "/member/address/addressUpdate";
    }


    @PostMapping("/update")
    String updateAddress(@RequestBody AddressUpdateRequestDto addressUpdateRequestDto) {
        addressService.updateAddress(addressUpdateRequestDto);
        return ADDRESS_URL;
    }

}

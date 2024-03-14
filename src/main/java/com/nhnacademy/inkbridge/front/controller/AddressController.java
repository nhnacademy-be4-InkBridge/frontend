package com.nhnacademy.inkbridge.front.controller;

import com.nhnacademy.inkbridge.front.dto.address.AddressCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.address.AddressUpdateRequestDto;
import com.nhnacademy.inkbridge.front.enums.AddressMessageEnum;
import com.nhnacademy.inkbridge.front.exception.ValidationException;
import com.nhnacademy.inkbridge.front.service.AddressService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    private static final String ADDRESS_URL = "redirect:/mypage/address";

    /**
     * 사용자의 주소 목록을 조회하여 모델에 추가합니다.
     * 조회된 주소 목록은 주소 목록 페이지에 표시됩니다.
     *
     * @param model 주소 목록을 뷰에 전달하기 위한 모델 객체
     * @return 주소 목록 페이지의 경로
     */
    @GetMapping
    public String getAddressList(Model model) {
        model.addAttribute("addressList", addressService.getAddresses());
        return "member/address/address";
    }

    /**
     * 주소 등록 폼 페이지로 이동합니다.
     *
     * @return 주소 등록 폼 페이지의 경로
     */
    @GetMapping("/register")
    public String createAddressForm() {
        return "member/address/addressRegister";
    }

    /**
     * 새로운 주소를 등록합니다.
     *
     * @param addressCreateRequestDto 새 주소 등록을 위한 요청 데이터
     * @return 주소 목록 페이지로의 리다이렉션 경로
     */
    @PostMapping("/register")
    public String createAddress(@Valid @RequestBody AddressCreateRequestDto addressCreateRequestDto, BindingResult bindingResult) {
        addressService.createAddress(addressCreateRequestDto);
        if(bindingResult.hasErrors()){
            throw new ValidationException(AddressMessageEnum.ADDRESS_VALID_FAIL.getMessage());
        }
        return ADDRESS_URL;
    }

    /**
     * 지정된 ID의 주소를 삭제합니다.
     *
     * @param addressId 삭제할 주소의 ID
     * @return 주소 목록 페이지로의 리다이렉션 경로
     */
    @PostMapping("/delete/{addressId}")
    public String deleteAddress(@PathVariable("addressId") Long addressId) {
        addressService.deleteAddress(addressId);
        return ADDRESS_URL;
    }

    /**
     * 주소 수정 폼 페이지로 이동합니다. 수정할 주소의 기존 정보를 모델에 추가합니다.
     *
     * @param addressId 수정할 주소의 ID
     * @param model 수정할 주소의 정보를 뷰에 전달하기 위한 모델 객체
     * @return 주소 수정 폼 페이지의 경로
     */
    @GetMapping("/update/{addressId}")
    public String updateAddress(@PathVariable("addressId") Long addressId, Model model) {
        model.addAttribute("address", addressService.getAddress(addressId));
        return "member/address/addressUpdate";
    }


    /**
     * 주소 정보를 업데이트합니다.
     *
     * @param addressUpdateRequestDto 주소 업데이트를 위한 요청 데이터
     * @return 주소 목록 페이지로의 리다이렉션 경로
     */
    @PostMapping("/update")
    public String updateAddress(@Valid @RequestBody AddressUpdateRequestDto addressUpdateRequestDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new ValidationException(AddressMessageEnum.ADDRESS_VALID_FAIL.getMessage());
        }
        addressService.updateAddress(addressUpdateRequestDto);
        return ADDRESS_URL;
    }

    @ExceptionHandler(value = ValidationException.class)
    public String tagExceptionHandler(ValidationException e, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", e.getMessage());
        return ADDRESS_URL;
    }

}

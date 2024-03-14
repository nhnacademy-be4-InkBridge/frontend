package com.nhnacademy.inkbridge.front.controller;

import com.nhnacademy.inkbridge.front.dto.pay.PayConfirmRequestDto;
import com.nhnacademy.inkbridge.front.service.PayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * class: PayRestController.
 *
 * @author jangjaehun
 * @version 2024/03/09
 */
@RestController
@RequestMapping("/pay/confirm")
@RequiredArgsConstructor
@Slf4j
public class PayRestController {

    private final PayService payService;


    /**
     * 결제 정보를 받아 결제 승인 요청을 보낸 후 성공 시 결제 정보를 저장합니다.
     * @param requestDto 결제 승인 요청 데이터
     * @return 결제 승인 응답
     */
    @PostMapping
    public ResponseEntity<JSONObject> confirmPayment(@RequestBody PayConfirmRequestDto requestDto) {
        log.info("pay request data : {}", requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(payService.doConfirm(requestDto));
    }

}

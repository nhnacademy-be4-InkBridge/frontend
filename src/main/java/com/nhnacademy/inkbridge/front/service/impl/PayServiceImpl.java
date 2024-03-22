package com.nhnacademy.inkbridge.front.service.impl;

import static com.nhnacademy.inkbridge.front.utils.CommonUtils.getMemberId;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.inkbridge.front.adaptor.PayAdaptor;
import com.nhnacademy.inkbridge.front.adaptor.PaymentCompanyAdaptor;
import com.nhnacademy.inkbridge.front.dto.OrderBooksIdResponseDto;
import com.nhnacademy.inkbridge.front.dto.pay.PayConfirmRequestDto;
import com.nhnacademy.inkbridge.front.dto.pay.PayConfirmResponseDto;
import com.nhnacademy.inkbridge.front.dto.pay.PayCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.pay.TossConfirmResponseDto;
import com.nhnacademy.inkbridge.front.exception.PaymentConfirmResponseReadFailedException;
import com.nhnacademy.inkbridge.front.factory.PaymentCompanyAdaptorFactory;
import com.nhnacademy.inkbridge.front.service.BookService;
import com.nhnacademy.inkbridge.front.service.CartService;
import com.nhnacademy.inkbridge.front.service.OrderService;
import com.nhnacademy.inkbridge.front.service.PayService;
import com.nhnacademy.inkbridge.front.utils.CookieUtils;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * class: PayServiceImpl.
 *
 * @author jangjaehun
 * @version 2024/03/13
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class PayServiceImpl implements PayService {


    private final PaymentCompanyAdaptorFactory paymentCompanyAdaptorFactory;
    private final PayAdaptor payAdaptor;
    private final ObjectMapper objectMapper;
    private final OrderService orderService;
    private final CartService cartService;
    private final BookService bookService;

    /**
     * {@inheritDoc}
     *
     * @param requestDto 결제 승인 정보
     * @param provider 결제 회사
     */
    @Override
    public void doPayment(PayConfirmRequestDto requestDto, String provider) {

        PayConfirmResponseDto responseDto = doConfirm(requestDto, provider);
        registerPay(responseDto);

        String memberId = Objects.isNull(getMemberId()) ? CookieUtils.getCookie("cart").getValue()
            : getMemberId().toString();

        if (Objects.nonNull(memberId)) {
            List<OrderBooksIdResponseDto> bookIdResponseDtoList =
                orderService.getOrderBookIds(requestDto.getOrderId());

            bookIdResponseDtoList.forEach(
                book -> cartService.deleteCartBook(book.getBookId().toString(), memberId));
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param requestDto 결제 승인 정보
     * @return 요청 응답
     */
    @Override
    public PayConfirmResponseDto doConfirm(PayConfirmRequestDto requestDto, String provider) {
        PaymentCompanyAdaptor payCompanyAdaptor = paymentCompanyAdaptorFactory.findMatchesAdaptor(
            provider);
        String payConfirmJsonResponse = payCompanyAdaptor.doPayConfirm(requestDto);

        PayConfirmResponseDto responseDto = null;
        try {
            Map<String, Object> attribute = objectMapper.readValue(payConfirmJsonResponse,
                Map.class);

            if ("toss".equals(provider)) {
                responseDto = new TossConfirmResponseDto(attribute);
            }

            log.debug("complete toss responseDto {}", attribute);

        } catch (JsonProcessingException exception) {
            throw new PaymentConfirmResponseReadFailedException();
        }

        return responseDto;
    }

    /**
     * {@inheritDoc}
     *
     * @param responseDto 결제 정보
     */
    @Override
    public void registerPay(PayConfirmResponseDto responseDto) {

        PayCreateRequestDto payCreateRequestDto = new PayCreateRequestDto(responseDto);
        log.debug("payCreateRequestDto {}", payCreateRequestDto);
        payAdaptor.doPay(payCreateRequestDto);
    }
}

package com.nhnacademy.inkbridge.front.adaptor.impl;

import static com.nhnacademy.inkbridge.front.utils.CommonUtils.createHeader;

import com.nhnacademy.inkbridge.front.adaptor.CouponAdaptor;
import com.nhnacademy.inkbridge.front.dto.PageRequestDto;
import com.nhnacademy.inkbridge.front.dto.coupon.BirthDayCouponCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.coupon.BookCouponCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.coupon.CategoryCouponCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.coupon.CouponCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.coupon.CouponReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.coupon.MemberCouponReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.coupon.OrderCouponReadResponseDto;
import com.nhnacademy.inkbridge.front.exception.NotFoundException;
import com.nhnacademy.inkbridge.front.property.GatewayProperties;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * class: CouponAdaptorImpl.
 *
 * @author JBum
 * @version 2024/02/23
 */
@Component
public class CouponAdaptorImpl implements CouponAdaptor {

    private final RestTemplate restTemplate;
    private final GatewayProperties gatewayProperties;
    private final String CREATE_ERROR = "쿠폰생성중 문제가 발생하였습니다";
    private final String CREATE_OK = "쿠폰 생성 성공";
    private final String ISSUE_OK = "발급 성공";
    private final String ISSUE_ERROR = "이미 발급 된 쿠폰입니다";
    private final String ISSUABLE_COUPON_URL = "/coupons";

    private final String ADMIN_COUPON_URL = "/admin/coupons";

    /**
     * 쿠폰 어댑터에 필요한 컴포넌트들 호출.
     *
     * @param restTemplate      WAS를 호출할 restTemplate
     * @param gatewayProperties 게이트웨이설정
     */
    public CouponAdaptorImpl(RestTemplate restTemplate, GatewayProperties gatewayProperties) {
        this.restTemplate = restTemplate;
        this.gatewayProperties = gatewayProperties;
    }

    private static PrintWriter getPrintWriter(HttpServletResponse httpServletResponse,
        HttpStatus statusCode, String msg, String url) throws IOException {
        PrintWriter out = httpServletResponse.getWriter();
        out.println("<script language='javascript'>");

        if (statusCode == HttpStatus.CREATED) {
            out.println("alert('" + msg + "')");
        } else if (statusCode == HttpStatus.CONFLICT) {
            out.println("alert('" + msg + "')");

        } else {
            throw new NotFoundException("접근제한");
        }
        out.println("window.location.href = '" + url + "';");
        out.println("</script>");

        return out;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PageRequestDto<CouponReadResponseDto> getAdminCoupons(Integer couponStatusId,
        Integer page, Integer size) {
        HttpHeaders httpHeaders = createHeader();

        String url = String.format("%s?coupon-status-id=%d&page=%d&size=%d",
            gatewayProperties.getUrl() + "/api/admin/coupons", couponStatusId, page, size);
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<PageRequestDto<CouponReadResponseDto>> exchange =
            restTemplate.exchange(url,
                HttpMethod.GET, httpEntity,
                new ParameterizedTypeReference<PageRequestDto<CouponReadResponseDto>>() {
                });
        if (!exchange.getStatusCode().is2xxSuccessful()) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return exchange.getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCoupons(CouponCreateRequestDto couponCreateRequestDto,
        HttpServletResponse httpServletResponse) throws IOException {
        HttpHeaders httpHeaders = createHeader();

        String url = gatewayProperties.getUrl() + "/api/admin/coupons";
        HttpEntity<CouponCreateRequestDto> httpEntity = new HttpEntity<>(couponCreateRequestDto,
            httpHeaders);
        httpServletResponse.setContentType("text/html; charset=UTF-8");
        PrintWriter out;
        try {
            HttpStatus statusCode = restTemplate.exchange(
                url,
                HttpMethod.POST,
                httpEntity,
                void.class
            ).getStatusCode();
            out = getPrintWriter(httpServletResponse, statusCode, CREATE_OK, ADMIN_COUPON_URL);

        } catch (HttpClientErrorException httpClientErrorException) {
            out = getPrintWriter(httpServletResponse, HttpStatus.CONFLICT, CREATE_ERROR,
                ADMIN_COUPON_URL);

        }
        out.flush();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PageRequestDto<CouponReadResponseDto> getCoupons(Integer page, Integer size) {
        HttpHeaders httpHeaders = createHeader();
        String url = String.format("%s?page=%d&size=%d",
            gatewayProperties.getUrl() + "/api/coupons", page, size);
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<PageRequestDto<CouponReadResponseDto>> exchange =
            restTemplate.exchange(url,
                HttpMethod.GET, httpEntity,
                new ParameterizedTypeReference<PageRequestDto<CouponReadResponseDto>>() {
                });
        if (!exchange.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Failed to retrieve coupons");
        }

        return exchange.getBody();
    }

    @Override
    public void issueCoupon(String memberId, String couponId,
        HttpServletResponse httpServletResponse) throws IOException {

        HttpHeaders httpHeaders = createHeader();
        String url = String.format("%s/api/auth/members/%s/coupons/%s",
            gatewayProperties.getUrl(), memberId, couponId);
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
        httpServletResponse.setContentType("text/html; charset=UTF-8");
        PrintWriter out;
        try {

            HttpStatus statusCode = restTemplate.exchange(url,
                HttpMethod.POST, httpEntity,
                new ParameterizedTypeReference<>() {
                }).getStatusCode();
            out = getPrintWriter(httpServletResponse, statusCode, ISSUE_OK, ISSUABLE_COUPON_URL);

        } catch (HttpClientErrorException httpClientErrorException) {
            out = getPrintWriter(httpServletResponse, HttpStatus.CONFLICT, ISSUE_ERROR,
                ISSUABLE_COUPON_URL);

        }
        out.flush();
    }

    @Override
    public PageRequestDto<MemberCouponReadResponseDto> getIssuedCoupon(String memberId,
        String status, Integer page, Integer size) {
        HttpHeaders httpHeaders = createHeader();
        String url = String.format(
            "%s/api/auth/members/%s/coupons?status=%s&page=%d&size=%d",
            gatewayProperties.getUrl(), memberId, status, page, size);
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<PageRequestDto<MemberCouponReadResponseDto>> exchange;
        exchange = restTemplate.exchange(url,
            HttpMethod.GET, httpEntity,
            new ParameterizedTypeReference<>() {
            });

        return exchange.getBody();
    }

    @Override
    public List<OrderCouponReadResponseDto> getOrderCoupons(Long memberId, List<String> bookIds) {
        HttpEntity<Void> httpEntity = new HttpEntity<>(createHeader());

        URI uri = UriComponentsBuilder
            .fromUriString(gatewayProperties.getUrl())
            .path("/api/auth/members/{memberId}/order-coupons")
            .queryParam("book-id", String.join(",", bookIds))
            .encode()
            .build()
            .expand(memberId)
            .toUri();

        ResponseEntity<List<OrderCouponReadResponseDto>> exchange = restTemplate.exchange(
            uri,
            HttpMethod.GET,
            httpEntity,
            new ParameterizedTypeReference<>() {
            }
        );

        return exchange.getBody();
    }

    @Override
    public void setCategoryCoupon(CategoryCouponCreateRequestDto categoryCouponCreateRequestDto,
        HttpServletResponse httpServletResponse)
        throws IOException {
        HttpHeaders httpHeaders = createHeader();

        String url = gatewayProperties.getUrl() + "/api/admin/coupons/category-coupons";
        HttpEntity<CategoryCouponCreateRequestDto> httpEntity = new HttpEntity<>(
            categoryCouponCreateRequestDto,
            httpHeaders);
        httpServletResponse.setContentType("text/html; charset=UTF-8");
        PrintWriter out;
        try {
            HttpStatus statusCode = restTemplate.exchange(
                url,
                HttpMethod.POST,
                httpEntity,
                void.class
            ).getStatusCode();
            out = getPrintWriter(httpServletResponse, statusCode, CREATE_OK, ADMIN_COUPON_URL);

        } catch (HttpClientErrorException httpClientErrorException) {
            out = getPrintWriter(httpServletResponse, HttpStatus.CONFLICT, CREATE_ERROR,
                ADMIN_COUPON_URL);

        }
        out.flush();
    }

    @Override
    public void setBookCoupon(BookCouponCreateRequestDto bookCouponCreateRequestDto,
        HttpServletResponse httpServletResponse) throws IOException {
        HttpHeaders httpHeaders = createHeader();
        String url = gatewayProperties.getUrl() + "/api/admin/coupons/book-coupons";
        HttpEntity<BookCouponCreateRequestDto> httpEntity = new HttpEntity<>(
            bookCouponCreateRequestDto,
            httpHeaders);
        httpServletResponse.setContentType("text/html; charset=UTF-8");
        PrintWriter out;
        try {
            HttpStatus statusCode = restTemplate.exchange(
                url,
                HttpMethod.POST,
                httpEntity,
                void.class
            ).getStatusCode();
            out = getPrintWriter(httpServletResponse, statusCode, CREATE_OK, ADMIN_COUPON_URL);

        } catch (HttpClientErrorException httpClientErrorException) {
            out = getPrintWriter(httpServletResponse, HttpStatus.CONFLICT, CREATE_ERROR,
                ADMIN_COUPON_URL);

        }
        out.flush();
    }

    @Override
    public void setBirthdayCoupon(BirthDayCouponCreateRequestDto birthDayCouponCreateRequestDto,
        HttpServletResponse httpServletResponse)
        throws IOException {
        HttpHeaders httpHeaders = createHeader();

        String url = gatewayProperties.getUrl() + "/api/admin/coupons/birthday-coupons";
        HttpEntity<BirthDayCouponCreateRequestDto> httpEntity = new HttpEntity<>(
            birthDayCouponCreateRequestDto,
            httpHeaders);
        httpServletResponse.setContentType("text/html; charset=UTF-8");
        PrintWriter out;
        try {
            HttpStatus statusCode = restTemplate.exchange(
                url,
                HttpMethod.POST,
                httpEntity,
                void.class
            ).getStatusCode();
            out = getPrintWriter(httpServletResponse, statusCode, CREATE_OK, ADMIN_COUPON_URL);

        } catch (HttpClientErrorException httpClientErrorException) {
            out = getPrintWriter(httpServletResponse, HttpStatus.CONFLICT, CREATE_ERROR,
                ADMIN_COUPON_URL);

        }
        out.flush();
    }
}

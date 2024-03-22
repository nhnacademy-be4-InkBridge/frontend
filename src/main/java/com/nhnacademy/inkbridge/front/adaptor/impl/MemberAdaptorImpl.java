package com.nhnacademy.inkbridge.front.adaptor.impl;

import static com.nhnacademy.inkbridge.front.jwt.utils.JwtEnums.ACCESS_HEADER;
import static com.nhnacademy.inkbridge.front.jwt.utils.JwtEnums.BEARER_PREFIX;
import static com.nhnacademy.inkbridge.front.jwt.utils.JwtEnums.REFRESH_HEADER;
import static com.nhnacademy.inkbridge.front.utils.CommonUtils.createHeader;
import static com.nhnacademy.inkbridge.front.utils.CommonUtils.getMemberId;

import com.nhnacademy.inkbridge.front.adaptor.MemberAdaptor;
import com.nhnacademy.inkbridge.front.dto.member.MemberPointReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.member.request.MemberEmailRequestDto;
import com.nhnacademy.inkbridge.front.dto.member.request.MemberLoginRequestDto;
import com.nhnacademy.inkbridge.front.dto.member.request.MemberPasswordRequestDto;
import com.nhnacademy.inkbridge.front.dto.member.request.MemberSignupOAuthRequestDto;
import com.nhnacademy.inkbridge.front.dto.member.request.MemberSignupRequestDto;
import com.nhnacademy.inkbridge.front.dto.member.request.MemberUpdateRequestDto;
import com.nhnacademy.inkbridge.front.dto.member.response.MemberInfoResponseDto;
import com.nhnacademy.inkbridge.front.property.GatewayProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * class: MemberAdaptorImpl.
 *
 * @author devminseo
 * @version 2/29/24
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class MemberAdaptorImpl implements MemberAdaptor {

    private final RestTemplate restTemplate;
    private final GatewayProperties gatewayProperties;
    private static final String URL = "https://inkbridge.store/auth-login";


    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<Void> login(MemberLoginRequestDto memberLoginRequestDto) {
        return restTemplate.exchange(
            gatewayProperties.getUrl() + "/auth/login",
            HttpMethod.POST,
            new HttpEntity<>(memberLoginRequestDto, createHeader()),
            Void.class
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MemberInfoResponseDto getMemberInfoByToken(String accessToken) {
        HttpHeaders header = createHeader();
        header.add(HttpHeaders.AUTHORIZATION, BEARER_PREFIX.getName() + accessToken);

        return restTemplate.exchange(
            gatewayProperties.getUrl() + "/api/auth/info",
            HttpMethod.GET,
            new HttpEntity<>(header),
            MemberInfoResponseDto.class
        ).getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<Void> reissueToken(String access, String refresh) {
        HttpHeaders header = createHeader();
        header.add(ACCESS_HEADER.getName(), BEARER_PREFIX.getName() + access);
        header.add(REFRESH_HEADER.getName(), BEARER_PREFIX.getName() + refresh);

        return restTemplate.exchange(
            gatewayProperties.getUrl() + "/auth/reissue",
            HttpMethod.POST,
            new HttpEntity<>(header),
            Void.class
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void signup(MemberSignupRequestDto memberSignupRequestDto) {
        restTemplate.exchange(
            gatewayProperties.getUrl() + "/api/members/",
            HttpMethod.POST,
            new HttpEntity<>(memberSignupRequestDto, createHeader()),
            Void.class
        );
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void signupWithOAuth(MemberSignupOAuthRequestDto memberSignupOAuthRequestDto) {
        log.debug("signup oauth start ->");
        restTemplate.exchange(
            gatewayProperties.getUrl() + "/api/members/",
            HttpMethod.POST,
            new HttpEntity<>(memberSignupOAuthRequestDto, createHeader()),
            Void.class
        );
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void logout(String access, String refresh) {
        HttpHeaders header = createHeader();
        header.add(ACCESS_HEADER.getName(), BEARER_PREFIX.getName() + access);
        header.add(REFRESH_HEADER.getName(), BEARER_PREFIX.getName() + refresh);

        restTemplate.exchange(
            gatewayProperties.getUrl() + "/auth/logout",
            HttpMethod.GET,
            new HttpEntity<>(header),
            Void.class
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<Void> doLogin(String id, String password) {

        // POST 요청에 필요한 데이터
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("email", id);
        formData.add("password", password);

        // POST 요청 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // POST 요청 생성
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(formData,
            headers);

        return restTemplate.exchange(URL, HttpMethod.POST, requestEntity, Void.class);
    }

    /**
     * 로그인한 회원의 포인트 가져오는 메서드
     *
     * @return 로그인 한 회원의 포인트 값
     */
    @Override
    public MemberPointReadResponseDto getPoint() {
        HttpHeaders headers = createHeader();

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<MemberPointReadResponseDto> responseEntity = restTemplate.exchange(
            gatewayProperties.getUrl() + "/api/mygage/points", HttpMethod.GET, entity,
            new ParameterizedTypeReference<>() {
            });
        return responseEntity.getBody();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<Boolean> isDuplicatedEmail(MemberEmailRequestDto memberEmailRequestDto) {
        return restTemplate.exchange(
                gatewayProperties.getUrl() + "/api/members/checkEmail",
                HttpMethod.POST,
                new HttpEntity<>(memberEmailRequestDto, createHeader()),
                Boolean.class
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateMember(MemberUpdateRequestDto memberUpdateRequestDto,Long memberId) {
        restTemplate.exchange(
                gatewayProperties.getUrl() + "/api/mypage/members/"+memberId,
                HttpMethod.PUT,
                new HttpEntity<>(memberUpdateRequestDto, createHeader()),
                Void.class
        );
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<Boolean> updatePassword(MemberPasswordRequestDto memberPasswordRequestDto) {
        Long memberId = getMemberId();
        return restTemplate.exchange(
                gatewayProperties.getUrl() + "/api/mypage/members/"+memberId+"/password",
                HttpMethod.POST,
                new HttpEntity<>(memberPasswordRequestDto, createHeader()),
                Boolean.class
        );
    }

    @Override
    public String getPassword() {
        return restTemplate.exchange(
                gatewayProperties.getUrl() + "/api/mypage/members/password",
                HttpMethod.GET,
                new HttpEntity<>(createHeader()),
                String.class
        ).getBody();
    }

}

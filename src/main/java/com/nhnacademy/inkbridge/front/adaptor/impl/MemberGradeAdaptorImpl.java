package com.nhnacademy.inkbridge.front.adaptor.impl;

import static com.nhnacademy.inkbridge.front.utils.CommonUtils.createHeader;

import com.nhnacademy.inkbridge.front.adaptor.MemberGradeAdaptor;
import com.nhnacademy.inkbridge.front.dto.member.MemberGradeReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.member.MemberGradeUpdateRequestDto;
import com.nhnacademy.inkbridge.front.property.GatewayProperties;
import groovy.util.logging.Slf4j;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * class: MemberGradeAdaptorImpl.
 *
 * @author jeongbyeonghun
 * @version 3/21/24
 */
@RequiredArgsConstructor
@Component
@Slf4j
public class MemberGradeAdaptorImpl implements MemberGradeAdaptor {

    private final RestTemplate restTemplate;

    private final GatewayProperties gatewayProperties;

    private static final String GRADE_PATH = "/api/admin/member/grade";

    @Override
    public List<MemberGradeReadResponseDto> getGrades() {
        HttpHeaders headers = createHeader();

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<List<MemberGradeReadResponseDto>> responseEntity = restTemplate.exchange(
            gatewayProperties.getUrl() + GRADE_PATH, HttpMethod.GET, entity,
            new ParameterizedTypeReference<>() {
            });
        return responseEntity.getBody();
    }

    @Override
    public void updateGrade(Long gradeId, MemberGradeUpdateRequestDto memberGradeUpdateRequestDto) {
        HttpHeaders headers = createHeader();

        HttpEntity<MemberGradeUpdateRequestDto> entity = new HttpEntity<>(
            memberGradeUpdateRequestDto, headers);

        restTemplate.exchange(gatewayProperties.getUrl() + GRADE_PATH + "/" + gradeId, HttpMethod.PUT, entity,
            new ParameterizedTypeReference<>() {
            });
    }
}

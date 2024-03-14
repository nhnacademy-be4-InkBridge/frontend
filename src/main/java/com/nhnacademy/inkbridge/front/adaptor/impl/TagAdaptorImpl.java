package com.nhnacademy.inkbridge.front.adaptor.impl;

import static com.nhnacademy.inkbridge.front.utils.CommonUtils.createHeader;

import com.nhnacademy.inkbridge.front.adaptor.TagAdaptor;
import com.nhnacademy.inkbridge.front.dto.tag.TagCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.tag.TagCreateResponseDto;
import com.nhnacademy.inkbridge.front.dto.tag.TagDeleteResponseDto;
import com.nhnacademy.inkbridge.front.dto.tag.TagReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.tag.TagUpdateRequestDto;
import com.nhnacademy.inkbridge.front.dto.tag.TagUpdateResponseDto;
import com.nhnacademy.inkbridge.front.exception.TagException;
import com.nhnacademy.inkbridge.front.property.GatewayProperties;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * class: TagAdaptorImpl.
 *
 * @author jeongbyeonghun
 * @version 3/4/24
 */
@Component
@RequiredArgsConstructor
public class TagAdaptorImpl implements TagAdaptor {

    private final RestTemplate restTemplate;

    private final GatewayProperties gatewayProperties;

    private static final String TAG_PATH = "api/tags";



    @Override
    public void createTag(TagCreateRequestDto newTag) {
        HttpHeaders httpHeaders = createHeader();

        URI uri = UriComponentsBuilder
            .fromUriString(gatewayProperties.getUrl())
            .path(TAG_PATH)
            .encode()
            .build()
            .toUri();

        HttpEntity<TagCreateRequestDto> entity = new HttpEntity<>(newTag, httpHeaders);
        ResponseEntity<TagCreateResponseDto> responseEntity = restTemplate.exchange(
            uri, HttpMethod.POST, entity, new ParameterizedTypeReference<>() {
            });
        if (responseEntity.getStatusCode() != HttpStatus.CREATED) {
            String message = newTag.getTagName() + " 이미 존재하는 테그입니다.";
            throw new TagException(message);
        }
    }

    @Override
    public void deleteTag(Long tagId) {
        HttpHeaders httpHeaders = createHeader();


        URI uri = UriComponentsBuilder
            .fromUriString(gatewayProperties.getUrl())
            .path(TAG_PATH)
            .path("/{tagId}")
            .encode()
            .build()
            .expand(tagId)
            .toUri();
        HttpEntity<Void> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<TagDeleteResponseDto> responseEntity = restTemplate.exchange(
            uri, HttpMethod.DELETE, entity, new ParameterizedTypeReference<>() {
            });
        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new TagException(tagId + " 없는 테그아이디입니다.");
        }

    }

    @Override
    public void updateTag(Long tagId, TagUpdateRequestDto tagUpdateRequestDto) {
        HttpHeaders httpHeaders = createHeader();


        URI uri = UriComponentsBuilder
            .fromUriString(gatewayProperties.getUrl())
            .path(TAG_PATH)
            .path("/{tagId}")
            .encode()
            .build()
            .expand(tagId)
            .toUri();
        HttpEntity<TagUpdateRequestDto> entity = new HttpEntity<>(tagUpdateRequestDto, httpHeaders);
        ResponseEntity<TagUpdateResponseDto> responseEntity = restTemplate.exchange(
            uri, HttpMethod.PUT, entity, new ParameterizedTypeReference<>() {
            });
        if(responseEntity.getStatusCode() != HttpStatus.OK){
            throw new TagException(tagUpdateRequestDto.getTagName() + " 이미 존재하는 테그입니다.");
        }
    }

    @Override
    public List<TagReadResponseDto> getTagList() {
        HttpHeaders httpHeaders = createHeader();


        URI uri = UriComponentsBuilder
            .fromUriString(gatewayProperties.getUrl())
            .path(TAG_PATH)
            .encode()
            .build()
            .toUri();

        HttpEntity<TagReadResponseDto> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<List<TagReadResponseDto>> responseEntity = restTemplate.exchange(
            uri, HttpMethod.GET, entity, new ParameterizedTypeReference<>() {
            });
        return responseEntity.getBody();
    }

}
package com.nhnacademy.inkbridge.front.adaptor.impl;

import com.nhnacademy.inkbridge.front.adaptor.AuthorAdaptor;
import com.nhnacademy.inkbridge.front.dto.PageRequestDto;
import com.nhnacademy.inkbridge.front.dto.author.AuthorCreateUpdateRequestDto;
import com.nhnacademy.inkbridge.front.dto.author.AuthorInfoReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.author.AuthorReadResponseDto;
import com.nhnacademy.inkbridge.front.property.GatewayProperties;
import com.nhnacademy.inkbridge.front.utils.CommonUtils;
import java.io.IOException;
import java.net.URI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * class: AuthorAdaptorImpl.
 *
 * @author minm063
 * @version 2024/03/14
 */
@Slf4j
@Component
public class AuthorAdaptorImpl implements AuthorAdaptor {

    private final GatewayProperties gatewayProperties;
    private final RestTemplate restTemplate;
    private static final String PATH = "/api/authors";
    private static final String ADMIN_PATH = "/api/admin/authors";

    public AuthorAdaptorImpl(GatewayProperties gatewayProperties, RestTemplate restTemplate) {
        this.gatewayProperties = gatewayProperties;
        this.restTemplate = restTemplate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AuthorReadResponseDto getAuthor(Long authorId, Long page, Long size) {
        URI uri = UriComponentsBuilder
            .fromUriString(gatewayProperties.getUrl())
            .path(PATH)
            .path("/{authorId}")
            .queryParam("page", page)
            .queryParam("size", size)
            .encode()
            .build()
            .expand(authorId)
            .toUri();

        ResponseEntity<AuthorReadResponseDto> exchange = restTemplate.exchange(uri, HttpMethod.GET,
            new HttpEntity<>(CommonUtils.createHeader()),
            new ParameterizedTypeReference<>() {
            });
        if (exchange.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }

        return exchange.getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PageRequestDto<AuthorInfoReadResponseDto> getAuthors(Long page, Long size) {
        URI uri = UriComponentsBuilder
            .fromUriString(gatewayProperties.getUrl())
            .path(ADMIN_PATH)
            .queryParam("page", page)
            .queryParam("size", size)
            .encode()
            .build()
            .toUri();

        ResponseEntity<PageRequestDto<AuthorInfoReadResponseDto>> exchange = restTemplate.exchange(
            uri, HttpMethod.GET,
            new HttpEntity<>(CommonUtils.createHeader()),
            new ParameterizedTypeReference<>() {
            });

        if (exchange.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }

        return exchange.getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createAuthor(MultipartFile authorFile,
        AuthorCreateUpdateRequestDto authorCreateUpdateRequestDto) {
        URI uri = UriComponentsBuilder
            .fromUriString(gatewayProperties.getUrl())
            .path(ADMIN_PATH)
            .encode()
            .build()
            .toUri();

        MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();
        HttpHeaders httpHeaders = CommonUtils.createHeader(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<AuthorCreateUpdateRequestDto> httpEntity = new HttpEntity<>(
            authorCreateUpdateRequestDto,
            CommonUtils.createHeader());

        try {
            ByteArrayResource byteArrayResource = new ByteArrayResource(authorFile.getBytes()) {
                @Override
                public String getFilename() {
                    return authorFile.getOriginalFilename();
                }
            };
            multiValueMap.add("image", new HttpEntity<>(byteArrayResource, httpHeaders));
        } catch (IOException e) {
            log.error("error: {}", e.getMessage());
            return;
        }
        multiValueMap.add("author", httpEntity);

        ResponseEntity<HttpStatus> response = restTemplate.postForEntity(uri,
            new HttpEntity<>(multiValueMap, httpHeaders),
            HttpStatus.class);
        if (response.getStatusCode() != HttpStatus.CREATED) {
            throw new RuntimeException();
        }
    }

    @Override
    public void updateAuthor(MultipartFile authorFile,
        AuthorCreateUpdateRequestDto authorCreateUpdateRequestDto, Long authorId) {
        URI uri = UriComponentsBuilder
            .fromUriString(gatewayProperties.getUrl())
            .path(ADMIN_PATH)
            .path("/{authorId}")
            .encode()
            .build()
            .expand(authorId)
            .toUri();

        MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();
        HttpHeaders httpHeaders = CommonUtils.createHeader(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<AuthorCreateUpdateRequestDto> httpEntity = new HttpEntity<>(
            authorCreateUpdateRequestDto,
            CommonUtils.createHeader());

        try {
            ByteArrayResource byteArrayResource = new ByteArrayResource(authorFile.getBytes()) {
                @Override
                public String getFilename() {
                    return authorFile.getOriginalFilename();
                }
            };
            multiValueMap.add("image", new HttpEntity<>(byteArrayResource, httpHeaders));
        } catch (IOException e) {
            log.error("error: {}", e.getMessage());
            return;
        }
        multiValueMap.add("author", httpEntity);

        ResponseEntity<HttpStatus> response = restTemplate.exchange(uri, HttpMethod.PUT,
            new HttpEntity<>(multiValueMap, httpHeaders),
            HttpStatus.class);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }
    }
}

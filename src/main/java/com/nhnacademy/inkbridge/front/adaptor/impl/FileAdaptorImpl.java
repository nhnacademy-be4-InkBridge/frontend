package com.nhnacademy.inkbridge.front.adaptor.impl;

import com.nhnacademy.inkbridge.front.adaptor.FileAdaptor;
import com.nhnacademy.inkbridge.front.dto.book.BookFileReadResponseDto;
import com.nhnacademy.inkbridge.front.exception.ValidationException;
import com.nhnacademy.inkbridge.front.property.GatewayProperties;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * class: FileAdaptorImpl.
 *
 * @author minm063
 * @version 2024/02/29
 */
@Component
public class FileAdaptorImpl implements FileAdaptor {

    private final Path noImageFile = Paths.get("image/noImage.png").toAbsolutePath();

    private final RestTemplate restTemplate;
    private final GatewayProperties gatewayProperties;

    public FileAdaptorImpl(RestTemplate restTemplate, GatewayProperties gatewayProperties) {
        this.restTemplate = restTemplate;
        this.gatewayProperties = gatewayProperties;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BookFileReadResponseDto uploadFile(MultipartFile image) throws IOException {
        URI uri = UriComponentsBuilder
            .fromUriString(gatewayProperties.getUrl())
            .path("/api/images")
            .encode()
            .build()
            .toUri();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();

        fileToByteArrayResource(image, multiValueMap, httpHeaders);

        ResponseEntity<BookFileReadResponseDto> exchange = restTemplate.exchange(
            uri,
            HttpMethod.POST,
            new HttpEntity<>(multiValueMap, httpHeaders),
            new ParameterizedTypeReference<>() {
            }
        );

        return exchange.getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] loadFile(String fileName) {
        URI uri = UriComponentsBuilder
            .fromUriString(gatewayProperties.getUrl())
            .path("/api/images/{fileName}")
            .encode()
            .build()
            .expand(fileName)
            .toUri();

        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<byte[]> exchange;
        try {
            exchange = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>() {
                });
        } catch (Exception e) {
            byte[] byteFile;
            try {
                byteFile = Files.readAllBytes(noImageFile);
            } catch (IOException e1) {
                throw new ValidationException("파일을 찾을수 없습니다");
            }
            return byteFile;
        }

        return exchange.getBody();
    }

    /**
     * MultipartFile을 ByteArrayResource로 래핑합니다.
     *
     * @param image           MultipartFile
     * @param multiValueMap   MultiValueMap
     * @param multipartHeader HttpHeaders
     * @throws IOException MultipartFile.getByte()
     */
    private void fileToByteArrayResource(MultipartFile image,
        MultiValueMap<String, Object> multiValueMap, HttpHeaders multipartHeader)
        throws IOException {
        if (image != null) {
            ByteArrayResource thumbnailAsResource = new ByteArrayResource(image.getBytes()) {
                @Override
                public String getFilename() {
                    return image.getOriginalFilename();
                }
            };
            multiValueMap.add("image", new HttpEntity<>(thumbnailAsResource, multipartHeader));
        }
    }
}

package com.nhnacademy.inkbridge.front.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * class: RedisConfig.
 *
 * @author devminseo
 * @version 3/2/24
 */
@EnableRedisHttpSession
@Configuration
@RequiredArgsConstructor
public class RedisConfig implements BeanClassLoaderAware {
    @Value("${inkbridge.redis.host}")
    private String host;
    @Value("${inkbridge.redis.port}")
    private String port;
    @Value("${inkbridge.redis.password}")
    private String password;
    @Value("${inkbridge.redis.database}")
    private String database;
    private ClassLoader classLoader;
    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    /**
     * redis 연결 위한 빈 설정
     *
     * @return factory 반환
     */
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName(host);
        configuration.setPort(Integer.parseInt(port));
        configuration.setPassword(password);
        configuration.setDatabase(Integer.parseInt(database));

        return new LettuceConnectionFactory(configuration);
    }

    /**
     * redis 직렬화 설정 메서드.
     *
     * @return redis 서버에 crud 가능한 객체 반환
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());
        redisTemplate.setDefaultSerializer(new StringRedisSerializer());

        return redisTemplate;
    }
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }
}

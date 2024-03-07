package com.nhnacademy.inkbridge.front.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.security.jackson2.SecurityJackson2Modules;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * class: RedisConfig.
 *
 * @author devminseo
 * @version 3/2/24
 */
@EnableRedisHttpSession
@Configuration
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

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
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
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());

        return redisTemplate;
    }

    @Bean
    public RedisSerializer<Object> springSessionDefaultRedisSerializer() {
        return new GenericJackson2JsonRedisSerializer(objectMapper());
    }

    private ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModules(SecurityJackson2Modules.getModules(classLoader));

        return objectMapper;
    }
}

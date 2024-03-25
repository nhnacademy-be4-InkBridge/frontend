package com.nhnacademy.inkbridge.front.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.Duration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

/**
 *  class: RedisCacheConfig.
 *
 *  @author minm063
 *  @version 2024/03/25
 */
@Configuration
@EnableCaching
public class RedisCacheConfig {

    @Value("${inkbridge.redis.host}")
    private String host;
    @Value("${inkbridge.redis.port}")
    private String port;
    @Value("${inkbridge.redis.password}")
    private String password;
    @Value("${inkbridge.redis.database.cache}")
    private String database;

    @Bean
    public RedisConnectionFactory redisConnectionFactoryForCache() {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName(host);
        configuration.setPort(Integer.parseInt(port));
        configuration.setPassword(password);
        configuration.setDatabase(Integer.parseInt(database));

        return new LettuceConnectionFactory(configuration);
    }

    @Bean
    public CacheManager cacheManager() {
        GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer(
            objectMapper());

        RedisSerializationContext.SerializationPair<Object> serializationPair =
            RedisSerializationContext.SerializationPair.fromSerializer(serializer);

        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
            .serializeValuesWith(serializationPair)
            .entryTtl(Duration.ofMinutes(3L));

        return RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(
            redisConnectionFactoryForCache()).cacheDefaults(redisCacheConfiguration).build();
    }

    private ObjectMapper objectMapper() {
        PolymorphicTypeValidator validator = BasicPolymorphicTypeValidator.builder().allowIfSubType(
            Object.class).build();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.activateDefaultTyping(validator, DefaultTyping.NON_FINAL);
        return objectMapper;
    }
}

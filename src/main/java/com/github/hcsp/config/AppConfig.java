package com.github.hcsp.config;

import com.github.hcsp.entities.CacheValue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class AppConfig {
    @Bean
    RedisTemplate<String, CacheValue> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, CacheValue> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        return redisTemplate;
    }
}

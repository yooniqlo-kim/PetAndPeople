package com.ssafy.petandpeople.infrastructure.persistence.repository;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RedisRepository {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisRepository(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void save(String key, Object data) {
        redisTemplate.opsForValue().set(key,data);
    }

    public Object find(String redisKey) {
        return redisTemplate.opsForValue().get(redisKey);
    }

}

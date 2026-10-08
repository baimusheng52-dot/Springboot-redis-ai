package com.example.fourtextredis;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
public class RedisController {

    private final StringRedisTemplate redisTemplate;

    public RedisController(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PutMapping("/{key}")
    public String set(@PathVariable @NonNull String key, @RequestParam @NonNull String value) {
        redisTemplate.opsForValue().set(key, value);
        return value;
    }

    @GetMapping("/{key}")
    public ResponseEntity<String> get(@PathVariable @NonNull String key) {
        String value = redisTemplate.opsForValue().get(key);
        return value == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(value);
    }
}
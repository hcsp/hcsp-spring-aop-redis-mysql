package com.github.hcsp.aspects;

import com.github.hcsp.entities.CacheValue;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

@Aspect
@Configuration
public class CacheAspectRedis {
    @Autowired
    private RedisTemplate<String, CacheValue> redisTemplate;
    private final int MAX_CACHE_TIME = 1000;

    @Around("@annotation(com.github.hcsp.anno.Cache)")
    public Object cache(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String methodName = methodSignature.getName();
        CacheValue cacheValue = redisTemplate.opsForValue().get(methodName);
        if (cacheValue != null && (System.currentTimeMillis() - cacheValue.getUpdateAt() < MAX_CACHE_TIME)) {
            System.out.println("Data from cache!");
            return cacheValue.getData();
        } else {
            System.out.println("Data from database!");
            Object realValue = joinPoint.proceed();
            redisTemplate.opsForValue().set(methodName, new CacheValue(realValue, System.currentTimeMillis()));
            return realValue;
        }
    }
}

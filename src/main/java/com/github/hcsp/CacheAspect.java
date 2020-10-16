package com.github.hcsp;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;


@Configuration
@Aspect
public class CacheAspect {
    //    Map<String, Object> cache = new HashMap<>();
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Around("@annotation(com.github.hcsp.anno.Cache)")
    public Object cache(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getName();
        Object cacheValue = redisTemplate.opsForValue().get(methodName);
        if (cacheValue != null) {
            System.out.println("get value from cache!");
            return cacheValue;
        } else {
            System.out.println("get value from database!");
            return getRealData(joinPoint, methodName);
        }
    }

    public Object getRealData(ProceedingJoinPoint joinPoint, String methodName) {
        try {
            Object realValue = joinPoint.proceed();
            redisTemplate.opsForValue().set(methodName, realValue, 1L, TimeUnit.SECONDS);
            return realValue;
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        }
    }
}

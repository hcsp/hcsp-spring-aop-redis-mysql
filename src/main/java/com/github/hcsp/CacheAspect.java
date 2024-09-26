package com.github.hcsp;

import com.github.hcsp.anno.Cache;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

@Aspect
@Configuration
public class CacheAspect {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Around("@annotation(com.github.hcsp.anno.Cache)")
    public Object cache(ProceedingJoinPoint joinPoint) throws Throwable {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        int time = signature.getMethod().getAnnotation(Cache.class).cacheSeconds();

        String methodName = signature.getName();

        Object cacheValue = redisTemplate.opsForValue().get(methodName);

        if (cacheValue != null) {
            System.out.println("get value from cache!");
            return cacheValue;
        } else {
            System.out.println("get value from database!");
            Object realValue = joinPoint.proceed();
            redisTemplate.opsForValue().set(methodName, realValue, time, TimeUnit.SECONDS);
            return realValue;
        }
    }
}

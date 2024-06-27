package com.github.hcsp.aspects;

import com.github.hcsp.entities.CacheValue;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CacheAspectMemory {

    private final int MAX_CACHE_TIME = 1000;
    private final Map<String, CacheValue> cache = new ConcurrentHashMap<>();

    @Around("@annotation(com.github.hcsp.anno.Cache)")
    public Object cache(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String methodName = methodSignature.getName();
        CacheValue cacheValue = cache.get(methodName);
        if (cacheValue != null && (System.currentTimeMillis() - cacheValue.getUpdateAt() < MAX_CACHE_TIME)) {
            System.out.println("Data from cache!");
            return cacheValue.getData();
        } else {
            System.out.println("Data from database!");
            Object realValue = joinPoint.proceed();
            cache.put(methodName, new CacheValue(realValue, System.currentTimeMillis()));
            return realValue;
        }
    }
}

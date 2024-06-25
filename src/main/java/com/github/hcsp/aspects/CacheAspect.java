package com.github.hcsp.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Aspect
@Configuration
public class CacheAspect {
    private static class CacheValue {
        private Object data;
        private long updateAt;

        CacheValue(Object data, long updateAt) {
            this.data = data;
            this.updateAt = updateAt;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }

        public long getUpdateAt() {
            return updateAt;
        }

        public void setUpdateAt(long updateAt) {
            this.updateAt = updateAt;
        }
    }

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

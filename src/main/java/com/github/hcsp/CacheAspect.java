package com.github.hcsp;

import com.github.hcsp.entity.GoodsItem;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 切面处理
 * 缓存热点数据
 */
@Aspect
@Configuration
public class CacheAspect {
    @Autowired
    RedisTemplate<String, List<GoodsItem>> redisTemplate;

    //声明标志  缓存注解
    @Around("@annotation(com.github.hcsp.anno.Cache)")
    public List<GoodsItem> Catch(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        //拿到调用的方法名与参数  进行反射 增加方法
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        String methodName = signature.getName();

        //从缓存中拿到参数方法名 判断是否存在  存在则返回缓存中的  不存在则从缓存中创建 并返回
        List<GoodsItem> catchValue = redisTemplate.opsForValue().get(methodName);

        if (catchValue != null) {
            System.out.println("catch value !");
            return catchValue;
        } else {
            System.out.println("real value !");
            List<GoodsItem> realValue = (List<GoodsItem>) proceedingJoinPoint.proceed();
            //一秒以内的重复请求使用缓存数据
            redisTemplate.opsForValue().set(methodName, realValue, 1, TimeUnit.SECONDS);
            return realValue;
        }

    }
}

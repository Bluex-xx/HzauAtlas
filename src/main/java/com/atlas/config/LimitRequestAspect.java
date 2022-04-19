package com.atlas.config;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.atlas.utils.HttpHelper;
import net.jodah.expiringmap.ExpirationPolicy;
import net.jodah.expiringmap.ExpiringMap;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Aspect
@Component
public class LimitRequestAspect {
    private static ConcurrentHashMap<String, ExpiringMap<String, Integer>> book = new ConcurrentHashMap<>();

    @Pointcut("@annotation(limitRequest)")
    public void excudeService(LimitRequest limitRequest) {
    }

    @Around("excudeService(limitRequest)")
    public Object doAround(ProceedingJoinPoint pjp, LimitRequest limitRequest) throws Throwable {

        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();

        String body = HttpHelper.getBodyString(request);
        Map<String, Object> bodyMap = HttpHelper.getBodyMap(request);
        System.out.println("body: " + body);
        System.out.println("bodyMap: " + bodyMap);
        String uid=bodyMap.get("uid").toString();
        System.out.println(uid);

        ExpiringMap<String, Integer> uc = book.getOrDefault(request.getRequestURI(), ExpiringMap.builder().variableExpiration().build());
        Integer uCount = uc.getOrDefault(uid, 0);
        System.out.println("count:"+uCount);

        if (uCount >= limitRequest.count()) {
            return "接口请求超过次数";
            //return JsonResult.error("请求次数过多，已经限制");
        } else if (uCount == 0) { // 第一次请求时，设置有效时间
            uc.put(uid, uCount + 1, ExpirationPolicy.CREATED, limitRequest.time(), TimeUnit.MILLISECONDS);
        } else {
            uc.put(uid, uCount + 1);
        }
        book.put(request.getRequestURI(), uc);

        Object result = pjp.proceed();
        //System.out.println("re:"+result);
        return result;
    }
}

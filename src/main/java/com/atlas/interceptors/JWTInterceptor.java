package com.atlas.interceptors;

import com.atlas.utils.JWTUtils;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class JWTInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token=request.getHeader("Authorization");
        Map<String,Object> map=new HashMap<>();
        try{
            DecodedJWT vertify= JWTUtils.vertify(token);
            map.put("status",true);
            map.put("msg","请求成功");
            return true;
        }catch (SignatureVerificationException e){
            map.put("status",false);
            map.put("msg","无效签名");
        }catch (TokenExpiredException e){
            map.put("status",false);
            map.put("msg","token过期");
        }catch (AlgorithmMismatchException e){
            map.put("status",false);
            map.put("msg","token算法不一致");
        }catch (Exception e){
            map.put("status",false);
            map.put("msg","token无效");
        }
        String json=new ObjectMapper().writeValueAsString(map);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
        return false;
    }
}

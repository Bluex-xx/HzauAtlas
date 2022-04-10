package com.atlas.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

public class JWTUtils {
    private static final String SING="32798%$*&g678";

    public static String getToken(String response){
        Calendar instance=Calendar.getInstance();
        instance.add(Calendar.DATE,30);
        JWTCreator.Builder builder= JWT.create();
        builder.withClaim("response",response);
        String token=builder.withExpiresAt(instance.getTime()).sign(Algorithm.HMAC256(SING));
        return token;
    }

    public static DecodedJWT vertify(String token){
        return JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
    }
}

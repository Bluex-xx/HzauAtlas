package com.atlas.config;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface LimitRequest {
    long time() default 6000;
    int count() default 1;
}

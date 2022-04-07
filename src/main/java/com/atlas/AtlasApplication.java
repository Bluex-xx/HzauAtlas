package com.atlas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AtlasApplication {
    public static void main(String[] args) {
        SpringApplication.run(AtlasApplication.class);
        System.out.println("启动成功");
    }
}

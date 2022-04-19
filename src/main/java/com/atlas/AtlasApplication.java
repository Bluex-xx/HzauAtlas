package com.atlas;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ServletComponentScan
public class AtlasApplication {
    public static void main(String[] args) {
        SpringApplication.run(AtlasApplication.class);
        System.out.println("启动成功");
    }
}

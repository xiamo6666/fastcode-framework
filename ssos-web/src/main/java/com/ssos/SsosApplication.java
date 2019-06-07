package com.ssos;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan({"com.ssos.web.mapper", "com.ssos.base.mapper"})
public class SsosApplication {
    public static void main(String[] args) {
        SpringApplication.run(SsosApplication.class, args);
    }
}

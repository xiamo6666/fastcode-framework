package com.fc.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author : xwl
 * @version : 1.0
 * @className : SystemApplication
 * @description : dto
 * @date : 2022/4/20 21:26
 */
@SpringBootApplication(scanBasePackages = "com.fc")
public class SystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class, args);
    }
}

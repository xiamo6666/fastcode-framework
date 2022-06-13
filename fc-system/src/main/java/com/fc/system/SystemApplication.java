package com.fc.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author xwl
 * @version 1.0
 * @since 2022/6/8 15:19
 */
@SpringBootApplication(scanBasePackages = "com.fc")
@EnableDiscoveryClient
@MapperScan(basePackages = {"com.fc.system.**.mapper"})
public class SystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class, args);
    }
}

package com.ssos.formengine;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.ssos.formengine.mapper")
public class FormEngineApplication {
	public static void main(String[] args) {
		SpringApplication.run(FormEngineApplication.class, args);
	}
}

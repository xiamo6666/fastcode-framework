package com.ssos.formenginv2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan({"com.ssos.formenginv2.mapper","com.ssos.base.mapper"})
public class FormEngineV2Application {
	public static void main(String[] args) {
		SpringApplication.run(FormEngineV2Application.class, args);
	}
}

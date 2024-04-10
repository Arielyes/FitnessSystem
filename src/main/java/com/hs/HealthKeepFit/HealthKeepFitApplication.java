package com.hs.HealthKeepFit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hs.HealthKeepFit.dao")
public class HealthKeepFitApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthKeepFitApplication.class, args);
	}

}

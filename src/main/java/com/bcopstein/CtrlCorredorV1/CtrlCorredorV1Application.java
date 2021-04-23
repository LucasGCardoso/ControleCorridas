package com.bcopstein.CtrlCorredorV1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = { "com.bcopstein" })
@EntityScan(basePackages = { "com.bcopstein" })
@EnableJpaRepositories(basePackages = {"com.bcopstein"})
public class CtrlCorredorV1Application {	
	public static void main(String[] args) {
		SpringApplication.run(CtrlCorredorV1Application.class, args);
	}

}

package com.insys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan //({"com.insys.model", "com.insys.controller"})
//@EntityScan
@EnableJpaRepositories
public class InsysApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(InsysApplication.class, args);
	}

}

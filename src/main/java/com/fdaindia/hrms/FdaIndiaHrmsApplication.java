package com.fdaindia.hrms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.fdaindia.hrms"})
public class FdaIndiaHrmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FdaIndiaHrmsApplication.class, args);
	}

	
}

package com.fund;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MyFundApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyFundApplication.class, args);
	}

}

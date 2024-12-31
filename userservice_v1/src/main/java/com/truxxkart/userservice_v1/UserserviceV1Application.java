package com.truxxkart.userservice_v1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class UserserviceV1Application {

	public static void main(String[] args) {
		SpringApplication.run(UserserviceV1Application.class, args);
	}

}

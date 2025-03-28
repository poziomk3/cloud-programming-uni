package com.poziomk3.user_man_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.poziomk3.user_man_service"
)
public class UserManServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserManServiceApplication.class, args);
    }

}

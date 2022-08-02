package com.deal2u;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
// @ComponentScan("com.deal2u.controller")
public class Deal2uApplication {
    public static void main(String[] args) {
        SpringApplication.run(Deal2uApplication.class, args);
    }
}

package com.yushun.figure.company;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.yushun")
public class ServiceCompanyApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceCompanyApplication.class, args);
    }
}

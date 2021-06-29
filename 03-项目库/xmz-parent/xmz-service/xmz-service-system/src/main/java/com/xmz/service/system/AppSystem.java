package com.xmz.service.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AppSystem {
    public static void main(String[] args) {
        SpringApplication.run(AppSystem.class,args);
    }
}

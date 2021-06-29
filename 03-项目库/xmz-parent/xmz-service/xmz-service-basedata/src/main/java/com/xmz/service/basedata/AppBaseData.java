package com.xmz.service.basedata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AppBaseData {
    public static void main(String[] args) {
        SpringApplication.run(AppBaseData.class,args);
    }
}

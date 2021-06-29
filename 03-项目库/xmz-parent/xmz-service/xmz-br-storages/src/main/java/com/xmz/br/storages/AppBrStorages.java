package com.xmz.br.storages;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class AppBrStorages {
    public static void main(String[] args) {
        SpringApplication.run(AppBrStorages.class,args);
    }
}

package com.xmz.service.carsparts.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.xmz.common.serviceBase")
@MapperScan("com.xmz.service.carsparts.mapper")
public class AppConfiguration {

}

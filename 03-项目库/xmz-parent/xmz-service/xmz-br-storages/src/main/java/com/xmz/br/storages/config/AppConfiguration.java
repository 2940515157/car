package com.xmz.br.storages.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.xmz.common.serviceBase")
@MapperScan("com.xmz.br.storages.mapper")
public class AppConfiguration {

}

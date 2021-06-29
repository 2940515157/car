package com.xmz.br.basedata.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@Configuration
//@MapperScan("com.xmz.br.basedata.mapper")
@MapperScan("com.xmz.br.basedata.mapper")
// 扫描指定的组件位置
//@ComponentScan(basePackages = "com.xmz")
@ComponentScan(basePackages = "com.xmz.common.serviceBase")
//开启nacos服务与注册
//@EnableDiscoveryClient
//开启feign
//@EnableFeignClients(basePackages = {"com.xmz.br.basedata.service"})
// 自定义数据源一定要排除SpringBoot自动配置数据源，不然会出现循环引用的问题，The dependencies of some of the beans in the application context form a cycle
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
// 开启security注解配置
//@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class HrManagementConfig {

    // 加入逻辑删除的组件
//    @Bean
//    public ISqlInjector sqlInjector() {
//        return new LogicSqlInjector();
//    }

    // 加入分页插件
//    @Bean
//    public PaginationInterceptor paginationInterceptor() {
//        return new PaginationInterceptor();
//    }

}

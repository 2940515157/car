package com.xmz.service.demo.controller;

import com.xmz.common.utils.Result;
import com.xmz.service.demo.entity.pojo.Demo;
import com.xmz.service.demo.service.IDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exampleDemo/demo")
public class DemoController {

    @Autowired
    IDemoService demoService;

    @Value("${spring.datasource.url}")
    String datasourceURL;

    @GetMapping("/getD")
    Result<Demo> getD(){
        return Result.getOKInstance(demoService.getD());
    }
    @GetMapping("/getConfigInDatasourceURL")
    Result<Object> getConfigInDatasourceURL(){
        return Result.getOKInstance().data(datasourceURL);
    }

    @GetMapping("/error")
    void error1(){
        System.out.println(0/0);
    }

    @GetMapping("/auth")
    Result<Object> auth(){
        return Result.getErrorInstance(50014,"你的登录令牌已过期！！");
    }


}

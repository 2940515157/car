package com.xmz.service.feigndemo.controller;

import com.xmz.common.utils.Result;
import com.xmz.service.feigndemo.entity.pojo.Demo;
import com.xmz.service.feigndemo.service.FDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @Autowired
    FDemoService demoService;

    @GetMapping("/getD")
    Result<Demo> getD(){
        return demoService.getD();
    }
    @GetMapping("/getConfigInDatasourceURL")
    Result<Object> getConfigInDatasourceURL(){
        return demoService.getConfigInDatasourceURL();
    }

}

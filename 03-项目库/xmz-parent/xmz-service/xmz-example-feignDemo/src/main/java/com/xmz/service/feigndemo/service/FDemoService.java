package com.xmz.service.feigndemo.service;

import com.xmz.common.utils.Result;
import com.xmz.service.feigndemo.entity.pojo.Demo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "service-demo")
@RequestMapping("/demo")
public interface FDemoService {
    @GetMapping("/getD")
    Result<Demo> getD();

    @GetMapping("/getConfigInDatasourceURL")
    Result<Object> getConfigInDatasourceURL();

}

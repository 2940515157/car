package com.xmz.service.carsparts.service;

import com.xmz.common.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@FeignClient(name = "serviceBasedata", contextId = "dictClient")
@RequestMapping("/serviceBasedata/dictentry")
public interface DictFeignService {

    //根据dicttypeid查
    @GetMapping("/queryTypeByIdList/{dicttypeid}")
    public Result<List> queryTypeByIdList(@PathVariable String dicttypeid);
}

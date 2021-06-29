package com.xmz.service.hr.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(value = "serviceSystem")
@RequestMapping("/serviceSystem/sys-resource")
public interface FResourceService {


    @PostMapping("/getRes/{id}")
    List<String> getRes(@PathVariable String id);


}

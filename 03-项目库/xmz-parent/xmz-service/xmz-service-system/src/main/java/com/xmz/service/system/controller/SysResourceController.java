package com.xmz.service.system.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xmz.common.utils.Result;
import com.xmz.service.system.entity.pojo.SysResource;
import com.xmz.service.system.entity.vo.SysResourceQuery;
import com.xmz.service.system.entity.vo.SysResourceVo;
import com.xmz.service.system.service.ISysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 资源 前端控制器
 * </p>
 *
 * @author KONG_No001
 * @since 2021-05-28
 */
@RestController
@RequestMapping("/serviceSystem/sys-resource")
public class SysResourceController {

    @Autowired
    ISysResourceService service;

    @GetMapping("/get/{resourceId}")
    Result<SysResource> list(@PathVariable String resourceId){
        return Result.getOKInstance(service.getById(resourceId));
    }

    @GetMapping("/queryResource")
    Result<List<SysResourceVo>> queryResource(SysResourceQuery resourceQuery){
        System.out.println(resourceQuery);
        return Result.getOKInstance(service.queryResource(resourceQuery));

    }

    @PostMapping("/add")
    Result<Object> add(@RequestBody SysResource sysResource){
        return Result.getOKInstance(service.save(sysResource));
    }

    @PutMapping("/update")
    Result<Object> update(@RequestBody SysResource sysResource){
        return Result.getOKInstance(service.updateById(sysResource));
    }

    @DeleteMapping("/delete/{resourceId}")
    Result<Object> delete(@PathVariable String resourceId){
        return Result.getOKInstance(service.removeById(resourceId));
    }

    @PostMapping("/getRes/{id}")
    List<String> getRes(@PathVariable String id){
        return service.getRes(id);
    }

}


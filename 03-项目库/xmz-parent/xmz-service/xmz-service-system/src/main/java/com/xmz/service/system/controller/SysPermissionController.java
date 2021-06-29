package com.xmz.service.system.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.common.utils.Result;
import com.xmz.common.utils.ResultMap;
import com.xmz.service.system.entity.pojo.SysPermission;
import com.xmz.service.system.entity.vo.SysOperationInfiniteListVo;
import com.xmz.service.system.entity.vo.SysPermissionListVo;
import com.xmz.service.system.entity.vo.SysPermissionQuery;
import com.xmz.service.system.service.ISysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 权限 前端控制器
 * </p>
 *
 * @author 罗俊
 * @since 2021-06-01
 */
@RestController
@RequestMapping("/serviceSystem/sys-permission")
public class SysPermissionController {

    @Autowired
    ISysPermissionService service;

    @GetMapping("/list/{current}/{size}")
    public ResultMap list(
            @PathVariable Integer current, @PathVariable Integer size,
            SysPermissionQuery sysPermission
    ){
        Page<SysPermissionListVo> page = service.listPage(new Page<>(current,size),sysPermission);
        return ResultMap.getOKInstance()
                .putData("records",page.getRecords())
                .putData("total",page.getTotal())
                .putData("current",page.getCurrent())
                .putData("size",page.getSize())
                .putData("pages",page.getPages());
    }

    @PutMapping("/batchEdit")
    public Result<Object> batchEdit(@RequestBody Map<String,String> data){
        SysPermission duplicate = JSONObject.parseObject(data.get("duplicate"),SysPermission.class);
        List<SysPermission> list = JSONObject.parseArray(data.get("list"),SysPermission.class);
        if(service.batchEdit(duplicate,list))
        return Result.getOKInstance();
        return Result.getErrorInstance();
    }

}


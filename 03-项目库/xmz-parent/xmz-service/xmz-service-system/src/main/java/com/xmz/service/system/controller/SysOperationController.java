package com.xmz.service.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.common.utils.Result;
import com.xmz.common.utils.ResultMap;
import com.xmz.service.system.entity.pojo.SysOperation;
import com.xmz.service.system.entity.pojo.SysPermission;
import com.xmz.service.system.entity.vo.SysOperationInfiniteListVo;
import com.xmz.service.system.service.ISysOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.management.Query;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 页面操作按钮 前端控制器
 * </p>
 *
 * @author 罗俊
 * @since 2021-06-02
 */
@RestController
@RequestMapping("/serviceSystem/sys-operation")
public class SysOperationController {
    @Autowired
    ISysOperationService service;

    @GetMapping("/queryPage/{current}/{size}")
    public ResultMap queryPage(
            @PathVariable Integer current,
            @PathVariable Integer size,
            SysOperation sysOperation){
        Page<SysOperation> page = new Page<SysOperation>(current,size);
        service.page(page, new QueryWrapper<SysOperation>()
                .select("`OPERATION_ID` `operationId`","`NAME` `name`",
                        "`OPERATION_NO` `operationNo`","`SEQ_NO` `seqNo`",
                        "`MEMO` `memo`","IS_ACTIVE isActive")
                .like(sysOperation.getName()!=null,"NAME",sysOperation.getName())
                .like(sysOperation.getMemo()!=null,"MEMO",sysOperation.getMemo())
        );

        return ResultMap.getOKInstance()
                .putData("records",page.getRecords())
                .putData("total",page.getTotal())
                .putData("current",page.getCurrent())
                .putData("size",page.getSize())
                .putData("pages",page.getPages())
                ;

    }

    @GetMapping("/infiniteList/{resourceId}/{current}/{size}")
    public ResultMap infiniteList(
            @PathVariable Integer current, @PathVariable Integer size,
            @PathVariable String resourceId,
            String name){
        Page<SysOperationInfiniteListVo> page = new Page<>(current,size);
        service.infiniteList(page,resourceId,name);
        return ResultMap.getOKInstance()
                .putData("records",page.getRecords())
                .putData("total",page.getTotal())
                .putData("current",page.getCurrent())
                .putData("size",page.getSize())
                .putData("pages",page.getPages());
    }

    @GetMapping("/get/{id}")
    public Result<SysOperation> get(@PathVariable String id){
        return Result.getOKInstance(service.getById(id));
    }

    @PostMapping("/save")
    public Result<Object> save(@RequestBody SysOperation sysOperation){
        if(service.save(sysOperation))
            return Result.getOKInstance();
        return Result.getErrorInstance();
    }
    @PutMapping("/update")
    public Result<Object> update(@RequestBody SysOperation sysOperation){
        if(service.updateById(sysOperation))
            return Result.getOKInstance();
        return Result.getErrorInstance();
    }
    @DeleteMapping("/delete/{id}")
    public Result<Object> update(@PathVariable String id){
        if(service.removeById(id))
            return Result.getOKInstance();
        return Result.getErrorInstance();
    }

}


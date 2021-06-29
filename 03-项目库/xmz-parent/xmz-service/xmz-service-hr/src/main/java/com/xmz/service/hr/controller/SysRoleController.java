package com.xmz.service.hr.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mysql.cj.util.StringUtils;
import com.xmz.common.utils.Result;
import com.xmz.common.utils.ResultMap;
import com.xmz.service.hr.entity.pojo.SysRole;
import com.xmz.service.hr.entity.pojo.SysRolePermission;
import com.xmz.service.hr.service.ISysRolePermissionService;
import com.xmz.service.hr.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色 前端控制器
 * </p>
 *
 * @author 罗俊
 * @since 2021-06-09
 */
@RestController
@RequestMapping("/serviceHr/sys-role")
public class SysRoleController {

    @Autowired
    ISysRoleService roleService;

    @Autowired
    ISysRolePermissionService rolePermissionService;

    @GetMapping("/list/{current}/{size}")
    public ResultMap listPage(
            @PathVariable Integer current, @PathVariable Integer size,
            SysRole role){

        Page<SysRole> page = new Page<>(current,size);
        roleService.page(page,new QueryWrapper<SysRole>()
                .select("ROLE_ID roleId","NAME name","ROLE_NO roleNo","MEMO memo","ROLE_ORIGIN roleOrigin","IS_ACTIVE isActive")
                .like(!StringUtils.isNullOrEmpty(role.getName()),"NAME",role.getName())
                .like(!StringUtils.isNullOrEmpty(role.getRoleNo()),"ROLE_NO",role.getRoleNo())
        );

        return ResultMap.getOKInstance()
                .putData("records",page.getRecords())
                .putData("total",page.getTotal())
                .putData("current",page.getCurrent())
                .putData("size",page.getSize())
                .putData("pages",page.getPages());

    }

    @GetMapping("/get/{id}")
    public Result get(@PathVariable String id){
        return Result.getOKInstance(roleService.getById(id));
    }

    @PostMapping("/save")
    public Result save(@RequestBody SysRole role){
        if(roleService.save(role)) return Result.getOKInstance();
        return Result.getErrorInstance();
    }
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable String id){
        if(roleService.removeById(id)) return Result.getOKInstance();
        return Result.getErrorInstance();
    }

    @PutMapping("/update")
    public Result update(@RequestBody SysRole role){
        if(roleService.updateById(role)) return Result.getOKInstance();
        return Result.getErrorInstance();
    }

    @GetMapping("/getPrs/{roleId}/{resId}")
    public ResultMap getPrs(@PathVariable String resId, @PathVariable String roleId){
        return ResultMap.getOKInstance(roleService.getPrs(roleId,resId));
    }

    @PostMapping("/updatePrs")
    public Result updatePrs(@RequestBody Map<String,String> data){
        List<SysRolePermission> prs = JSONObject.parseArray(data.get("prs"), SysRolePermission.class);
        System.out.println(prs);
        if(rolePermissionService.saveOrUpdateBatch(prs)) return Result.getOKInstance();
        return Result.getErrorInstance();

    }


}


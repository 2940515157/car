package com.xmz.service.hr.controller;


import com.xmz.common.utils.Result;
import com.xmz.service.hr.entity.pojo.SysAccountRole;
import com.xmz.service.hr.service.ISysAccountRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 用户账号-角色 前端控制器
 * </p>
 *
 * @author 罗俊
 * @since 2021-06-15
 */
@RestController
@RequestMapping("/serviceHr/sys-account-role")
public class SysAccountRoleController {

    @Autowired
    ISysAccountRoleService accountRoleService;

    @PostMapping("/barthSaveOrUpdate")
    public Result<Object> barthSaveOrUpdate(@RequestBody List<SysAccountRole> data){
        if(accountRoleService.saveOrUpdateBatch(data))
            return Result.getOKInstance();
        return Result.getErrorInstance();
    }

}


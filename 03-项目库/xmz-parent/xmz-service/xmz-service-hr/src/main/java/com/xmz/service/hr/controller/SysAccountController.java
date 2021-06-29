package com.xmz.service.hr.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.common.utils.Result;
import com.xmz.common.utils.ResultMap;
import com.xmz.service.hr.entity.pojo.SysAccount;
import com.xmz.service.hr.entity.vo.SysAccountQuery;
import com.xmz.service.hr.entity.vo.SysRoleGrantVo;
import com.xmz.service.hr.service.ISysAccountService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author 罗俊
 * @since 2021-06-10
 */
@RestController
@RequestMapping("/serviceHr/sys-account")
public class SysAccountController {

    @Autowired
    ISysAccountService accountService;

    @ApiOperation("查询所有领卡人")
    @PostMapping("/queryAllSa")
    public ResultMap queryAllSa() {
        List<SysAccount> list = accountService.queryAllSa();
        return ResultMap.getOKInstance()
                .putData("list", list);
    }

    @GetMapping("/queryPage/{current}/{size}")
    public ResultMap queryPage(
            @PathVariable Integer current, @PathVariable Integer size,
            SysAccountQuery accountQuery) {
        Page<Map<String, Object>> page = new Page<>(current, size);

        accountService.queryPage(page,accountQuery);

        return ResultMap.getOKInstance()
                .putData("records",page.getRecords())
                .putData("total",page.getTotal())
                .putData("current",page.getCurrent())
                .putData("size",page.getSize())
                .putData("pages",page.getPages());
    }

    @GetMapping("/get/{id}")
    public Result<Object> get(@PathVariable String id){
        return Result.getOKInstance(accountService.getById(id));
    }

    @PostMapping("/save")
    public Result<Object> save(@RequestBody SysAccount account){
        account.setStatus("locking");
        if(accountService.addAccount(account))return Result.getOKInstance();
        return Result.getErrorInstance();

    }

    @PutMapping("/update")
    public Result<Object> update(@RequestBody SysAccount account){
        if(accountService.updateById(account)) return Result.getOKInstance();
        return Result.getErrorInstance();
    }

    @DeleteMapping("/remove/{id}")
    public Result<Object> remove(@PathVariable String id){
        if(accountService.removeById(id)) return Result.getOKInstance();
        return Result.getErrorInstance();
    }

    @PutMapping("/respwd/{id}")
    public Result<Object> resPassword(@PathVariable String id){
        SysAccount sysAccount = new SysAccount();
        sysAccount.setAccountId(id);
        sysAccount.setPassword("123456");
        sysAccount.setPasswordInitFlag("Y");
        if(accountService.updateById(sysAccount)) return Result.getOKInstance();
        return Result.getErrorInstance();
    }

    @PutMapping("/locking/{id}")
    public Result<Object> locking(@PathVariable String id){
        SysAccount sysAccount = new SysAccount();
        sysAccount.setAccountId(id);
        sysAccount.setStatus("locking");
        if(accountService.updateById(sysAccount)) return Result.getOKInstance();
        return Result.getErrorInstance();
    }

    @PutMapping("/unlocking/{id}")
    public Result<Object> unlocking(@PathVariable String id){
        SysAccount sysAccount = new SysAccount();
        sysAccount.setAccountId(id);
        sysAccount.setStatus("normal");
        if(accountService.updateById(sysAccount)) return Result.getOKInstance();
        return Result.getErrorInstance();
    }

    @GetMapping("/RoleGrant/infiniteList/{accountId}/{current}/{size}")
    public ResultMap infiniteList(String name,
            @PathVariable Integer current,
            @PathVariable Integer size, @PathVariable String accountId){
        Page<SysRoleGrantVo> page = new Page<>(current,size);

        accountService.infiniteList(page,accountId,name);

        return ResultMap.getOKInstance()
                .putData("records",page.getRecords())
                .putData("total",page.getTotal())
                .putData("current",page.getCurrent())
                .putData("size",page.getSize())
                .putData("pages",page.getPages());

    }

}


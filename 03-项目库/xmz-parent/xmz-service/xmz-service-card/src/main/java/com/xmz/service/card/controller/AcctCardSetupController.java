package com.xmz.service.card.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.common.utils.ResultMap;
import com.xmz.service.card.config.PinYinUtil;
import com.xmz.service.card.entity.pojo.AcctCardSetup;
import com.xmz.service.card.entity.vo.AcctCardSetupQuery;

import com.xmz.service.card.entity.vo.MyAcctCardSetup;

import com.xmz.service.card.service.IAcctCardSetupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 储值卡产品定义 前端控制器
 * </p>
 *
 * @author liujiaxi
 * @since 2021-06-01
 */
@Api("储值卡产品定义")
@CrossOrigin
@RestController
@RequestMapping("/serviceCard/acct-card-setup")
public class AcctCardSetupController {
    @Autowired
    IAcctCardSetupService iAcctCardSetupService;



    @ApiOperation("分页条件查询储值卡产品定义")
    @PostMapping("queryAllAcct/{current}/{size}")
    public ResultMap queryAllAcct(@PathVariable Long current,
                                  @PathVariable Long size,
                                  @RequestBody AcctCardSetupQuery acctCardSetupQuery){
        Page<MyAcctCardSetup> brPackagePage = new Page<>(current, size);

        iAcctCardSetupService.queryAllAcct(brPackagePage,acctCardSetupQuery);
        return ResultMap.getOKInstance()
                .putData("rows",brPackagePage.getRecords())
                .putData("pages",brPackagePage.getPages())
                .putData("total",brPackagePage.getTotal())
                .putData("current",brPackagePage.getCurrent())
                .putData("size",brPackagePage.getSize());
    }



    //删除
    @ApiOperation("删除储值卡产品")
    @DeleteMapping("/delete/{id}")
    public ResultMap delete(@PathVariable String id){
        boolean flag=iAcctCardSetupService.removeById(id);
        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }

    @ApiOperation("上架储值卡产品")
    @PostMapping("/update1")
    public ResultMap update1(@RequestBody AcctCardSetup acctCardSetup){

        acctCardSetup.setStatus("up");

        boolean flag=iAcctCardSetupService.updateById(acctCardSetup);
        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }


    @ApiOperation("下架储值卡产品")
    @PostMapping("/update2")
    public ResultMap update2(@RequestBody AcctCardSetup acctCardSetup){

        acctCardSetup.setStatus("under");

        boolean flag=iAcctCardSetupService.updateById(acctCardSetup);
        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }

    @ApiOperation("强制退回储值卡产品")
    @PostMapping("/update3")
    public ResultMap update3(@RequestBody AcctCardSetup acctCardSetup){

        acctCardSetup.setStatus("init");

        boolean flag=iAcctCardSetupService.updateById(acctCardSetup);
        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }

    //添加
    @ApiOperation("增加储值卡产品")
    @PostMapping("/add")
    public ResultMap add(@RequestBody AcctCardSetup acctCardSetup){
        //拼音
        acctCardSetup.setPinyin(PinYinUtil.toPinyin(acctCardSetup.getAcsName()));
        //是否有效
        acctCardSetup.setIsActive("Y");
        //设置未上架
        acctCardSetup.setStatus("init");

        boolean flag=iAcctCardSetupService.save(acctCardSetup);
        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }


    //修改
    @ApiOperation("修改储值卡产品")
    @PostMapping("/update")
    public ResultMap update(@RequestBody AcctCardSetup acctCardSetup){
        boolean flag=iAcctCardSetupService.updateById(acctCardSetup);
        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }

    @ApiOperation("根据id查询储值卡产品")
    @PostMapping("/queryById/{id}")
    public ResultMap queryById(@PathVariable String id) {

        AcctCardSetup acctCardSetup=iAcctCardSetupService.getById(id);
        return ResultMap.getOKInstance()
                .putData("acctCardSetup",acctCardSetup);
    }




}


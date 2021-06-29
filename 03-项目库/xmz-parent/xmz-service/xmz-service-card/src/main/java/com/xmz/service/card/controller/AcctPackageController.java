package com.xmz.service.card.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.common.utils.ResultMap;
import com.xmz.service.card.config.PinYinUtil;
import com.xmz.service.card.entity.pojo.AcctCardSetup;
import com.xmz.service.card.entity.pojo.AcctCardStore;
import com.xmz.service.card.entity.pojo.AcctPackage;

import com.xmz.service.card.entity.pojo.AcctPackageDetail;
import com.xmz.service.card.entity.vo.MyAcctPackage;
import com.xmz.service.card.service.IAcctPackageDetailService;
import com.xmz.service.card.service.IAcctPackageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 套餐 前端控制器
 * </p>
 *
 * @author liujiaxi
 * @since 2021-06-03
 */
@Api("套餐产品")
@CrossOrigin
@RestController
@RequestMapping("/serviceCard/acct-package")
public class AcctPackageController {
    @Autowired
    IAcctPackageService iAcctPackageService;

    @Autowired
    IAcctPackageDetailService iAcctPackageDetailService;

    @ApiOperation("分页条件查询套餐产品")
    @PostMapping("queryAllAcctPackage/{current}/{size}")
    public ResultMap queryAllAcctPackage(@PathVariable Long current,
                                         @PathVariable Long size,
                                         @RequestBody AcctPackage acctPackage){
        Page<MyAcctPackage> brPackagePage = new Page<>(current, size);

        iAcctPackageService.queryAllAcctPackage(brPackagePage,acctPackage);
        return ResultMap.getOKInstance()
                .putData("rows",brPackagePage.getRecords())
                .putData("pages",brPackagePage.getPages())
                .putData("total",brPackagePage.getTotal())
                .putData("current",brPackagePage.getCurrent())
                .putData("size",brPackagePage.getSize());
    }


    //添加
    @ApiOperation("增加套餐产品")
    @PostMapping("/add")
    public ResultMap add(@RequestBody Map<String,String> data){

       boolean flag= iAcctPackageService.add(data);

        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }


    //修改
    @ApiOperation("修改套餐产品")
    @PostMapping("/update")
    public ResultMap update(@RequestBody AcctPackage acctPackage){
        boolean flag=iAcctPackageService.updateById(acctPackage);
        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }

    @ApiOperation("根据id查询套餐产品")
    @PostMapping("/queryById/{id}")
    public ResultMap queryById(@PathVariable String id) {

       AcctPackage acctPackage=iAcctPackageService.getById(id);
        return ResultMap.getOKInstance()
                .putData("acctPackage",acctPackage);
    }


    //删除
    @ApiOperation("删除套餐产品")
    @DeleteMapping("/delete/{id}")
    public ResultMap delete(@PathVariable String id){
        boolean flag=iAcctPackageService.removeById(id);
        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }


    @ApiOperation("上架储值卡产品")
    @PostMapping("/update1")
    public ResultMap update1(@RequestBody AcctPackage acctPackage){

        acctPackage.setStatus("up");

        boolean flag=iAcctPackageService.updateById(acctPackage);
        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }


    @ApiOperation("下架储值卡产品")
    @PostMapping("/update2")
    public ResultMap update2(@RequestBody AcctPackage acctPackage){

        acctPackage.setStatus("under");

        boolean flag=iAcctPackageService.updateById(acctPackage);
        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }


}


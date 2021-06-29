package com.xmz.service.card.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.common.utils.ResultMap;

import com.xmz.service.card.config.PinYinUtil;
import com.xmz.service.card.entity.pojo.AcctCardArea;
import com.xmz.service.card.entity.pojo.AcctCardSetup;
import com.xmz.service.card.entity.vo.MyAcctCardArea;
import com.xmz.service.card.service.IAcctCardAreaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 卡与使用区域关系表 前端控制器
 * </p>
 *
 * @author liujiaxi
 * @since 2021-06-03
 */
@Api("储值卡与使用区域关系表")
@CrossOrigin
@RestController
@RequestMapping("/serviceCard/acct-card-area")
public class AcctCardAreaController {
    @Autowired
    IAcctCardAreaService iAcctCardAreaService;

    @ApiOperation("分页条件查询卡与区域的关系")
    @PostMapping("queryAllPage/{current}/{size}/{acsUuid}")
    public ResultMap queryAllPage(@PathVariable Long current,
                                     @PathVariable Long size,
                                  @PathVariable String acsUuid){
        System.out.println("---------");
        System.out.println(acsUuid);
        Page<MyAcctCardArea> brPackagePage = new Page<>(current, size);

        iAcctCardAreaService.queryAllPage(brPackagePage,acsUuid);
        return ResultMap.getOKInstance()
                .putData("rows",brPackagePage.getRecords())
                .putData("pages",brPackagePage.getPages())
                .putData("total",brPackagePage.getTotal())
                .putData("current",brPackagePage.getCurrent())
                .putData("size",brPackagePage.getSize());
    }

    //添加
    @ApiOperation("增加选择范围")
    @PostMapping("/add")
    public ResultMap add(@RequestBody AcctCardArea acctCardArea){

        boolean flag=iAcctCardAreaService.save(acctCardArea);
        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }

    //删除
    @ApiOperation("删除卡与区域的关系")
    @DeleteMapping("/delete/{id}")
    public ResultMap delete(@PathVariable String id){
        boolean flag=iAcctCardAreaService.removeById(id);
        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }











}


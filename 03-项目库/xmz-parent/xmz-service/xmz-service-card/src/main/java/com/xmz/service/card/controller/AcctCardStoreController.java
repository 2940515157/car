package com.xmz.service.card.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.common.utils.ResultMap;
import com.xmz.service.card.config.PinYinUtil;
import com.xmz.service.card.entity.pojo.AcctCardSetup;
import com.xmz.service.card.entity.pojo.AcctCardStore;
import com.xmz.service.card.entity.vo.AcctBrCardQuery;
import com.xmz.service.card.entity.vo.AcctCardStoredQuery;
import com.xmz.service.card.entity.vo.MyAcctBrCard;
import com.xmz.service.card.entity.vo.MyAcctCardStored;
import com.xmz.service.card.service.IAcctCardStoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 卡库 前端控制器
 * </p>
 *
 * @author liujiaxi
 * @since 2021-06-09
 */
@Api("制卡管理")
@CrossOrigin
@RestController
@RequestMapping("/serviceCard/acct-card-store")
public class AcctCardStoreController {
    @Autowired
    IAcctCardStoreService iAcctCardStoreService;


    @ApiOperation("查询所有制卡管理")
    @PostMapping("queryAll")
    public ResultMap queryAll(){
        List<AcctCardStore> list=iAcctCardStoreService.list();

        return ResultMap.getOKInstance()
                .putData("list",list);
    }


    @ApiOperation("分页条件查询制卡管理")
    @PostMapping("queryAllZhiKa/{current}/{size}")
    public ResultMap queryAllZhiKa(@PathVariable Long current,
                                   @PathVariable Long size,
                                   @RequestBody AcctCardStoredQuery acctCardStoredQuery){
        Page<MyAcctCardStored> brPackagePage = new Page<>(current, size);

        iAcctCardStoreService.queryAllZhiKa(brPackagePage,acctCardStoredQuery);
        return ResultMap.getOKInstance()
                .putData("rows",brPackagePage.getRecords())
                .putData("pages",brPackagePage.getPages())
                .putData("total",brPackagePage.getTotal())
                .putData("current",brPackagePage.getCurrent())
                .putData("size",brPackagePage.getSize());
    }


    //添加
    @ApiOperation("增加制卡管理")
    @PostMapping("/add")
    public ResultMap add(@RequestBody AcctCardStore acctCardStore){

        boolean flag=iAcctCardStoreService.save(acctCardStore);
        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }


    //修改
    @ApiOperation("修改制卡管理")
    @PostMapping("/update")
    public ResultMap update(@RequestBody AcctCardStore acctCardStore){
        boolean flag=iAcctCardStoreService.updateById(acctCardStore);
        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }

    @ApiOperation("根据id查询制卡管理")
    @PostMapping("/queryById/{id}")
    public ResultMap queryById(@PathVariable String id) {

        AcctCardStore acctCardStore=iAcctCardStoreService.getById(id);
        return ResultMap.getOKInstance()
                .putData("acctCardStore",acctCardStore);
    }

    //删除
    @ApiOperation("删除制卡管理")
    @DeleteMapping("/delete/{id}")
    public ResultMap delete(@PathVariable String id){
        boolean flag=iAcctCardStoreService.removeById(id);
        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }



}


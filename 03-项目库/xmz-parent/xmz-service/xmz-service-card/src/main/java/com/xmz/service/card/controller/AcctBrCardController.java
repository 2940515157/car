package com.xmz.service.card.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.common.utils.ResultMap;
import com.xmz.service.card.entity.pojo.AcctBrCard;
import com.xmz.service.card.entity.pojo.AcctCardStore;
import com.xmz.service.card.entity.vo.AcctBrCardQuery;
import com.xmz.service.card.entity.vo.AcctCardSetupQuery;
import com.xmz.service.card.entity.vo.MyAcctBrCard;
import com.xmz.service.card.entity.vo.MyAcctCardSetup;
import com.xmz.service.card.service.IAcctBrCardService;
import com.xmz.service.card.service.IAcctCardStoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

/**
 * <p>
 * 门店领卡 前端控制器
 * </p>
 *
 * @author liujiaxi
 * @since 2021-06-07
 */
@Api("发卡管理")
@CrossOrigin
@RestController
@RequestMapping("/serviceCard/acct-br-card")
public class AcctBrCardController {
    @Autowired
    IAcctBrCardService iAcctBrCardService;

    @Autowired
    IAcctCardStoreService iAcctCardStoreService;

    @ApiOperation("分页条件查询发卡管理")
    @PostMapping("queryAllABrCard/{current}/{size}")
    public ResultMap queryAllABrCard(@PathVariable Long current,
                                     @PathVariable Long size,
                                     @RequestBody AcctBrCardQuery acctBrCardQuery){
        Page<MyAcctBrCard> brPackagePage = new Page<>(current, size);

        iAcctBrCardService.queryAllABrCard(brPackagePage,acctBrCardQuery);
        return ResultMap.getOKInstance()
                .putData("rows",brPackagePage.getRecords())
                .putData("pages",brPackagePage.getPages())
                .putData("total",brPackagePage.getTotal())
                .putData("current",brPackagePage.getCurrent())
                .putData("size",brPackagePage.getSize());
    }


    //添加  发卡
    @ApiOperation("增加发卡")
    @PostMapping("/add")//AcctBrCard
    public ResultMap add(@RequestBody Map<String,String> data){
        //添加发卡
         boolean  flag=iAcctBrCardService.add(data);

        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }

    @ApiOperation("作废发卡")
    @PostMapping("/update")//AcctBrCard
    public ResultMap update(@RequestBody AcctBrCard acctBrCard){
        //作废
        acctBrCard.setBrCardStatus("invalid");
        //作废发卡
        boolean  flag=iAcctBrCardService.updateById(acctBrCard);

        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }


}


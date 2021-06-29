package com.xmz.br.vipCard.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.br.vipCard.entity.pojo.*;
import com.xmz.br.vipCard.entity.vo.AcctBrCardQuery;
import com.xmz.br.vipCard.entity.vo.AcctCustCardQuery;
import com.xmz.br.vipCard.entity.vo.CustCustInfoQuery;
import com.xmz.br.vipCard.service.IAcctBrCardService;
import com.xmz.br.vipCard.service.IAcctCustCardService;
import com.xmz.common.utils.ResultMap;
import io.swagger.annotations.ApiOperation;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 客户卡 前端控制器
 * </p>
 *
 * @author zt
 * @since 2021-06-04
 */
@RestController
@RequestMapping("/brVipcard/acct-cust-card")
public class AcctCustCardController {

    @Autowired
    IAcctCustCardService iAcctCustCardService;

    @Autowired
    IAcctBrCardService iAcctBrCardService;

    @ApiOperation("获取会员卡列表(条件分页)")
    @PostMapping("/queryPage/{current}/{size}")
    public ResultMap queryPage(@RequestBody AcctCustCardQuery acctCustCardQuery,
                               @PathVariable Integer current,
                               @PathVariable Integer size){
        Page<MyAcctCustCard> page=new Page(current,size);
        iAcctCustCardService.myPage(page,acctCustCardQuery);
        return ResultMap.getOKInstance()
                .putData("rows",page.getRecords())
                .putData("pages",page.getPages())
                .putData("total",page.getTotal())
                .putData("current",page.getCurrent())
                .putData("size",page.getSize());
    }

    //添加点检项目
    @ApiOperation("增加会员开卡")
    @PostMapping("/add")
    public ResultMap add(@RequestBody Map<String,String> data) {
        boolean flag=iAcctCustCardService.add(data);
        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();

    }


    @ApiOperation("获取可充值的客户信息")
    @PostMapping("/queryPhone")
    public ResultMap queryPhone(@RequestBody AcctCustCardQuery acctCustCardQuery){
        List<MyAcctCustCard> list= iAcctCustCardService.queryPhone(acctCustCardQuery);
        return ResultMap.getOKInstance()
                .putData("list",list);

    }

    @ApiOperation("获取可转赠优惠券的用户列表(条件分页)")
    @PostMapping("/queryPage2/{current}/{size}")
    public ResultMap queryPage2(@RequestBody AcctCustCardQuery acctCustCardQuery,
                               @PathVariable Integer current,
                               @PathVariable Integer size){
        Page<MyAcctCustCard> page=new Page(current,size);
        iAcctCustCardService.myPage2(page,acctCustCardQuery);
        return ResultMap.getOKInstance()
                .putData("rows",page.getRecords())
                .putData("pages",page.getPages())
                .putData("total",page.getTotal())
                .putData("current",page.getCurrent())
                .putData("size",page.getSize());
    }


}


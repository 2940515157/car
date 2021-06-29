package com.xmz.br.vipCard.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.br.vipCard.entity.pojo.AcctRechargeRecord;
import com.xmz.br.vipCard.entity.pojo.MyAcctBrCard;
import com.xmz.br.vipCard.entity.pojo.MyAcctRechargeRecord;
import com.xmz.br.vipCard.entity.vo.AcctBrCardQuery;
import com.xmz.br.vipCard.entity.vo.AcctRechargeRecordQuery;
import com.xmz.br.vipCard.service.IAcctCustCardService;
import com.xmz.br.vipCard.service.IAcctRechargeRecordService;
import com.xmz.common.utils.ResultMap;
import io.swagger.annotations.ApiOperation;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * <p>
 * 充值记录表 前端控制器
 * </p>
 *
 * @author zt
 * @since 2021-06-09
 */
@RestController
@RequestMapping("/brVipcard/acct-recharge-record")
@CrossOrigin
public class AcctRechargeRecordController {
    @Autowired
    IAcctRechargeRecordService iAcctRechargeRecordService;

    @ApiOperation("获取会员卡充值记录列表(条件分页)")
    @PostMapping("/queryPage/{current}/{size}")
    public ResultMap queryPage(@RequestBody AcctRechargeRecordQuery acctRechargeRecordQuery,
                               @PathVariable Integer current,
                               @PathVariable Integer size){
        Page<MyAcctRechargeRecord> page=new Page(current,size);
        iAcctRechargeRecordService.myPage(page,acctRechargeRecordQuery);
        return ResultMap.getOKInstance()
                .putData("rows",page.getRecords())
                .putData("pages",page.getPages())
                .putData("total",page.getTotal())
                .putData("current",page.getCurrent())
                .putData("size",page.getSize());
    }

    //添加点检项目
    @ApiOperation("增加会员充值记录")
    @PostMapping("/add")
    public ResultMap add(@RequestBody MyAcctRechargeRecord myAcctRechargeRecord) {

        boolean flag=iAcctRechargeRecordService.add(myAcctRechargeRecord);
        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }



}


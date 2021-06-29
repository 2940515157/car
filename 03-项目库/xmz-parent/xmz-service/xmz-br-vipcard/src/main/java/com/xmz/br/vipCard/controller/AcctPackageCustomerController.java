package com.xmz.br.vipCard.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.br.vipCard.entity.pojo.AcctPackageCustomer;
import com.xmz.br.vipCard.entity.pojo.MyAcctBrCard;
import com.xmz.br.vipCard.entity.pojo.MyAcctCardSetup;
import com.xmz.br.vipCard.entity.pojo.MyAcctPackageCustomer;
import com.xmz.br.vipCard.entity.vo.AcctBrCardQuery;
import com.xmz.br.vipCard.entity.vo.AcctCardSetupQuery;
import com.xmz.br.vipCard.entity.vo.AcctPackageCustomerQuery;
import com.xmz.br.vipCard.service.IAcctPackageCustomerService;
import com.xmz.common.utils.ResultMap;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 客户已购买套餐 前端控制器
 * </p>
 *
 * @author zt
 * @since 2021-06-11
 */
@RestController
@RequestMapping("/brVipcard/acct-package-customer")
public class AcctPackageCustomerController {
    @Autowired
    IAcctPackageCustomerService iAcctPackageCustomerService;

    @ApiOperation("获取套餐销售列表(条件分页)")
    @PostMapping("/queryPage/{current}/{size}")
    public ResultMap queryPage(@RequestBody AcctPackageCustomerQuery acctPackageCustomerQuery,
                               @PathVariable Integer current,
                               @PathVariable Integer size){
        Page<MyAcctPackageCustomer> page=new Page(current,size);
        iAcctPackageCustomerService.myPage(page,acctPackageCustomerQuery);
        return ResultMap.getOKInstance()
                .putData("rows",page.getRecords())
                .putData("pages",page.getPages())
                .putData("total",page.getTotal())
                .putData("current",page.getCurrent())
                .putData("size",page.getSize());
    }

    @ApiOperation("增加套餐销售")
    @PostMapping("/add")
    public ResultMap add(@RequestBody Map<String,String> data) {
        boolean flag  =iAcctPackageCustomerService.add(data);
        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }

    @PostMapping("/queryDate")
    public ResultMap queryDate(@RequestBody AcctPackageCustomerQuery acctPackageCustomerQuery){
        AcctPackageCustomer acctPackageCustomer= iAcctPackageCustomerService.queryDate(acctPackageCustomerQuery);
        return ResultMap.getOKInstance()
                .putData("apc",acctPackageCustomer);

    }


}


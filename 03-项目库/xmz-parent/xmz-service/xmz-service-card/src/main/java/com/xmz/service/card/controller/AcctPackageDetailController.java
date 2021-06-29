package com.xmz.service.card.controller;


import com.alibaba.fastjson.JSONObject;
import com.xmz.common.utils.ResultMap;
import com.xmz.service.card.entity.pojo.AcctCoupon;
import com.xmz.service.card.entity.pojo.AcctCouponDetail;
import com.xmz.service.card.entity.pojo.AcctPackage;
import com.xmz.service.card.entity.pojo.AcctPackageDetail;
import com.xmz.service.card.entity.vo.MyAcctPackageDetail;
import com.xmz.service.card.service.IAcctPackageDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 套餐服务明细 前端控制器
 * </p>
 *
 * @author liujiaxi
 * @since 2021-06-18
 */
@Api("套餐服务明细")
@CrossOrigin
@RestController
@RequestMapping("/serviceCard/acct-package-detail")
public class AcctPackageDetailController {

    @Autowired
    IAcctPackageDetailService iAcctPackageDetailService;

    //添加
    @ApiOperation("增加套餐服务明细")
    @PostMapping("/add")
    public ResultMap add(@RequestBody Map<String,String> data){


        return ResultMap.getOKInstance();
    }

    @ApiOperation("通过套餐编号查询套餐服务")
    @GetMapping("/getApUuid/{apUuid}")
    public ResultMap getApUuid(
            @ApiParam(name = "apUuid",value = "套餐编号")
            @PathVariable String apUuid) {
        List<MyAcctPackageDetail> acctPackageDetail = iAcctPackageDetailService.getApUuid(apUuid);

        return ResultMap.getOKInstance().putData("acctPackageDetail",acctPackageDetail);
    }



}


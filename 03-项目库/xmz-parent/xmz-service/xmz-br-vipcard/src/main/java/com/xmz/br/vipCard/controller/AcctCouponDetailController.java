package com.xmz.br.vipCard.controller;


import com.xmz.br.vipCard.entity.pojo.AcctCouponDetailPojo;
import com.xmz.br.vipCard.entity.pojo.CouponServerVo;
import com.xmz.br.vipCard.service.AcctCouponDetailService;
import com.xmz.common.utils.ResultMap;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 优惠券服务明细 前端控制器
 * </p>
 *
 * @author LLY
 * @since 2021-06-02
 */
@RestController
@RequestMapping("/brVipcard/acct-cou-det")
public class AcctCouponDetailController{
    @Autowired
    AcctCouponDetailService acctCouponDetailService;

    @ApiOperation("查询该优惠券的所有服务")
    @PostMapping("/serverSelectAll")
    public ResultMap serverSelectAll(@ApiParam("条件参数") @RequestBody(required = false)
                                             AcctCouponDetailPojo acctCouponDetailPojo) {
        List<CouponServerVo> list = acctCouponDetailService.serverSelectAll(acctCouponDetailPojo);
        return  ResultMap.getOKInstance().putData("list",list);
    }


}

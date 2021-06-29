package com.xmz.service.card.controller;


import com.xmz.common.utils.ResultMap;
import com.xmz.service.card.entity.pojo.AcctCouponPartsDetail;
import com.xmz.service.card.entity.vo.MyAcctCouponPartsDetail;
import com.xmz.service.card.service.IAcctCouponPartsDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 优惠券配件明细 前端控制器
 * </p>
 *
 * @author liujiaxi
 * @since 2021-06-16
 */
@Api("优惠券配件明细")
@CrossOrigin
@RestController
@RequestMapping("/serviceCard/acct-coupon-parts-detail")
public class AcctCouponPartsDetailController {

    @Autowired
    IAcctCouponPartsDetailService iAcctCouponPartsDetailService;

    @ApiOperation("根据id查询优惠券服务明细")
    @PostMapping("/queryById")
    public ResultMap queryById(@RequestBody AcctCouponPartsDetail acctCouponPartsDetail) {
        List<MyAcctCouponPartsDetail> list=iAcctCouponPartsDetailService.acdList(acctCouponPartsDetail);
        return ResultMap.getOKInstance()
                .putData("list",list);
    }

}


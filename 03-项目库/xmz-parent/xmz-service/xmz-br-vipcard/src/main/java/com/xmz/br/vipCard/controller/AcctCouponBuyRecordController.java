package com.xmz.br.vipCard.controller;


import com.xmz.br.vipCard.entity.pojo.AcctCouponBuyRecordPojo;
import com.xmz.br.vipCard.service.AcctCouponBuyRecordService;
import com.xmz.common.utils.ResultMap;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 优惠券购买及消费跟踪 前端控制器
 * </p>
 *
 * @author zt
 * @since 2021-06-19
 */
@RestController
@RequestMapping("/brVipcard/acct-coupon-buy-record")
public class AcctCouponBuyRecordController {
    @Autowired
    AcctCouponBuyRecordService acctCouponBuyRecordService;
    @ApiOperation("转赠客户")
    @PostMapping("/update")
    public ResultMap update(@ApiParam("对象") @RequestBody AcctCouponBuyRecordPojo acctCouponBuyRecordPojo) {
        acctCouponBuyRecordPojo.setAttornDate(new Date());
        boolean flag = acctCouponBuyRecordService.updateById(acctCouponBuyRecordPojo);
        return ResultMap.getOKInstance();
    }

    @ApiOperation("批量转赠")
    @PostMapping("/addList2/{num}")
    public ResultMap addList2(@ApiParam("转赠数量") @PathVariable BigDecimal num,
                             @ApiParam("对象") @RequestBody AcctCouponBuyRecordPojo acctCouponBuyRecordPojo) {
        boolean flag = acctCouponBuyRecordService.addList2(acctCouponBuyRecordPojo, num);
        return ResultMap.getOKInstance();
    }
}


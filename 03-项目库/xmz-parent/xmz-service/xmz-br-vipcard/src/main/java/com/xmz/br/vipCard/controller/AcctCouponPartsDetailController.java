package com.xmz.br.vipCard.controller;



import com.xmz.br.vipCard.entity.pojo.AcctCouponPartsDetailPojo;
import com.xmz.br.vipCard.entity.pojo.CouponPartsVo;
import com.xmz.br.vipCard.service.AcctCouponPartsDetailService;
import com.xmz.common.utils.ResultMap;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 优惠券配件明细 前端控制器
 * </p>
 *
 * @author LLY
 * @since 2021-06-03
 */
@RestController
@RequestMapping("/brVipcard/acct-cou-par-deta")
public class AcctCouponPartsDetailController {
    @Autowired
    AcctCouponPartsDetailService acctCouponPartsDetailService;

    @ApiOperation("查询该优惠券的所有配件")
    @PostMapping("/serverSelectAll")
    public ResultMap partsSelectAll(@ApiParam("条件参数") @RequestBody(required = false)
                                            AcctCouponPartsDetailPojo acctCouponPartsDetailPojo) {
        List<CouponPartsVo> list = acctCouponPartsDetailService.partsSelectAll(acctCouponPartsDetailPojo);
        return  ResultMap.getOKInstance().putData("list",list);
    }
}

package com.xmz.br.vipCard.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xmz.br.vipCard.entity.pojo.BrCouponVo;
import com.xmz.br.vipCard.service.BrCouponService;
import com.xmz.common.utils.ResultMap;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * <p>
 * 门店与优惠券对应关系 前端控制器
 * </p>
 *
 * @author LLY
 * @since 2021-06-10
 */
@RestController
@RequestMapping("/brVipcard/br_coupon")
public class BrCouponController{
    @Autowired
    BrCouponService brCouponService;

    @ApiOperation("多条件分页查询")
    @PostMapping("pageList/{page}/{size}")
    @CrossOrigin
    public ResultMap pageList(@ApiParam("页数") @PathVariable long page,
                              @ApiParam("页大小") @PathVariable long size,
                              @ApiParam("条件参数") @RequestBody(required = false) BrCouponVo brCouponVo) {
        Page<BrCouponVo> pojoPage = new Page<>(page, size);
        List<BrCouponVo> list = brCouponService.pageList(pojoPage,brCouponVo);
        return ResultMap.getOKInstance()
                .putData("list", list)
                .putData("total", pojoPage.getTotal());
    }
}

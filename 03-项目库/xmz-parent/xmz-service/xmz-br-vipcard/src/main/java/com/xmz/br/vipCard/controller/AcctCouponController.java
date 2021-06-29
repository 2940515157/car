package com.xmz.br.vipCard.controller;



import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xmz.br.vipCard.entity.pojo.*;
import com.xmz.br.vipCard.entity.vo.AcctBrCardQuery;
import com.xmz.br.vipCard.entity.vo.AcctCouponBuyRecordPojoQuery;
import com.xmz.br.vipCard.service.AcctCouponBuyRecordService;
import com.xmz.br.vipCard.service.AcctCouponService;
import com.xmz.br.vipCard.service.BrCouponService;
import com.xmz.br.vipCard.service.IAcctBrCardService;
import com.xmz.common.utils.ResultMap;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 优惠券 前端控制器
 * </p>
 *
 * @author LLY
 * @since 2021-05-27
 */
@RestController
@RequestMapping("/brVipcard/acct-cou-pon")
public class AcctCouponController{
    @Autowired
    AcctCouponService acctCouponService;

    @Autowired
    BrCouponService brCouponService;

    @Autowired
    AcctCouponBuyRecordService acctCouponBuyRecordService;



    @ApiOperation("基础 多条件分页查询 ")
    @PostMapping("/pageList/{page}/{size}")
    @CrossOrigin
    public ResultMap getBasePageByParamWrapper(@ApiParam("页数") @PathVariable long page,
                                               @ApiParam("页大小") @PathVariable long size,
                                               @ApiParam("条件参数") @RequestBody(required = false) CouponVo couponVo) {
        Page<CouponVo> pojoPage = new Page<>(page, size);
        List<CouponVo> list = acctCouponService.pageList(pojoPage, couponVo);
        return ResultMap.getOKInstance()
                .putData("list", list)
                .putData("total", pojoPage.getTotal());
    }

    @ApiOperation("上架/下架")
    @PostMapping("/updateStatus")
    public ResultMap updateStatus(@RequestBody BrCouponPojo brCouponPojo) {
        brCouponService.updateById(brCouponPojo);
        return ResultMap.getOKInstance();
    }

    @ApiOperation("获取优惠券消费记录列表(条件分页)")
    @PostMapping("/queryPage/{current}/{size}")
    public ResultMap queryPage(@RequestBody AcctCouponBuyRecordPojoQuery acctCouponBuyRecordPojoQuery,
                               @PathVariable Integer current,
                               @PathVariable Integer size){
        Page<MyAcctCouponBuyRecordPojo> page=new Page(current,size);
        acctCouponService.myPage(page,acctCouponBuyRecordPojoQuery);
        return ResultMap.getOKInstance()
                .putData("rows",page.getRecords())
                .putData("pages",page.getPages())
                .putData("total",page.getTotal())
                .putData("current",page.getCurrent())
                .putData("size",page.getSize());
    }

    @ApiOperation("获取优惠券汇总记录列表(条件分页)")
    @PostMapping("/queryPage2/{current}/{size}")
    public ResultMap queryPage2(@RequestBody AcctCouponBuyRecordPojoQuery acctCouponBuyRecordPojoQuery,
                               @PathVariable Integer current,
                               @PathVariable Integer size){
        Page<MyAcbrStatus> page=new Page(current,size);
        acctCouponService.myPage2(page,acctCouponBuyRecordPojoQuery);
        return ResultMap.getOKInstance()
                .putData("rows",page.getRecords())
                .putData("pages",page.getPages())
                .putData("total",page.getTotal())
                .putData("current",page.getCurrent())
                .putData("size",page.getSize());
    }


    @ApiOperation("添加多个")
    @PostMapping("/addList/{num}")
    public ResultMap addList(@ApiParam("添加多个") @PathVariable BigDecimal num,
                              @ApiParam("对象") @RequestBody AcctCouponBuyRecordPojo acctCouponBuyRecordPojo) {
        boolean flag = acctCouponBuyRecordService.addList(acctCouponBuyRecordPojo, num);
        return ResultMap.getOKInstance();
    }

    @ApiOperation("分页条件查询优惠券明细")
    @PostMapping("/pageList2/{page}/{size}")
    public ResultMap pageList(@ApiParam("页数") @PathVariable long page,
                               @ApiParam("页大小") @PathVariable long size,
                               @ApiParam("对象") @RequestBody RecordVo recordVo) {
        Page<RecordVo> pojoPage = new Page<>(page, size);
        List<RecordVo> list = acctCouponBuyRecordService.pageList(pojoPage, recordVo);
        return ResultMap.getOKInstance()
                .putData("list", list)
                .putData("total", pojoPage.getTotal());
    }


    @ApiOperation("查询所有优惠券汇总")
    @PostMapping("/queryAll")
    public ResultMap queryAll() {
     List<MyAcbrStatus> list= acctCouponService.queryAll();
        return ResultMap.getOKInstance()
                .putData("list",list);
    }

    @ApiOperation("查询所有未转赠的优惠券数量")
    @PostMapping("/queryNormal/{acUuid}")
    public ResultMap queryNormal(@PathVariable String acUuid) {
        MyAcbrStatus myAcbrStatus= acctCouponService.queryNormal(acUuid);
        return ResultMap.getOKInstance()
                .putData("myAcbrStatus",myAcbrStatus);
    }

}

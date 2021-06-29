package com.xmz.service.card.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.common.utils.ResultMap;
import com.xmz.service.card.config.PinYinUtil;
import com.xmz.service.card.entity.pojo.*;
import com.xmz.service.card.entity.vo.AcctCouponQuery;
import com.xmz.service.card.entity.vo.MyAcctCompositeProduct;
import com.xmz.service.card.entity.vo.MyAcctCoupon;
import com.xmz.service.card.service.IAcctCouponDetailService;
import com.xmz.service.card.service.IAcctCouponPartsDetailService;
import com.xmz.service.card.service.IAcctCouponPartsService;
import com.xmz.service.card.service.IAcctCouponService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 优惠券 前端控制器
 * </p>
 *
 * @author ;liujiaxi
 * @since 2021-06-07
 */
@Api("优惠券")
@CrossOrigin
@RestController
@RequestMapping("/serviceCard/acct-coupon")
public class AcctCouponController {
    @Autowired
    IAcctCouponService iAcctCouponService;

    @Autowired
    IAcctCouponDetailService iAcctCouponDetailService;

    @Autowired
    IAcctCouponPartsService iAcctCouponPartsService;

    @Autowired
    IAcctCouponPartsDetailService iAcctCouponPartsDetailService;


    @ApiOperation("分页条件查询优惠券")
    @PostMapping("queryAllACoup/{current}/{size}")
    public ResultMap queryAllACoup(@PathVariable Long current,
                                  @PathVariable Long size,
                                  @RequestBody AcctCouponQuery acctCouponQuery){
        Page<MyAcctCoupon> brPackagePage = new Page<>(current, size);

        iAcctCouponService.queryAllACoup(brPackagePage,acctCouponQuery);
        return ResultMap.getOKInstance()
                .putData("rows",brPackagePage.getRecords())
                .putData("pages",brPackagePage.getPages())
                .putData("total",brPackagePage.getTotal())
                .putData("current",brPackagePage.getCurrent())
                .putData("size",brPackagePage.getSize());
    }

    //添加
    @ApiOperation("增加优惠券")
    @PostMapping("/add")//AcctCoupon
    public ResultMap add(@RequestBody Map<String,String> data){
     //获取优惠券数据

      boolean flag=iAcctCouponService.add(data);

        if(flag)
        return ResultMap.getErrorInstance();
        return ResultMap.getOKInstance();
    }

    //修改
    @ApiOperation("修改优惠券管理")
    @PostMapping("/update")
    public ResultMap update(@RequestBody AcctCoupon acctCoupon){
        //卡券拼音
        acctCoupon.setPinyin(PinYinUtil.toPinyin(acctCoupon.getAcName()));
        boolean flag=iAcctCouponService.updateById(acctCoupon);
        if(flag)
            return ResultMap.getErrorInstance();
            return ResultMap.getOKInstance();
    }


    @ApiOperation("根据id查询优惠券管理")
    @PostMapping("/queryById/{id}")
    public ResultMap queryById(@PathVariable String id) {

        AcctCoupon acctCoupon=iAcctCouponService.getById(id);
        return ResultMap.getOKInstance()
                .putData("acctCoupon",acctCoupon);
    }

    //删除
    @ApiOperation("删除优惠券管理")
    @DeleteMapping("/delete/{id}")
    public ResultMap delete(@PathVariable String id){
        boolean flag=iAcctCouponService.removeById(id);
        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }


    @ApiOperation("上架储值卡产品")
    @PostMapping("/update1")
    public ResultMap update1(@RequestBody AcctCoupon acctCoupon){

        acctCoupon.setStatus("up");

        boolean flag=iAcctCouponService.updateById(acctCoupon);
        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }


    @ApiOperation("下架储值卡产品")
    @PostMapping("/update2")
    public ResultMap update2(@RequestBody AcctCoupon acctCoupon){

        acctCoupon.setStatus("under");

        boolean flag=iAcctCouponService.updateById(acctCoupon);
        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }

    @ApiOperation("强制退回储值卡产品")
    @PostMapping("/update3")
    public ResultMap update3(@RequestBody AcctCoupon acctCoupon){

        acctCoupon.setStatus("init");

        boolean flag=iAcctCouponService.updateById(acctCoupon);
        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }

    //添加
    @ApiOperation("增加优惠券配件")
    @PostMapping("/adds")//AcctCoupon
    public ResultMap adds(@RequestBody Map<String,String> data){
        //获取优惠券数据
        AcctCoupon acctCoupon= JSONObject.parseObject(data.get("form"), AcctCoupon.class);

        iAcctCouponService.save(acctCoupon);
        //获取优惠券与配件
        List<AcctCouponPartsDetail> list
                = JSONObject.parseArray(data.get("forms"),AcctCouponPartsDetail.class);

        List<AcctCouponPartsDetail> list1
                = JSONObject.parseArray(data.get("partTypeUuid"),AcctCouponPartsDetail.class);
        System.out.println(list1.toString());
        //new 优惠券与配件明细对象
        AcctCouponPartsDetail acctCouponPartsDetail=new AcctCouponPartsDetail();

        for(int i=0;i<list.size();i++){
            //添加优惠券与配件明细
            acctCouponPartsDetail.setAcpdUuid(null);

            //AC_UUID卡券id
            acctCouponPartsDetail.setAcUuid(acctCoupon.getAcUuid());

            //PART_UUID 零配件id
            acctCouponPartsDetail.setPartUuid(list.get(i).getPartUuid());

            //PART_TYPE_UUID 配件类型id
            acctCouponPartsDetail.setPartTypeUuid(list1.get(i).getPartTypeUuid());
            //抵用可用金额
            acctCouponPartsDetail.setReplacePrice(list.get(i).getReplacePrice());
            //数量
            acctCouponPartsDetail.setAcpdNumber(list.get(i).getAcpdNumber());
            //单位
            acctCouponPartsDetail.setUnit(list.get(i).getUnit());
            //是否有效
            acctCouponPartsDetail.setIsActive("Y");
            acctCouponPartsDetail.setCreatedDate(new Date());
            //总部分摊比率ALLOCATION_BASE
            acctCouponPartsDetail.setAllocationBase(list.get(i).getAllocationBase());
            //门店分摊比率ALLOCATION_BRANCHES
            acctCouponPartsDetail.setAllocationBranches(list.get(i).getAllocationBranches());
            //SA分摊比率ALLOCATION_SA
            acctCouponPartsDetail.setAllocationSa(list.get(i).getAllocationSa());

            iAcctCouponPartsDetailService.save(acctCouponPartsDetail);
        }


        return ResultMap.getOKInstance();

    }

    @ApiOperation("查询门店可用的优惠券")
    @PostMapping("queryBrACoup/{current}/{size}")
    public ResultMap queryBrACoup(@PathVariable Long current,
                                   @PathVariable Long size,
                                   @RequestBody AcctCouponQuery acctCouponQuery){
        Page<MyAcctCoupon> brPackagePage = new Page<>(current, size);

        iAcctCouponService.queryBrACoup(brPackagePage,acctCouponQuery);
        return ResultMap.getOKInstance()
                .putData("rows",brPackagePage.getRecords())
                .putData("pages",brPackagePage.getPages())
                .putData("total",brPackagePage.getTotal())
                .putData("current",brPackagePage.getCurrent())
                .putData("size",brPackagePage.getSize());
    }


}


package com.xmz.service.card.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.common.utils.ResultMap;
import com.xmz.service.card.entity.pojo.AcctCardArea;
import com.xmz.service.card.entity.pojo.AcctCouponArea;
import com.xmz.service.card.entity.vo.MyAcctCardArea;
import com.xmz.service.card.entity.vo.MyAcctCouponArea;
import com.xmz.service.card.service.IAcctCouponAreaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 优惠券使用范围 前端控制器
 * </p>
 *
 * @author liujiaxi
 * @since 2021-06-16
 */
@Api("优惠券使用范围")
@RestController
@CrossOrigin
@RequestMapping("/serviceCard/acct-coupon-area")
public class AcctCouponAreaController {
    @Autowired
    IAcctCouponAreaService iAcctCouponAreaService;

    @ApiOperation("分页条件查询卡与区域的关系")
    @PostMapping("queryAllPage/{current}/{size}/{acUuid}")
    public ResultMap queryAllPage(@PathVariable Long current,
                                  @PathVariable Long size,
                                  @PathVariable String acUuid){
        Page<MyAcctCouponArea> brPackagePage = new Page<>(current, size);

        iAcctCouponAreaService.queryAllPage(brPackagePage,acUuid);
        return ResultMap.getOKInstance()
                .putData("rows",brPackagePage.getRecords())
                .putData("pages",brPackagePage.getPages())
                .putData("total",brPackagePage.getTotal())
                .putData("current",brPackagePage.getCurrent())
                .putData("size",brPackagePage.getSize());
    }


    //添加
    @ApiOperation("增加选择范围")
    @PostMapping("/add")
    public ResultMap add(@RequestBody AcctCouponArea acctCouponArea){

        boolean flag=iAcctCouponAreaService.add(acctCouponArea);
        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }

    //删除
    @ApiOperation("删除卡与区域的关系")
    @DeleteMapping("/delete/{id}")
    public ResultMap delete(@PathVariable String id){
        boolean flag=iAcctCouponAreaService.removeById(id);
        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }



}


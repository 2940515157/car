package com.xmz.service.card.controller;


import com.alibaba.fastjson.JSONObject;
import com.xmz.common.utils.ResultMap;
import com.xmz.service.card.config.PinYinUtil;
import com.xmz.service.card.entity.pojo.AcctBrCard;
import com.xmz.service.card.entity.pojo.AcctCardStore;
import com.xmz.service.card.entity.pojo.AcctCoupon;
import com.xmz.service.card.entity.pojo.AcctCouponDetail;
import com.xmz.service.card.entity.vo.MyAcctCouponDetail;
import com.xmz.service.card.service.IAcctCouponDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 优惠券服务明细 前端控制器
 * </p>
 *
 * @author liujiaxi
 * @since 2021-06-15
 */
@Api("优惠券服务明细")
@RestController
@CrossOrigin
@RequestMapping("/serviceCard/acct-coupon-detail")
public class AcctCouponDetailController {

    @Autowired
    IAcctCouponDetailService iAcctCouponDetailService;

    //添加
    @ApiOperation("增加优惠券服务明细")
    @PostMapping("/add")
    public ResultMap add(@RequestBody Map<String,String> data){

            return ResultMap.getOKInstance();
    }

    @ApiOperation("根据id查询优惠券服务明细")
    @PostMapping("/queryById")
    public ResultMap queryById(@RequestBody AcctCouponDetail acctCouponDetail) {
        List<MyAcctCouponDetail> list=iAcctCouponDetailService.acdList(acctCouponDetail);
        return ResultMap.getOKInstance()
                .putData("list",list);
    }

    //修改
    @ApiOperation("修改优惠券服务明细")
    @PostMapping("/update")
    public ResultMap update(@RequestBody Map<String,String> data){
        List<AcctCouponDetail> list
                = JSONObject.parseArray(data.get("forms2"),AcctCouponDetail.class);

        //获取卡券id
        String AcUuid= data.get("AcUuid");

        List<AcctCouponDetail> list1
                = JSONObject.parseArray(data.get("stUuid"),AcctCouponDetail.class);

        List<AcctCouponDetail> list2
                = JSONObject.parseArray(data.get("acdUuid"),AcctCouponDetail.class);



            for(int i=0;i<list2.size();i++){
                iAcctCouponDetailService.removeById(list2.get(i).getAcdUuid());
            }
          //根据卡券id 删除优惠券服务明细
            for(int i=0;i<list.size();i++){

                    AcctCouponDetail acctCouponDetail=new AcctCouponDetail();
               if(list.get(i).getStUuid()==null){
                   System.out.println(list.get(i).getServTypeUuid());
                   acctCouponDetail.setStUuid(list1.get(i).getServTypeUuid());
               }else {
                   System.out.println(list.get(i).getStUuid());
                   acctCouponDetail.setStUuid(list1.get(i).getStUuid());

               }
                    acctCouponDetail.setAcdUuid(null);
                    //卡券id
                    acctCouponDetail.setAcUuid(AcUuid);

                    acctCouponDetail.setStUuid(list1.get(i).getStUuid());
                    //服务项目id
                    acctCouponDetail.setCatalogUuid(list.get(i).getCatalogUuid());
                    //销售价格
                    acctCouponDetail.setSalePrice(list.get(i).getSalePrice());
                    //是否有效
                    acctCouponDetail.setIsActive(list.get(i).getIsActive());
                    //服务可抵用金额
                    acctCouponDetail.setReplacePrice(list.get(i).getReplacePrice());
                    //创建日期
                    acctCouponDetail.setCreatedDate(list.get(i).getCreatedDate());
                    //创建人
                    acctCouponDetail.setCreatedBy(list.get(i).getCreatedBy());
                    //版本
                    acctCouponDetail.setVersion(list.get(i).getVersion());
                    //总部分摊比率
                    acctCouponDetail.setAllocationBase(list.get(i).getAllocationBase());
                    //门店分摊比率
                    acctCouponDetail.setAllocationBranches(list.get(i).getAllocationBranches());
                    //SA分摊比率
                    acctCouponDetail.setAllocationSa(list.get(i).getAllocationSa());
                    //添加优惠券服务明细
                    iAcctCouponDetailService.save(acctCouponDetail);

            }




        return ResultMap.getOKInstance();
    }

}


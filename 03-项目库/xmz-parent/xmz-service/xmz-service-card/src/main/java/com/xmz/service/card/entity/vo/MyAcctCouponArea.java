package com.xmz.service.card.entity.vo;

import com.xmz.service.card.entity.pojo.AcctCouponArea;
import lombok.Data;

import java.io.Serializable;

/**
 * 封装优惠券与区域范围的返回结果
 */
@Data
public class MyAcctCouponArea extends AcctCouponArea implements Serializable {

    private String typeName;//类型(门店，地区,全国)
    private String orgName;//部门名称
    private String brName;//门店名称

}

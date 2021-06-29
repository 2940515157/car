package com.xmz.service.card.entity.vo;


import com.xmz.service.card.entity.pojo.AcctCouponDetail;
import lombok.Data;

import java.io.Serializable;

/**
 * 封装优惠券服务明细 返回结果
 */
@Data
public class MyAcctCouponDetail extends AcctCouponDetail implements Serializable {
     private String  catalogName;   //服务项目名称
     private String  servTypeName;  //服务类型名称

}

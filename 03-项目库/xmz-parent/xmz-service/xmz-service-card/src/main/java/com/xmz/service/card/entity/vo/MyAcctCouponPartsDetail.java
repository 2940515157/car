package com.xmz.service.card.entity.vo;

import com.xmz.service.card.entity.pojo.AcctCouponPartsDetail;
import lombok.Data;

import java.io.Serializable;

/**
 * 封装优惠券配件明细 返回结果
 */
@Data
public class MyAcctCouponPartsDetail extends AcctCouponPartsDetail implements Serializable {

    private String bptNAME;  //配件目录
    private String partName; //配件商品


}

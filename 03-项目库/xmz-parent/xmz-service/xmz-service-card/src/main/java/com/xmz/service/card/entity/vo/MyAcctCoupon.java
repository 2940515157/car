package com.xmz.service.card.entity.vo;

import com.xmz.service.card.entity.pojo.AcctCoupon;
import lombok.Data;

import java.io.Serializable;

/**
 * 优惠券查询结果
 */
@Data
public class MyAcctCoupon extends AcctCoupon implements Serializable {

    private String bizTypeName;         //业务分类
    private String acTypeName;          //卡券类型
    private String acUseTypeName;       //卡券使用类型
    private String statusName;          //状态






}

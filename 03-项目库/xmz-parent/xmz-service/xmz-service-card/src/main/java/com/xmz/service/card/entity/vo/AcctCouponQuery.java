package com.xmz.service.card.entity.vo;

import lombok.Data;

/**
 * 优惠券查询条件
 */
@Data
public class AcctCouponQuery {

    private String acName; //卡券名称
    private String bizType; //业务分类
    private String acUseType; //卡券使用类型
    private String status; //状态

}

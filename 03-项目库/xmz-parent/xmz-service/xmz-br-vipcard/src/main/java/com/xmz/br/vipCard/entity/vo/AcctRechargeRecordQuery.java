package com.xmz.br.vipCard.entity.vo;

import lombok.Data;

/**
 * 会员卡充值记录的查询条件
 */
@Data
public class AcctRechargeRecordQuery {
    private String custName;//客户名称
    private String custPhone;//客户电话
    private String cardNo;//卡号
    private String acsName;//产品名称
    //private Data createdDate;//充值日期
    private String status;//充值状态
}

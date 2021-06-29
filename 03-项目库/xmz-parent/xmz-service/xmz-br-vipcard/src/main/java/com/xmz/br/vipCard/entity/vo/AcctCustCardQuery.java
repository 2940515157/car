package com.xmz.br.vipCard.entity.vo;

import lombok.Data;

/**
 * 封装会员卡管理的查询条件
 */
@Data
public class AcctCustCardQuery {
    private String licensePlate;//车牌号
    private String custPhone;//车主电话
    private String custName;//车主名称
    private String cardNo;//卡号
    private String accountId;//开卡人
    private String accountId2;//专属sa
    private String impFlag;//是否导入
    private String custUuid;//客户id

}

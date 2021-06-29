package com.xmz.br.vipCard.entity.vo;

import lombok.Data;

/**
 * 封装门店领卡的查询条件
 */
@Data
public class AcctBrCardQuery {
    //卡号
    private String cardNo;
    //领卡人
    private String saUuid;
    //是否已经发放给客户
    private String isExtend;
    private String empUuid;
}

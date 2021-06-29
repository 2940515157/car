package com.xmz.service.card.entity.vo;

import lombok.Data;

/**
 * 封装制卡管理查询条件
 */
@Data
public class AcctCardStoredQuery {

    private String cardPrefix;//卡号前缀
    private String cardNoBeg;//卡号开始
    private String cardNoEnd;//卡号结束
    private String cardType;  //类型
    private String impFlag;   //是否导入

}

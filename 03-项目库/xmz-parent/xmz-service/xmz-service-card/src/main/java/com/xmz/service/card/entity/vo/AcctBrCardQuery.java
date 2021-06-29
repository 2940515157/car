package com.xmz.service.card.entity.vo;

import lombok.Data;

/**
 * 发卡管理的查询条件
 */
@Data
public class AcctBrCardQuery {

    private String orgUuid;            //大区
    private String areaUuid;           //区域
    private String brUuid;             //门店
    private String saUuid;             //领卡人
    private String impFlag;            //是否导入
    private String cardNo;             //卡号
    private String isExtend;           //是否已发放给客户
    private String brCardStatus;      //卡状态





}

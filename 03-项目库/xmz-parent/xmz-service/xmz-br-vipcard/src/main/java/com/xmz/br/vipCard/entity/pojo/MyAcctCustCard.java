package com.xmz.br.vipCard.entity.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class MyAcctCustCard extends AcctCustCard implements Serializable {

    private String accountId;//开卡人id
    private String accountId2;//专属said
    private String custName; //车主名称
    private String custPhone;//车主电话
    private String name;//开卡人
    private String saname;//专属sa
    private String brName;//发卡门店
    private String status;//状态
    private String cardType;//类型
    private String custUuid;//客户id
    private String cardUuid;//门店领卡id
    private String brUuid;///发卡门店id
    private String carUuid;//车辆id
    private String cardNo;//卡号
    private String licensePlate;//车牌
    private String custLevel;//客户等级
}

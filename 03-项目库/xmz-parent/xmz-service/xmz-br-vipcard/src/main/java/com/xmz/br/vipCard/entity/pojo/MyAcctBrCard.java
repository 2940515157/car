package com.xmz.br.vipCard.entity.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class MyAcctBrCard extends AcctBrCard implements Serializable {
    private String name;//领卡人
    private String name2;//发放人
    private String cardCompany;//卡商
    private String brCardStatus;//卡状态
    private String brName;//发卡门店
    private String cardType;//卡类型
}

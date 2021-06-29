package com.xmz.br.vipCard.entity.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class MyAcctCouponBuyRecordPojo extends AcctCouponBuyRecordPojo implements Serializable {
    private String custName;
    private String acName;
    private String acNumber;
    private String totalPrice;
    private String custPhone;
    private String licensePlate;
    private String refCode;
    private String bizRefType;
}

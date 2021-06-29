package com.xmz.br.vipCard.entity.pojo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class MyAcctRechargeRecord extends AcctRechargeRecord implements Serializable {
    private String custName;//客户名称
    private String custPhone;//客户电话
    private String acsName;//产品名称
    private String name;//结案人
    private String custCardUuid;//客户卡id
    private float totalPrice;//总价值
    private float zfPrice;//支付金额
    private String productUuid;//产品id
    private float zongPrice;//充值后余额


}

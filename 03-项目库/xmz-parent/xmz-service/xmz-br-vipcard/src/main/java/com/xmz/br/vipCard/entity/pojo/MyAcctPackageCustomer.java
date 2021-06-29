package com.xmz.br.vipCard.entity.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class MyAcctPackageCustomer extends AcctPackageCustomer implements Serializable {
    private String cardNo;//卡号
    private String custName;//客户名称
    private String custPhone;//客户电话
    private String apType;//套餐类型
    private String compositeFlag;//是否是组合产品
    private String apcStatus;//已购买套餐的状态
    private String brName;//门店
}

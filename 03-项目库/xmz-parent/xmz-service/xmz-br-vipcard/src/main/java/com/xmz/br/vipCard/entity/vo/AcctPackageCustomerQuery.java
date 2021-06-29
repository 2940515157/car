package com.xmz.br.vipCard.entity.vo;

import lombok.Data;

import java.util.Date;

@Data
public class AcctPackageCustomerQuery {
    private String cardNo;//卡号
    private String custName;//客户名称
    private String custPhone;//客户电话
    private String apcName;//套餐名称
    private String apcStatus;//已购买套餐的状态
    private Date createdDate;
}

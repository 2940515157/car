package com.xmz.br.vipCard.entity.pojo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RecordVo extends AcctCouponBuyRecordPojo {
    private String acName;
    private String statusm;
    private String custName;
    private String custPhone;
    private String acUseType;
    private BigDecimal totalCount;
    private BigDecimal remainder;
    private String name;


}

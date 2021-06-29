package com.xmz.br.vipCard.entity.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class MyAcbrStatus extends AcctCouponPojo implements Serializable {
    private String acType;
    private String acUsrType;
    private String attorn;
    private String normal;
    private String used;
    private String expired;
    private String num;
}

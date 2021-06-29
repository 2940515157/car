package com.xmz.br.vipCard.entity.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class MyBrPackage extends AcctPackage implements Serializable {
    private String brStatus;//门店套餐状态
    private Date putOnDate;//上架时间
    private Date pullOffDate;//下架时间
    private String compostiteFlag;//是否是组合产品
    private String brPackageUuid;
}

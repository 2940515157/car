package com.xmz.br.vipCard.entity.pojo;

import lombok.Data;

@Data
public class CouponPartsVo extends AcctCouponPartsDetailPojo {
    private String name;
    private String partName;
    private String brand;
    private String model;
    private String units;
}

package com.xmz.service.carsparts.entity.vo;

import lombok.Data;

@Data
public class BaseCarPartsSkuQuery {

    private String showName;//配件显示名称品牌+商品名称+规格+型号
    private String partNo;//编号
    private String matchType;//匹配类型
    private String usableFlag;//是否启用Y/N
    private String brand;//品牌
    private String model;//型号
    private String spec;//规格
    private String brUuid;//门店id为空就归属总部
    private String partType;//配件目录

}

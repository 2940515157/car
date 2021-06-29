package com.xmz.service.card.entity.vo;

import lombok.Data;

/**
 * 用与封装查询总店 门店套餐情况的查询条件
 */
@Data
public class BrPackageQuery {

    private String brUuid;          //门店编号
    private String brName;          //门店名称
    private String apmUuid;         //套餐模板
    private String serviceType;     //服务类型
    private String apName;          //套餐名称
    private String apType;          //套餐类型
    private String status;          //状态

}

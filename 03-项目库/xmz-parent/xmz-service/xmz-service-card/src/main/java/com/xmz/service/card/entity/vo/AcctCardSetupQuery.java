package com.xmz.service.card.entity.vo;

import lombok.Data;

/**
 * 用与封装查询储值卡产品定义的查询条件
 */
@Data
public class AcctCardSetupQuery {

    private String acsName;      //产品名称
    private String acsType;   //类型(小拇指卡等)
    private String status;    //状态 数据字典：草稿 申请中 上架 下架



}

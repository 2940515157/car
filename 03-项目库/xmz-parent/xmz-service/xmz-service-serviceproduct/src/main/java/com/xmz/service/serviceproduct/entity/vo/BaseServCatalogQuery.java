package com.xmz.service.serviceproduct.entity.vo;

import lombok.Data;

/**
 * 封装服务项目的查询条件
 */
@Data
public class BaseServCatalogQuery {
    private String stUuid;         //菜单id
    private String usableFlag;     //是否启用
    private String topType;        //服务大类（服务目录）
    private String catalogName;    //服务项目名称
    private String servTypeName;   //服务类型（服务目录）
    private String isCommon;       //是否常用
}

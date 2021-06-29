package com.xmz.service.carsparts.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 用与封装查询配件目录的查询条件
 */
@Data
public class BasePartsTypeQuery implements Serializable {
    private String name;//名称
    private String alias1;//别名1
    private String alias2;//别名2
    private String alias3;//别名3
    private String alias4;//别名4
    private String alias5;//别名5
    private String dataMtType;//数据维护方式
    private String isCommon;//是否常用
    private String matchType;//匹配类型
    private String servType;//配件大类
    private String usableFlag;//是否启用
    private String parentTypeUuid;//父节点
}

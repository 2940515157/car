package com.xmz.service.card.entity.vo;

import com.xmz.service.card.entity.pojo.AcctPackage;
import com.xmz.service.card.entity.pojo.AcctPackageArea;
import lombok.Data;

import java.io.Serializable;

/**
 * 封装查询套餐产品 套餐与区域关系的返回结果
 */
@Data
public class MyAcctPackageArea extends AcctPackageArea implements Serializable {
    private String typeName;//类型(门店，地区,全国)
    private String orgName;//部门名称
    private String brName;//门店名称

}

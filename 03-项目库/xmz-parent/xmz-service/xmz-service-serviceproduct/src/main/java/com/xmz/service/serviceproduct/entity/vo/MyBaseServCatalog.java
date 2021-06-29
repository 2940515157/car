package com.xmz.service.serviceproduct.entity.vo;

import com.xmz.service.serviceproduct.entity.pojo.BaseServCatalog;
import lombok.Data;

import java.io.Serializable;
/**
 * 封装服务项目的返回结果
 */

@Data
public class MyBaseServCatalog extends BaseServCatalog implements Serializable {
    private String topType;        //服务大类（服务目录）
    private String servTypeName;   //服务类型（服务目录）
    private  String tstatType;    //统计分类
    private  String statMethod;   //统计方法
}

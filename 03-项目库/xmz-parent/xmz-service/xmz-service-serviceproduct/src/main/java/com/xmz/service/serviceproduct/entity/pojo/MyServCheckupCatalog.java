package com.xmz.service.serviceproduct.entity.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class MyServCheckupCatalog extends ServCheckupCatalog implements Serializable {
    //项目所属目录 DIRECTORY_NAME
    private String directoryName;
    //类型 TYPE 类型(正常类[normal],轻微异常[slight],严重异常[more]
    private String type;
    //异常描述  DESCRIPTION
    private String description;


}

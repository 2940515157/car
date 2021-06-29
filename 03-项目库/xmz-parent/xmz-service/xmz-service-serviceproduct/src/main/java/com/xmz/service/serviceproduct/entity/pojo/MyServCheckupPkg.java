package com.xmz.service.serviceproduct.entity.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class MyServCheckupPkg extends ServCheckupPkg implements Serializable {
    //点检项目
    private String catalogName;

    //点检项目id
    private String sccUuid;

    //项目所属目录 DIRECTORY_NAME
    private String directoryName;
}

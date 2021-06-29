package com.xmz.br.vipCard.entity.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class MyAcctPackageDetail extends AcctPackageDetail implements Serializable {
    private String catalogName;//服务项目
    private String servTypeName;//服务类型
}

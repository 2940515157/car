package com.xmz.service.card.entity.vo;


import com.xmz.service.card.entity.pojo.AcctPackageDetail;
import lombok.Data;

import java.io.Serializable;

@Data
public class MyAcctPackageDetail extends AcctPackageDetail implements Serializable {

    //服务项目
    private String catalogName;
    //服务目录
    private String servTypeName;
}

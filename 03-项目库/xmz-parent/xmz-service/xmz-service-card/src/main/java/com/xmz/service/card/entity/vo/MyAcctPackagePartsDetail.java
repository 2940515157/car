package com.xmz.service.card.entity.vo;


import com.xmz.service.card.entity.pojo.AcctPackagePartsDetail;
import lombok.Data;

import java.io.Serializable;

@Data
public class MyAcctPackagePartsDetail extends AcctPackagePartsDetail implements Serializable {

    //配件目录
    private String name;
    //商品名称
    private String showName;
    //单位
    private String dictname1;
}

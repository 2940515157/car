package com.xmz.service.card.entity.vo;

import com.xmz.service.card.entity.pojo.AcctCardSetup;
import lombok.Data;

import java.io.Serializable;

/**
 * 用于封装储值卡产品定义的查询结果
 */
@Data
public class MyAcctCardSetup extends AcctCardSetup implements Serializable {


    private String dictname1;    //类型(小拇指卡等)
    private String dictname2;    //状态 数据字典：草稿 申请中 上架 下架
    private String name;         //核算人

}

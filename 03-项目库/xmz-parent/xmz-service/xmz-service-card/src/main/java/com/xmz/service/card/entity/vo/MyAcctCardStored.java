package com.xmz.service.card.entity.vo;

import com.xmz.service.card.entity.pojo.AcctCardStore;
import lombok.Data;

import java.io.Serializable;

/**
 * 封装制卡管理的返回结果
 */
@Data
public class MyAcctCardStored extends AcctCardStore implements Serializable {
    private String sysName;         //录卡人
    private String cardTypeName;    //类型
    private String cardCompanyName; //卡商
}

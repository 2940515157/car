package com.xmz.br.vipCard.entity.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class MyAcctCardSetup extends AcctCardSetup implements Serializable {
    private String acsType;//类型
    private String status;//状态
}

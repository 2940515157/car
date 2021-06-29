package com.xmz.br.basedata.pojo.vo;

import lombok.Data;

@Data
public class HrBranchesQuery {

    private String orgUuid;//大区

    private String areaUuid;//区域

    private String brName;//门店名称

    private String brCode;//门店编号

    private String brType;//门店类型

    private String brPhone;//营业电话

    private String sysAccountId;//账号id
}

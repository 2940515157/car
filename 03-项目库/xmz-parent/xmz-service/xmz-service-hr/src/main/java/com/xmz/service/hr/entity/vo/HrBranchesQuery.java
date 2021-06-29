package com.xmz.service.hr.entity.vo;

import lombok.Data;

@Data
public class HrBranchesQuery {
    private String orgUuid;//大区
    private String brPhone;//门店电话
    private String areaUuid;//区域
    private String brCode;//门店编号
    private String brName;//门店名称
    private String brType;//门店类型
}

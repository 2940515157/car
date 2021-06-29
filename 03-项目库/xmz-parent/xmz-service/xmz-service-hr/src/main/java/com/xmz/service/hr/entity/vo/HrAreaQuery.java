package com.xmz.service.hr.entity.vo;

import lombok.Data;

@Data
public class HrAreaQuery {
    //大区名称
    private String orgUuid;
    //名称
    private String areaName;
    //区域代码
    private String areaCode;
    //门店id BR_UUID
    private  String brUuid;
}

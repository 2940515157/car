package com.xmz.service.hr.entity.vo;

import lombok.Data;

/**
 * 封装部门管理相关的查询条件
 */
@Data
public class HrOrgQuery {
    private String orgNo;
    private String orgName;
    private String isDisabled;
}

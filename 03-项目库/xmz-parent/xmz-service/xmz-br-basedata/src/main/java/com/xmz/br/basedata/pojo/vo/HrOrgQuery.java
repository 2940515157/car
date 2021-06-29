package com.xmz.br.basedata.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HrOrgQuery {

    private String orgUuid;//主键id

    private String orgNo;//部门编号

    private String orgName;//部门名称

    private String isDisabled;//是否停用

    private String parentUuid;//父类部门


}

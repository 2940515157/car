package com.xmz.br.basedata.pojo.vo;

import lombok.Data;

@Data
public class HrEmployeesQuery {

    private String orgId;//大区id

    private String areaId;//区域id

    private String branchesId;//门店id

    private String empType;//员工类别

    private String empStatus;//员工状态

    private String name;//员工姓名

}

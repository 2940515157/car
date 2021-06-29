package com.xmz.br.basedata.pojo.vo;


import com.xmz.br.basedata.pojo.entity.HrEmployees;
import lombok.Data;

@Data
public class HrEmployeesPage extends HrEmployees {

    private String branchesName;//门店名

    private String empStatusName;//员工状态

    private String empTypeName;//员工类别名字

    private String sex;//性别

    private String orgName;//部门名字

}

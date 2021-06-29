package com.xmz.br.basedata.pojo.vo;

import com.xmz.br.basedata.pojo.entity.HrTeamsEmp;
import lombok.Data;

@Data
public class MyHrTeamsEmp extends HrTeamsEmp {

    //员工名称
    private String empName;

    //员工编号
    private String empCode;

    //班组名称
    private String teamName;

}

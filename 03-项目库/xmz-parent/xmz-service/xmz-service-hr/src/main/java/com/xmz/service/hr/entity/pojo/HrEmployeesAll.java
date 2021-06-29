package com.xmz.service.hr.entity.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class HrEmployeesAll extends HrEmployees implements Serializable {
    private String brCode;
    private String brName;
    private String orgName;
}

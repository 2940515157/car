package com.xmz.service.hr.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysAccountQuery {
    private String orgId;
    private String areaId;
    private String brId;
    private List<String> empIds;
    private String name;
    private String loginName;

}

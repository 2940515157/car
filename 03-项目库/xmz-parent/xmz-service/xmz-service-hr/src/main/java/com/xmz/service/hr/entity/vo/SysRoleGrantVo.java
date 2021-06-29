package com.xmz.service.hr.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysRoleGrantVo {
    private String roleId;
    private String roleName;
    private String roleMemo;
    private String accountRoleId;
    private String accountId;
    private String isActive;

}

package com.xmz.service.system.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountInfo {
    private String id;
    private String name;
    private String status;
    private String loginName;
    private String loginTime;
    private String passwordInitFlag;
}

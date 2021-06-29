package com.xmz.service.system.entity.vo;

import lombok.Data;

@Data
public class SysResourceQuery {
    private String resourceName;
    private String resourceOrigin;
    private String parentId;
}

package com.xmz.service.system.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SysResource查询对象", description="资源")
public class SysPermissionQuery {
    @ApiModelProperty(value = "资源名称")
    private String resourceName;
    @ApiModelProperty(value = "操作名称")
    private String operationName;
    @ApiModelProperty(value = "逻辑流名称")
    private String logicFlowName;
    @ApiModelProperty(value = "归属")
    private String resourceOrigin;
}

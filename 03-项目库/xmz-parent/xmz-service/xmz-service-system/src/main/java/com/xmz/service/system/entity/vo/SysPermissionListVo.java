package com.xmz.service.system.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SysPermission再封装对象", description="权限")
public class SysPermissionListVo {
    @ApiModelProperty(value = "主键ID")
    private String permissionId;
    @ApiModelProperty(value = "资源类型 数据字典：页面、按钮")
    private String resourceOrigin;
    @ApiModelProperty(value = "权限名称")
    private String resourceName;
    @TableField("OPERATION_NAME")
    private String operationName;
    @ApiModelProperty(value = "逻辑流名称")
    private String logicFlowName;
}

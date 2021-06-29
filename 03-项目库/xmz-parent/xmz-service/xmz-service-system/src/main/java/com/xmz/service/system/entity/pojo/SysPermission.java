package com.xmz.service.system.entity.pojo;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 权限
 * </p>
 *
 * @author 罗俊
 * @since 2021-06-01
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @ApiModel(value="SysPermission对象", description="权限")
public class SysPermission implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "主键ID")
        @TableId(value = "PERMISSION_ID", type = IdType.ASSIGN_UUID)
      private String permissionId;

      @ApiModelProperty(value = "权限名称")
      @TableField("RESOURCE_NAME")
    private String resourceName;

      @ApiModelProperty(value = "资源URI")
      @TableField("RESOURCE_URI")
    private String resourceUri;

      @ApiModelProperty(value = "资源Code")
      @TableField("OPERATION_CODE")
    private String operationCode;

      @ApiModelProperty(value = "资源ID")
      @TableField("RESOURCE_ID")
    private String resourceId;

      @ApiModelProperty(value = "操作ID")
      @TableField("OPERATION_ID")
    private String operationId;

      @ApiModelProperty(value = "操作名称")
      @TableField("OPERATION_NAME")
    private String operationName;

      @ApiModelProperty(value = "是否有效(Y:有效；N:无效)")
      @TableField("IS_ACTIVE")
    private String isActive;

      @ApiModelProperty(value = "创建日期")
      @TableField("CREATED_DATE")
    private Date createdDate;

      @ApiModelProperty(value = "修改日期")
      @TableField("UPDATED_DATE")
    private Date updatedDate;

      @ApiModelProperty(value = "创建人")
      @TableField("CREATED_BY")
    private String createdBy;

      @ApiModelProperty(value = "修改人")
      @TableField("UPDATED_BY")
    private String updatedBy;

      @ApiModelProperty(value = "版本")
      @TableField("VERSION")
    private BigDecimal version;

      @ApiModelProperty(value = "逻辑流名称")
      @TableField("LOGIC_FLOW_NAME")
    private String logicFlowName;

      @ApiModelProperty(value = "扩展字段1")
      @TableField("EXT1")
    private String ext1;

      @ApiModelProperty(value = "扩展字段2")
      @TableField("EXT2")
    private String ext2;

      @ApiModelProperty(value = "扩展字段3")
      @TableField("EXT3")
    private String ext3;


}

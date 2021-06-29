package com.xmz.service.hr.entity.pojo;

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
 * 角色-权限
 * </p>
 *
 * @author 罗俊
 * @since 2021-06-10
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @ApiModel(value="SysRolePermission对象", description="角色-权限")
public class SysRolePermission implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "主键ID")
        @TableId(value = "ROLE_PERMISSION_ID", type = IdType.ASSIGN_UUID)
      private String rolePermissionId;

      @ApiModelProperty(value = "角色ID")
      @TableField("ROLE_ID")
    private String roleId;

      @ApiModelProperty(value = "权限ID")
      @TableField("PERMISSION_ID")
    private String permissionId;

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


}

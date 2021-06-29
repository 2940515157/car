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
 * 用户账号-角色
 * </p>
 *
 * @author 罗俊
 * @since 2021-06-15
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @ApiModel(value="SysAccountRole对象", description="用户账号-角色")
public class SysAccountRole implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "主键UUID")
        @TableId(value = "ACCOUNT_ROLE_ID", type = IdType.ASSIGN_UUID)
      private String accountRoleId;

      @ApiModelProperty(value = "账号ID")
      @TableField("ACCOUNT_ID")
    private String accountId;

      @ApiModelProperty(value = "角色ID")
      @TableField("ROLE_ID")
    private String roleId;

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

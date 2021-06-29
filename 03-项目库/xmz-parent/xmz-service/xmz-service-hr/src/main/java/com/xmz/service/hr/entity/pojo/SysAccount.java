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
 * 用户
 * </p>
 *
 * @author 罗俊
 * @since 2021-06-10
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @ApiModel(value="SysAccount对象", description="用户")
public class SysAccount implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "主键UUID")
        @TableId(value = "ACCOUNT_ID", type = IdType.ASSIGN_UUID)
      private String accountId;

      @ApiModelProperty(value = "用户名")
      @TableField("NAME")
    private String name;

      @ApiModelProperty(value = "账号类型 数据字典：普通账号、管理账号")
      @TableField("TYPE")
    private String type;

      @ApiModelProperty(value = "账号状态")
      @TableField("STATUS")
    private String status;

      @ApiModelProperty(value = "密码")
      @TableField("PASSWORD")
    private String password;

      @ApiModelProperty(value = "登录名")
      @TableField("LOGIN_NAME")
    private String loginName;

      @ApiModelProperty(value = "门店ID")
      @TableField("ORG_ID")
    private String orgId;

      @ApiModelProperty(value = "备注")
      @TableField("REMARK")
    private String remark;

      @ApiModelProperty(value = "代理人ID")
      @TableField("PROXY_ID")
    private String proxyId;

      @ApiModelProperty(value = "上次密码")
      @TableField("ORI_PASSWORD")
    private String oriPassword;

      @ApiModelProperty(value = "最近登录时间")
      @TableField("LAST_LOGIN_TIME")
    private Date lastLoginTime;

      @ApiModelProperty(value = "最近修改密码时间")
      @TableField("LAST_PASSWORD_TIME")
    private Date lastPasswordTime;

      @ApiModelProperty(value = "员工ID、供应商ID")
      @TableField("REF_ID")
    private String refId;

      @ApiModelProperty(value = "客户端IP")
      @TableField("LOGIN_IP")
    private String loginIp;

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

      @ApiModelProperty(value = "登录用户表关联类型员工，供应商")
      @TableField("REF_TYPE")
    private String refType;

      @ApiModelProperty(value = "app登录的token")
      @TableField("TOKEN")
    private String token;

      @ApiModelProperty(value = "token设置时间")
      @TableField("TOKEN_BEGIN_TIME")
    private Date tokenBeginTime;

      @ApiModelProperty(value = "token失效时间")
      @TableField("TOKEN_END_TIME")
    private Date tokenEndTime;

      @ApiModelProperty(value = "是否为初始密码,如果是登录后直接跳转到密码修改界面")
      @TableField("PASSWORD_INIT_FLAG")
    private String passwordInitFlag;

      @ApiModelProperty(value = "配件显示模式(Y:显示进价模式,N:不显示进价模式)")
      @TableField("PART_SHOW_MODE")
    private String partShowMode;

      @ApiModelProperty(value = "是否首次接车选择配件")
      @TableField("FIRST_SELECT_PART")
    private String firstSelectPart;


}

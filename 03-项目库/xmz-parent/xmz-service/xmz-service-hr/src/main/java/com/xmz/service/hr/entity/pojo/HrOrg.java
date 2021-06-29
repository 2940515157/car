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
 * 部门信息
 * </p>
 *
 * @author leiyaping
 * @since 2021-05-28
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @ApiModel(value="HrOrg对象", description="部门信息")
public class HrOrg implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "uuid")
        @TableId(value = "ORG_UUID", type = IdType.ASSIGN_UUID)
      private String orgUuid;

      @ApiModelProperty(value = "父类部门")
      @TableField("PARENT_UUID")
    private String parentUuid;

      @ApiModelProperty(value = "部门编号")
      @TableField("ORG_NO")
    private String orgNo;

      @ApiModelProperty(value = "名称")
      @TableField("ORG_NAME")
    private String orgName;

      @ApiModelProperty(value = "公司")
      @TableField("COMPANY_UUID")
    private String companyUuid;

      @ApiModelProperty(value = "部门电话")
      @TableField("ORG_PHONE")
    private String orgPhone;

      @ApiModelProperty(value = "部门负责人")
      @TableField("ORG_MANAGER")
    private String orgManager;

      @ApiModelProperty(value = "地址")
      @TableField("ADDRESS")
    private String address;

      @ApiModelProperty(value = "备注")
      @TableField("REMARK")
    private String remark;

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

      @ApiModelProperty(value = "是否大区(Y:是；N:否)")
      @TableField("IS_BIG_AREA")
    private String isBigArea;

      @ApiModelProperty(value = "是否停用(Y:有；N:否)")
      @TableField("IS_DISABLED")
    private String isDisabled;

      @ApiModelProperty(value = "排序")
      @TableField("SEQ_NO")
    private BigDecimal seqNo;


}

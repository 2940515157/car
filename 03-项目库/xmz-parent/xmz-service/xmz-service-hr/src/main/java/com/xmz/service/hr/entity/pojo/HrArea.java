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
 * 区域表
 * </p>
 *
 * @author lm
 * @since 2021-06-01
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @ApiModel(value="HrArea对象", description="区域表")
public class HrArea implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "uuid")
        @TableId(value = "AREA_UUID", type = IdType.ASSIGN_UUID)
      private String areaUuid;

      @ApiModelProperty(value = "父类区域")
      @TableField("PARENT_UUID")
    private String parentUuid;

      @ApiModelProperty(value = "大区")
      @TableField("ORG_UUID")
    private String orgUuid;

      @ApiModelProperty(value = "名称")
      @TableField("AREA_NAME")
    private String areaName;

      @ApiModelProperty(value = "区域代码")
      @TableField("AREA_CODE")
    private String areaCode;

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

      @ApiModelProperty(value = "区域名称的拼音")
      @TableField("PINYIN")
    private String pinyin;


}

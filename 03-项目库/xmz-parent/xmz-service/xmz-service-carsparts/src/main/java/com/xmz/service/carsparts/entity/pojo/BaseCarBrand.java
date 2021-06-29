package com.xmz.service.carsparts.entity.pojo;

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
 * 品牌(车辆)
 * </p>
 *
 * @author 江志威
 * @since 2021-06-15
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @ApiModel(value="BaseCarBrand对象", description="品牌(车辆)")
public class BaseCarBrand implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "主键ID")
        @TableId(value = "BRAND_UUID", type = IdType.ASSIGN_UUID)
      private String brandUuid;

      @ApiModelProperty(value = "品牌名称")
      @TableField("NAME")
    private String name;

      @ApiModelProperty(value = "品牌名称拼音")
      @TableField("NAME_PY")
    private String namePy;

      @ApiModelProperty(value = "排序")
      @TableField("SEQ")
    private BigDecimal seq;

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

      @ApiModelProperty(value = "品牌英文名称")
      @TableField("NAME_EN")
    private String nameEn;

      @ApiModelProperty(value = "类型，车辆、配件 DICT_BRAND_TYPE")
      @TableField("TYPE")
    private String type;

      @ApiModelProperty(value = "是否常用品牌")
      @TableField("IS_COMMON")
    private String isCommon;

      @ApiModelProperty(value = "附件的UUID")
      @TableField("ATTACHMENT_UUID")
    private String attachmentUuid;


}

package com.xmz.service.basedata.entity.pojo;

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
 * 车系
 * </p>
 *
 * @author hyc
 * @since 2021-06-08
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @ApiModel(value="BaseCarCategory对象", description="车系")
public class BaseCarCategory implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "主键ID")
        @TableId(value = "CATEGORY_UUID", type = IdType.ASSIGN_UUID)
      private String categoryUuid;

      @ApiModelProperty(value = "车系名称")
      @TableField("NAME")
    private String name;

      @ApiModelProperty(value = "车系名称拼音")
      @TableField("NAME_PY")
    private String namePy;

      @ApiModelProperty(value = "排序")
      @TableField("SEQ")
    private BigDecimal seq;

      @ApiModelProperty(value = "品牌")
      @TableField("BRAND_UUID")
    private String brandUuid;

      @ApiModelProperty(value = "厂家")
      @TableField("FACTORY_UUID")
    private String factoryUuid;

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

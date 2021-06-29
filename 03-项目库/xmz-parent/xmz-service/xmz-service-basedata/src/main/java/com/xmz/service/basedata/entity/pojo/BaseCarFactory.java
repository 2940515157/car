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
 * 厂家
 * </p>
 *
 * @author lm
 * @since 2021-05-31
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @ApiModel(value="BaseCarFactory对象", description="厂家")
public class BaseCarFactory implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "主键ID")
        @TableId(value = "FACTORY_UUID", type = IdType.ASSIGN_UUID)
      private String factoryUuid;

      @ApiModelProperty(value = "厂家名称")
      @TableField("NAME")
    private String name;

      @ApiModelProperty(value = "厂家名称拼音")
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

      @ApiModelProperty(value = "厂家全称")
      @TableField("FULL_NAME")
    private String fullName;

      @ApiModelProperty(value = "厂家全称拼音")
      @TableField("FULL_NAME_PY")
    private String fullNamePy;


}

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
 * 国家信息
 * </p>
 *
 * @author leiyaping
 * @since 2021-06-03
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @ApiModel(value="BaseCountrys对象", description="国家信息")
public class BaseCountrys implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "uuid")
        @TableId(value = "COUNTRY_UUID", type = IdType.ASSIGN_UUID)
      private String countryUuid;

      @ApiModelProperty(value = "国家名称")
      @TableField("COUNTRY_NAME")
    private String countryName;

      @ApiModelProperty(value = "国家代码")
      @TableField("COUNTRY_CODE")
    private String countryCode;

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

      @ApiModelProperty(value = "拼音")
      @TableField("PINYIN")
    private String pinyin;


}

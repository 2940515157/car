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
 * 城市
 * </p>
 *
 * @author 江志威
 * @since 2021-06-07
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @ApiModel(value="BaseCitys对象", description="城市")
public class BaseCitys implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "uuid")
        @TableId(value = "CITY_UUID", type = IdType.ASSIGN_UUID)
      private String cityUuid;

      @ApiModelProperty(value = "省份")
      @TableField("PROV_UUID")
    private String provUuid;

      @ApiModelProperty(value = "国家")
      @TableField("COUNTRY_UUID")
    private String countryUuid;

      @ApiModelProperty(value = "城市名称")
      @TableField("CITY_NAME")
    private String cityName;

      @ApiModelProperty(value = "城市代码")
      @TableField("CITY_CODE")
    private String cityCode;

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

      @ApiModelProperty(value = "排序")
      @TableField("SEQ_NO")
    private BigDecimal seqNo;


}

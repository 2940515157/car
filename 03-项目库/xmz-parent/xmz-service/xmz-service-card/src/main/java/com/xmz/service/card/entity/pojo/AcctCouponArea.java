package com.xmz.service.card.entity.pojo;

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
 * 优惠券使用范围
 * </p>
 *
 * @author liujiaxi
 * @since 2021-06-16
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @ApiModel(value="AcctCouponArea对象", description="优惠券使用范围")
public class AcctCouponArea implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "主键ID")
        @TableId(value = "ACA_UUID", type = IdType.ASSIGN_UUID)
      private String acaUuid;

      @ApiModelProperty(value = "优惠券UUID")
      @TableField("AC_UUID")
    private String acUuid;

      @ApiModelProperty(value = "使用范围类型")
      @TableField("CPA_TYPE")
    private String cpaType;

      @ApiModelProperty(value = "区域或门店UUID")
      @TableField("PK_UUID")
    private String pkUuid;

      @ApiModelProperty(value = "是否有效(Y:有效；N:无效)")
      @TableField("IS_ACTIVE")
    private String isActive;

      @ApiModelProperty(value = "创建(签单)日期")
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

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
 * 优惠券配件
 * </p>
 *
 * @author liujiaxi
 * @since 2021-06-19
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @ApiModel(value="AcctCouponParts对象", description="优惠券配件")
public class AcctCouponParts implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "主键ID")
        @TableId(value = "ACP_UUID", type = IdType.ASSIGN_UUID)
      private String acpUuid;

      @ApiModelProperty(value = "卡券UUID")
      @TableField("AC_UUID")
    private String acUuid;

      @ApiModelProperty(value = "配件类型UUID")
      @TableField("PART_TYPE_UUID")
    private String partTypeUuid;

      @ApiModelProperty(value = "数量")
      @TableField("ACP_NUMBER")
    private BigDecimal acpNumber;

      @ApiModelProperty(value = "单位")
      @TableField("UNIT")
    private String unit;

      @ApiModelProperty(value = "抵用总金额")
      @TableField("REPLACE_PRICE")
    private BigDecimal replacePrice;

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

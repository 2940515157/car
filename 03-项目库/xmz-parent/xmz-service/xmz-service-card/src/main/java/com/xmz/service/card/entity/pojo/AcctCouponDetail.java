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
 * 优惠券服务明细
 * </p>
 *
 * @author liujiaxi
 * @since 2021-06-15
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @ApiModel(value="AcctCouponDetail对象", description="优惠券服务明细")
public class AcctCouponDetail implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "主键ID")
        @TableId(value = "ACD_UUID", type = IdType.ASSIGN_UUID)
      private String acdUuid;

      @ApiModelProperty(value = "卡券UUID")
      @TableField("AC_UUID")
    private String acUuid;

      @ApiModelProperty(value = "服务可抵用金额")
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

      @ApiModelProperty(value = "服务项目UUID")
      @TableField("CATALOG_UUID")
    private String catalogUuid;

      @ApiModelProperty(value = "服务类型UUID")
      @TableField("ST_UUID")
    private String stUuid;

      @ApiModelProperty(value = "销售价格")
      @TableField("SALE_PRICE")
    private BigDecimal salePrice;

      @ApiModelProperty(value = "总部分摊比率")
      @TableField("ALLOCATION_BASE")
    private BigDecimal allocationBase;

      @ApiModelProperty(value = "门店分摊比率")
      @TableField("ALLOCATION_BRANCHES")
    private BigDecimal allocationBranches;

      @ApiModelProperty(value = "SA分摊比率")
      @TableField("ALLOCATION_SA")
    private BigDecimal allocationSa;

      @ApiModelProperty(value = "服务可抵用工时")
      @TableField("REPLACE_NUMBER")
    private BigDecimal replaceNumber;

    @TableField(exist = false)
     private String servTypeUuid;
}

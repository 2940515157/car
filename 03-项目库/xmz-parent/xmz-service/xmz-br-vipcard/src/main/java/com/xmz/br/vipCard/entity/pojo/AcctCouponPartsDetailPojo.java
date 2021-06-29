package com.xmz.br.vipCard.entity.pojo;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * 优惠券配件明细
 * </p>
 *
 * @author LLY
 * @since 2021-06-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("acct_coupon_parts_detail")
@ApiModel(value="AcctCouponPartsDetailPojo对象", description="优惠券配件明细")
public class AcctCouponPartsDetailPojo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId("ACPD_UUID")
    private String acpdUuid;

    @ApiModelProperty(value = "卡券配件UUID")
    @TableField("ACP_UUID")
    private String acpUuid;

    @ApiModelProperty(value = "数量")
    @TableField("ACPD_NUMBER")
    private BigDecimal acpdNumber;

    @ApiModelProperty(value = "单位")
    @TableField("UNIT")
    private String unit;

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

    @ApiModelProperty(value = "零配件UUID")
    @TableField("PART_UUID")
    private String partUuid;

    @ApiModelProperty(value = "卡卷UUID")
    @TableField("AC_UUID")
    private String acUuid;

    @ApiModelProperty(value = "配件类型UUID")
    @TableField("PART_TYPE_UUID")
    private String partTypeUuid;

    @ApiModelProperty(value = "配件可抵用金额")
    @TableField("REPLACE_PRICE")
    private BigDecimal replacePrice;

    @ApiModelProperty(value = "总部分摊比率")
    @TableField("ALLOCATION_BASE")
    private BigDecimal allocationBase;

    @ApiModelProperty(value = "门店分摊比率")
    @TableField("ALLOCATION_BRANCHES")
    private BigDecimal allocationBranches;

    @ApiModelProperty(value = "SA分摊比率")
    @TableField("ALLOCATION_SA")
    private BigDecimal allocationSa;

    @ApiModelProperty(value = "供应商分摊比率")
    @TableField("ALLOCATION_SUPPLIER")
    private BigDecimal allocationSupplier;

    @ApiModelProperty(value = "供应商UUID")
    @TableField("SUPPLIER_UUID")
    private String supplierUuid;

    @ApiModelProperty(value = "配件可抵用数量")
    @TableField("REPLACE_NUMBER")
    private BigDecimal replaceNumber;


}

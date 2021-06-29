package com.xmz.br.vipCard.entity.pojo;

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
 * 套餐配件明细
 * </p>
 *
 * @author zt
 * @since 2021-06-21
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @ApiModel(value="AcctPackagePartsDetail对象", description="套餐配件明细")
public class AcctPackagePartsDetail implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "主键ID")
        @TableId(value = "APPD_UUID", type = IdType.ASSIGN_UUID)
      private String appdUuid;

      @ApiModelProperty(value = "套餐配件UUID")
      @TableField("APP_UUID")
    private String appUuid;

      @ApiModelProperty(value = "成本价")
      @TableField("COST")
    private BigDecimal cost;

      @ApiModelProperty(value = "标准服务门店结算价")
      @TableField("STL_STD_PRICE")
    private BigDecimal stlStdPrice;

      @ApiModelProperty(value = "套餐服务销售价格")
      @TableField("SALE_PRICE")
    private BigDecimal salePrice;

      @ApiModelProperty(value = "服务标准报价")
      @TableField("STD_PRICE")
    private BigDecimal stdPrice;

      @ApiModelProperty(value = "套餐发行价(总价)")
      @TableField("PUBLISH_PRICE")
    private BigDecimal publishPrice;

      @ApiModelProperty(value = "SA提成比率")
      @TableField("COMMISSION_RATE_SA")
    private BigDecimal commissionRateSa;

      @ApiModelProperty(value = "技师提成比率(单次)")
      @TableField("COMMISSION_RATE_WORKER")
    private BigDecimal commissionRateWorker;

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

      @ApiModelProperty(value = "配件目录ID")
      @TableField("PART_TYPE_UUID")
    private String partTypeUuid;

      @ApiModelProperty(value = "套餐ID")
      @TableField("AP_UUID")
    private String apUuid;

      @ApiModelProperty(value = "数量")
      @TableField("APP_NUMBER")
    private BigDecimal appNumber;

      @ApiModelProperty(value = "单位 数据字典： DICT_UNIT 个、同、箱、升")
      @TableField("UNIT")
    private String unit;

      @ApiModelProperty(value = "单次数量")
      @TableField("PER_NUMBER")
    private BigDecimal perNumber;

      @ApiModelProperty(value = "单次协议服务门店结算价")
      @TableField("STL_AGREEMENT_PRICE")
    private BigDecimal stlAgreementPrice;


}

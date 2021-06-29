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
 * 套餐服务明细
 * </p>
 *
 * @author zt
 * @since 2021-06-16
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @ApiModel(value="AcctPackageDetail对象", description="套餐服务明细")
public class AcctPackageDetail implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "主键ID")
        @TableId(value = "APD_UUID", type = IdType.ASSIGN_UUID)
      private String apdUuid;

      @ApiModelProperty(value = "套餐UUID")
      @TableField("AP_UUID")
    private String apUuid;

      @ApiModelProperty(value = "服务项目UUID")
      @TableField("SERV_CATALOG_UUID")
    private String servCatalogUuid;

      @ApiModelProperty(value = "单次套餐成本价")
      @TableField("COST")
    private BigDecimal cost;

      @ApiModelProperty(value = "单次协议服务门店结算价")
      @TableField("STL_AGREEMENT_PRICE")
    private BigDecimal stlAgreementPrice;

      @ApiModelProperty(value = "标准服务门店结算价")
      @TableField("STL_STD_PRICE")
    private BigDecimal stlStdPrice;

      @ApiModelProperty(value = "单次套餐服务销售价格")
      @TableField("SALE_PRICE")
    private BigDecimal salePrice;

      @ApiModelProperty(value = "单次服务标准报价")
      @TableField("STD_PRICE")
    private BigDecimal stdPrice;

      @ApiModelProperty(value = "次数")
      @TableField("APD_NUMBER")
    private BigDecimal apdNumber;

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

      @ApiModelProperty(value = "消费提醒周期")
      @TableField("FEE_REMIND_CYCLE")
    private BigDecimal feeRemindCycle;

      @TableField(exist = false)
      private String catalogUuid;


}

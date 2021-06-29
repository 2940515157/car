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
 * 充值记录表
 * </p>
 *
 * @author zt
 * @since 2021-06-09
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @ApiModel(value="AcctRechargeRecord对象", description="充值记录表")
public class AcctRechargeRecord implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "主键")
        @TableId(value = "R_R_UUID", type = IdType.ASSIGN_UUID)
      private String rRUuid;

      @ApiModelProperty(value = "客户卡id")
      @TableField("CUST_CARD_UUID")
    private String custCardUuid;

      @ApiModelProperty(value = "卡号")
      @TableField("CARD_NO")
    private String cardNo;

      @ApiModelProperty(value = "充值门店")
      @TableField("BR_UUID")
    private String brUuid;

      @ApiModelProperty(value = "产品（总部定义的产品才能这么做）")
      @TableField("PRODUCT_UUID")
    private String productUuid;

      @ApiModelProperty(value = "操作人")
      @TableField("CUST_UUID")
    private String custUuid;

      @ApiModelProperty(value = "所属SA(哪个SA推销出去的)")
      @TableField("SA_UUID")
    private String saUuid;

      @ApiModelProperty(value = "折扣")
      @TableField("RATE")
    private BigDecimal rate;

      @ApiModelProperty(value = "实收金额")
      @TableField("TRUE_AMT")
    private BigDecimal trueAmt;

      @ApiModelProperty(value = "充值金额")
      @TableField("AMOUNT")
    private BigDecimal amount;

      @ApiModelProperty(value = "备注")
      @TableField("REMARK")
    private String remark;

      @ApiModelProperty(value = "扩展1")
      @TableField("EXT1")
    private String ext1;

      @ApiModelProperty(value = "扩展2")
      @TableField("EXT2")
    private String ext2;

      @ApiModelProperty(value = "扩展3")
      @TableField("EXT3")
    private String ext3;

      @ApiModelProperty(value = "扩展4")
      @TableField("EXT4")
    private String ext4;

      @ApiModelProperty(value = "扩展5")
      @TableField("EXT5")
    private String ext5;

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

      @ApiModelProperty(value = "类型（字典：白金，金卡，银卡，普通)")
      @TableField("CARD_TYPE")
    private String cardType;

      @ApiModelProperty(value = "资金账户资金主键")
      @TableField("ACCT_UUID")
    private String acctUuid;

      @ApiModelProperty(value = "充值前余额")
      @TableField("BALANCE")
    private BigDecimal balance;

    @TableField("STATUS")
    private String status;

      @ApiModelProperty(value = "储值卡结算折扣率,从储值卡中获取(冗余)")
      @TableField("SETTLE_PRICE")
    private BigDecimal settlePrice;

      @ApiModelProperty(value = "是否导入Y/N")
      @TableField("IMP_FLAG")
    private String impFlag;

      @ApiModelProperty(value = "结案人uuid")
      @TableField("CLOSE_SA_UUID")
    private String closeSaUuid;

      @ApiModelProperty(value = "结案人(SA)")
      @TableField("CLOSE_SA_NAME")
    private String closeSaName;

      @ApiModelProperty(value = "结案时间")
      @TableField("CLOSE_DATE")
    private Date closeDate;

      @ApiModelProperty(value = "资金是否归结总部(Y/N)")
      @TableField("IS_FUND_HEADQUARTER")
    private String isFundHeadquarter;


}

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
 * 门店应收应付明细
 * </p>
 *
 * @author zt
 * @since 2021-06-17
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @ApiModel(value="AcctBrArApItems对象", description="门店应收应付明细")
public class AcctBrArApItems implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "主键")
        @TableId(value = "ITEM_UUID", type = IdType.ASSIGN_UUID)
      private String itemUuid;

      @ApiModelProperty(value = "关联UUID，收款信息ITEM或者其他")
      @TableField("REF_UUID")
    private String refUuid;

      @ApiModelProperty(value = "业务类型,收款、抵扣增值等 DICT_AR_AP_BIZ_TYPE")
      @TableField("REF_TYPE")
    private String refType;

      @ApiModelProperty(value = "数据类型(应收、应付) DICT_AR_AP_TYPE")
      @TableField("TYPE")
    private String type;

      @ApiModelProperty(value = "门店uuid")
      @TableField("BR_UUID")
    private String brUuid;

      @ApiModelProperty(value = "金额")
      @TableField("TTL_AMT")
    private BigDecimal ttlAmt;

      @ApiModelProperty(value = "数据生成日期")
      @TableField("GEN_DATE")
    private Date genDate;

      @ApiModelProperty(value = "客户电话")
      @TableField("CUST_TEL")
    private String custTel;

      @ApiModelProperty(value = "车牌号 ")
      @TableField("LICENSE_PLATE")
    private String licensePlate;

      @ApiModelProperty(value = "付款方式 DICT_PAY_TYPE")
      @TableField("PAY_TYPE")
    private String payType;

      @ApiModelProperty(value = "银行交易流水单号(pos单号、优惠券号、充值卡号等)")
      @TableField("TRACE_NO")
    private String traceNo;

      @ApiModelProperty(value = "业务单据号")
      @TableField("REF_CODE")
    private String refCode;

      @ApiModelProperty(value = "结算单头UUID（总部门店结算后生成）")
      @TableField("HEAD_UUID")
    private String headUuid;

      @ApiModelProperty(value = "状态，初始、已结算")
      @TableField("STATUS")
    private String status;

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

      @ApiModelProperty(value = "第几次操作")
      @TableField("TIME_NO")
    private BigDecimal timeNo;

      @ApiModelProperty(value = "是否为反向操作")
      @TableField("IS_REVERSE")
    private String isReverse;

      @ApiModelProperty(value = "结算时间")
      @TableField("SETTLE_DATE")
    private Date settleDate;

      @ApiModelProperty(value = "业务发生额")
      @TableField("AR_AMT_BIZ")
    private BigDecimal arAmtBiz;

      @ApiModelProperty(value = "结算比例")
      @TableField("SETTLE_RATE")
    private BigDecimal settleRate;

      @ApiModelProperty(value = "客户UUID")
      @TableField("CUST_UUID")
    private String custUuid;

      @ApiModelProperty(value = "客户姓名")
      @TableField("CUST_NAME")
    private String custName;

      @ApiModelProperty(value = "业务类型,接车单、撤销预收款  DICT_AR_AP_BIZ_REF_TYPE")
      @TableField("BIZ_REF_TYPE")
    private String bizRefType;


}

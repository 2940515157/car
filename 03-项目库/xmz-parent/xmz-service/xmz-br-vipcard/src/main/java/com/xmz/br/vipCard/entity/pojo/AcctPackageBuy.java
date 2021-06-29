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
 * 套餐购买
 * </p>
 *
 * @author zt
 * @since 2021-06-11
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @ApiModel(value="AcctPackageBuy对象", description="套餐购买")
public class AcctPackageBuy implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "主键ID")
        @TableId(value = "APB_UUID", type = IdType.ASSIGN_UUID)
      private String apbUuid;

      @ApiModelProperty(value = "套餐UUID")
      @TableField("AP_UUID")
    private String apUuid;

      @ApiModelProperty(value = "客户UUID")
      @TableField("CUSTOMER_UUID")
    private String customerUuid;

      @ApiModelProperty(value = "门店UUID")
      @TableField("BR_UUID")
    private String brUuid;

      @ApiModelProperty(value = "出售人UUID")
      @TableField("SALE_USER_UUID")
    private String saleUserUuid;

      @ApiModelProperty(value = "出售时间")
      @TableField("SALE_DATE")
    private Date saleDate;

      @ApiModelProperty(value = "出售价格")
      @TableField("PAY_PRICE")
    private BigDecimal payPrice;

      @ApiModelProperty(value = "付款方式 数据字典：现金支付、POS、微信支付、网银转账")
      @TableField("PAY_TYPE")
    private String payType;

      @ApiModelProperty(value = "交易单号")
      @TableField("TRANSACTION_NO")
    private String transactionNo;

      @ApiModelProperty(value = "备注")
      @TableField("REMARK")
    private String remark;

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

      @ApiModelProperty(value = "实际付款")
      @TableField("ACTUAL_PAY_PRICE")
    private BigDecimal actualPayPrice;

      @ApiModelProperty(value = "状态")
      @TableField("STATUS")
    private String status;

      @ApiModelProperty(value = "已购买套餐UUID")
      @TableField("APC_UUID")
    private String apcUuid;


}

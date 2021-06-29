package com.xmz.br.vipCard.entity.pojo;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * 优惠券购买及消费跟踪
 * </p>
 *
 * @author LLY
 * @since 2021-06-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("acct_coupon_buy_record")
@ApiModel(value="AcctCouponBuyRecordPojo对象", description="优惠券购买及消费跟踪")
public class AcctCouponBuyRecordPojo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "ACBR_UUID",type = IdType.ASSIGN_UUID)
    private String acbrUuid;

    @ApiModelProperty(value = "门店UUID")
    @TableField("BR_UUID")
    private String brUuid;

    @ApiModelProperty(value = "SA-UUID")
    @TableField("SA_UUID")
    private String saUuid;

    @ApiModelProperty(value = "优惠券UUID")
    @TableField("AC_UUID")
    private String acUuid;

    @ApiModelProperty(value = "优惠券号")
    @TableField("AC_NUMBER")
    private String acNumber;

    @ApiModelProperty(value = "优惠券单价")
    @TableField("PRICE")
    private BigDecimal price;

    @ApiModelProperty(value = "状态 数据字典：正常、转赠、已使用、已过期")
    @TableField("STATUS")
    private String status;

    @ApiModelProperty(value = "转赠客户UUID")
    @TableField("CUST_UUID")
    private String custUuid;

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

    @ApiModelProperty(value = "消费时间")
    @TableField("USE_DATE")
    private Date useDate;

    @ApiModelProperty(value = "消费者UUID")
    @TableField("CONSUMER_UUID")
    private String consumerUuid;

    @ApiModelProperty(value = "转赠时间")
    @TableField("ATTORN_DATE")
    private Date attornDate;

    @ApiModelProperty(value = "客户已购买组合产品的UUID")
    @TableField("ACPC_UUID")
    private String acpcUuid;

    @ApiModelProperty(value = "销售价格")
    @TableField("SAL_PRICE")
    private BigDecimal salPrice;

    @ApiModelProperty(value = "实收金额 现金、pos、微信支付:等于售价 储值卡支付：=售价*支付时的折扣率 acct_rev_pay_items.discount_rate")
    @TableField("REL_PRICE")
    private BigDecimal relPrice;

    @ApiModelProperty(value = "是否导入Y/N")
    @TableField("IMP_FLAG")
    private String impFlag;


}

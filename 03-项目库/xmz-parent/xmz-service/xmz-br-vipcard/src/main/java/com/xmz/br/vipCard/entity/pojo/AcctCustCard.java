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
 * 客户卡
 * </p>
 *
 * @author zt
 * @since 2021-06-04
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @ApiModel(value="AcctCustCard对象", description="客户卡")
public class AcctCustCard implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "主键")
        @TableId(value = "CUST_CARD_UUID", type = IdType.ASSIGN_UUID)
      private String custCardUuid;

      @ApiModelProperty(value = "门店领卡主键")
      @TableField("CARD_UUID")
    private String cardUuid;

      @ApiModelProperty(value = "卡号")
      @TableField("CARD_NO")
    private String cardNo;

      @ApiModelProperty(value = "发卡门店")
      @TableField("BR_UUID")
    private String brUuid;

      @ApiModelProperty(value = "领卡人")
      @TableField("CUST_UUID")
    private String custUuid;

      @ApiModelProperty(value = "发放人（默认登陆用户）")
      @TableField("SA_UUID")
    private String saUuid;

      @ApiModelProperty(value = "车辆信息")
      @TableField("CAR_UUID")
    private String carUuid;

      @ApiModelProperty(value = "类型（字典：白金，金卡，银卡，普通)")
      @TableField("CARD_TYPE")
    private String cardType;

      @ApiModelProperty(value = "卡积分")
      @TableField("CARD_INTEGRAL")
    private BigDecimal cardIntegral;

      @ApiModelProperty(value = "卡余额")
      @TableField("BALANCE")
    private BigDecimal balance;

      @ApiModelProperty(value = "折扣率(加权平均)")
      @TableField("DISCOUNT_RATE")
    private BigDecimal discountRate;

      @ApiModelProperty(value = "状态(失效，挂失，冻结，注销，正常)")
      @TableField("STATUS")
    private String status;

      @ApiModelProperty(value = "生效日期")
      @TableField("BEGIN_DATE")
    private Date beginDate;

      @ApiModelProperty(value = "失效日期(预留)")
      @TableField("END_DATE")
    private Date endDate;

      @ApiModelProperty(value = "上次的卡号")
      @TableField("LAST_CARD_NO")
    private String lastCardNo;

      @ApiModelProperty(value = "效验码([持卡人+余额]加密,每次消费验证)")
      @TableField("CHECK_CODE")
    private String checkCode;

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

      @ApiModelProperty(value = "资金账户资金主键")
      @TableField("CUST_ITEM_UUID")
    private String custItemUuid;

      @ApiModelProperty(value = "结算折扣率（有协议折扣率就用它，没有就是成本折扣率乘以发行折扣率)")
      @TableField("SETTLE_RATE")
    private BigDecimal settleRate;

      @ApiModelProperty(value = "批量开卡主键")
      @TableField("BATCH_UUID")
    private String batchUuid;

      @ApiModelProperty(value = "是否激活(Y，N)")
      @TableField("IS_ACTIVATION")
    private String isActivation;

      @ApiModelProperty(value = "开卡日期")
      @TableField("OPENED_DATE")
    private Date openedDate;

      @ApiModelProperty(value = "是否导入Y/N")
      @TableField("IMP_FLAG")
    private String impFlag;

      @ApiModelProperty(value = "导入卡余额")
      @TableField("IMP_BALANCE")
    private BigDecimal impBalance;

      @ApiModelProperty(value = "导入结算折扣率")
      @TableField("IMP_SET_RATE")
    private BigDecimal impSetRate;

      @ApiModelProperty(value = "导入折扣率")
      @TableField("IMP_DISCOUNT_RATE")
    private BigDecimal impDiscountRate;


}

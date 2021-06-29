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
 * 批量开卡
 * </p>
 *
 * @author liujiaxi
 * @since 2021-06-07
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @ApiModel(value="AcctBatchCard对象", description="批量开卡")
public class AcctBatchCard implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "主键")
        @TableId(value = "BATCH_UUID", type = IdType.ASSIGN_UUID)
      private String batchUuid;

      @ApiModelProperty(value = "卡库主键")
      @TableField("CARD_STORE_UUID")
    private String cardStoreUuid;

      @ApiModelProperty(value = "卡号前缀")
      @TableField("CARD_PREFIX")
    private String cardPrefix;

      @ApiModelProperty(value = "卡号开始")
      @TableField("CARD_NO_BEG")
    private String cardNoBeg;

      @ApiModelProperty(value = "卡号结束")
      @TableField("CARD_NO_END")
    private String cardNoEnd;

      @ApiModelProperty(value = "共计张")
      @TableField("CARD_NUM")
    private BigDecimal cardNum;

      @ApiModelProperty(value = "单张充值金额")
      @TableField("CARD_BALANCE")
    private BigDecimal cardBalance;

      @ApiModelProperty(value = "应付")
      @TableField("SHOULD_AMT")
    private BigDecimal shouldAmt;

      @ApiModelProperty(value = "实付")
      @TableField("TRUE_AMT")
    private BigDecimal trueAmt;

      @ApiModelProperty(value = "折扣率")
      @TableField("DISCOUNT_RATE")
    private BigDecimal discountRate;

      @ApiModelProperty(value = "集团(DICT_BUY_OBJECT_TYPE)")
      @TableField("BUY_OBJECT_TYPE")
    private String buyObjectType;

      @ApiModelProperty(value = "购买对象")
      @TableField("BUY_OBJECT")
    private String buyObject;

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

      @ApiModelProperty(value = "购买人")
      @TableField("PURCHASER")
    private String purchaser;

      @ApiModelProperty(value = "购买人电话")
      @TableField("PHONE")
    private String phone;


}

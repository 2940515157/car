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
 * 储值卡产品定义
 * </p>
 *
 * @author liujiaxi
 * @since 2021-06-01
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @ApiModel(value="AcctCardSetup对象", description="储值卡产品定义")
public class AcctCardSetup implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "主键")
        @TableId(value = "ACS_UUID", type = IdType.ASSIGN_UUID)
      private String acsUuid;

      @ApiModelProperty(value = "产品名称")
      @TableField("ACS_NAME")
    private String acsName;

      @ApiModelProperty(value = "类型(小拇指卡等)")
      @TableField("ACS_TYPE")
    private String acsType;

      @ApiModelProperty(value = "产品名称拼音")
      @TableField("PINYIN")
    private String pinyin;

      @ApiModelProperty(value = "售价")
      @TableField("SALE_PRICE")
    private BigDecimal salePrice;

      @ApiModelProperty(value = "赠送价值")
      @TableField("GIFT_PRICE")
    private BigDecimal giftPrice;

      @ApiModelProperty(value = "总价值")
      @TableField("TOTAL_PRICE")
    private BigDecimal totalPrice;

      @ApiModelProperty(value = "有效期(开始)")
      @TableField("START_DATE")
    private Date startDate;

      @ApiModelProperty(value = "有效期(结束)")
      @TableField("END_DATE")
    private Date endDate;

      @ApiModelProperty(value = "状态 数据字典：草稿 申请中 上架 下架")
      @TableField("STATUS")
    private String status;

      @ApiModelProperty(value = "销售增值比率")
      @TableField("SALE_RATE")
    private BigDecimal saleRate;

      @ApiModelProperty(value = "描述")
      @TableField("CARD_DESC")
    private String cardDesc;

      @ApiModelProperty(value = "核算人UUID")
      @TableField("CHECK_UUID")
    private String checkUuid;

      @ApiModelProperty(value = "设定佣金分成比率人UUID")
      @TableField("SET_CR_UUID")
    private String setCrUuid;

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

      @ApiModelProperty(value = "结算价格")
      @TableField("SETTLE_PRICE")
    private BigDecimal settlePrice;

      @ApiModelProperty(value = "成本折扣率")
      @TableField("COST_RATE")
    private BigDecimal costRate;

      @ApiModelProperty(value = "协议折扣率（有协议折扣率就用它，没有就是成本折扣率乘以发行折扣率）")
      @TableField("PACT_RATE")
    private BigDecimal pactRate;

      @ApiModelProperty(value = "发行折扣率")
      @TableField("ISSUE_RATE")
    private BigDecimal issueRate;

      @ApiModelProperty(value = "是否自动专属")
      @TableField("CAN_OWNER")
    private String canOwner;


}

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
 * 优惠券
 * </p>
 *
 * @author ;liujiaxi
 * @since 2021-06-07
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @ApiModel(value="AcctCoupon对象", description="优惠券")
public class AcctCoupon implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "主键ID")
        @TableId(value = "AC_UUID", type = IdType.ASSIGN_UUID)
      private String acUuid;

      @ApiModelProperty(value = "优惠券编号规则")
      @TableField("AC_NUMBER")
    private String acNumber;

      @ApiModelProperty(value = "卡券名称")
      @TableField("AC_NAME")
    private String acName;

      @ApiModelProperty(value = "卡券类型")
      @TableField("AC_TYPE")
    private String acType;

      @ApiModelProperty(value = "卡券类型")
      @TableField("AC_USE_TYPE")
    private String acUseType;

      @ApiModelProperty(value = "卡券售价(B币购买价格)")
      @TableField("TOTAL_PRICE")
    private BigDecimal totalPrice;

      @ApiModelProperty(value = "抵用总金额(服务+配件)")
      @TableField("REPLACE_PRICE")
    private BigDecimal replacePrice;

      @ApiModelProperty(value = "有效期(开始)")
      @TableField("START_DATE")
    private Date startDate;

      @ApiModelProperty(value = "有效期(结束)")
      @TableField("END_DATE")
    private Date endDate;

      @ApiModelProperty(value = "状态")
      @TableField("STATUS")
    private String status;

      @ApiModelProperty(value = "上架时间")
      @TableField("PUT_ON_DATE")
    private Date putOnDate;

      @ApiModelProperty(value = "下架时间")
      @TableField("PULL_OFF_DATE")
    private Date pullOffDate;

      @ApiModelProperty(value = "创建人UUID")
      @TableField("CREATOR_UUID")
    private String creatorUuid;

      @ApiModelProperty(value = "审核UUID")
      @TableField("CHECK_UUID")
    private String checkUuid;

      @ApiModelProperty(value = "备注")
      @TableField("REMARK")
    private String remark;

      @ApiModelProperty(value = "扩展字段1")
      @TableField("EXT1")
    private String ext1;

      @ApiModelProperty(value = "扩展字段2")
      @TableField("EXT2")
    private String ext2;

      @ApiModelProperty(value = "扩展字段3")
      @TableField("EXT3")
    private String ext3;

      @ApiModelProperty(value = "扩展字段4")
      @TableField("EXT4")
    private String ext4;

      @ApiModelProperty(value = "扩展字段5")
      @TableField("EXT5")
    private String ext5;

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

      @ApiModelProperty(value = "发行总数")
      @TableField("TOTAL_COUNT")
    private BigDecimal totalCount;

      @ApiModelProperty(value = "卡卷名称拼音")
      @TableField("PINYIN")
    private String pinyin;

      @ApiModelProperty(value = "剩余数量")
      @TableField("REMAINDER")
    private BigDecimal remainder;

      @ApiModelProperty(value = "附件ID")
      @TableField("ATTACHMENT_UUID")
    private String attachmentUuid;

      @ApiModelProperty(value = "业务分类 DICT_COUPON_BIZ_TYPE")
      @TableField("BIZ_TYPE")
    private String bizType;

      @ApiModelProperty(value = "是否属于组合产品,Y/N")
      @TableField("COMPOSITE_FLAG")
    private String compositeFlag;

      @ApiModelProperty(value = "销售价格")
      @TableField("SAL_PRICE")
    private BigDecimal salPrice;


}

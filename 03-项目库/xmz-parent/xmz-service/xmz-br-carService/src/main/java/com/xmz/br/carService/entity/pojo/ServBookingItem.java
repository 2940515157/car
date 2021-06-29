package com.xmz.br.carService.entity.pojo;

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
 * 预约服务项目
 * </p>
 *
 * @author 罗俊
 * @since 2021-06-17
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @ApiModel(value="ServBookingItem对象", description="预约服务项目")
public class ServBookingItem implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "主键ID")
        @TableId(value = "ITEM_UUID", type = IdType.ASSIGN_UUID)
      private String itemUuid;

      @ApiModelProperty(value = "预约单ID")
      @TableField("SB_UUID")
    private String sbUuid;

      @ApiModelProperty(value = "服务项目(关联base_serv_catalog)")
      @TableField("SERV_ITEM_UUID")
    private String servItemUuid;

      @ApiModelProperty(value = "标准工时")
      @TableField("STD_MAN_HOUR")
    private BigDecimal stdManHour;

      @ApiModelProperty(value = "标准价格")
      @TableField("STD_PRICE")
    private BigDecimal stdPrice;

      @ApiModelProperty(value = "油漆标准面数")
      @TableField("STD_SIDE_NUM")
    private BigDecimal stdSideNum;

      @ApiModelProperty(value = "折扣")
      @TableField("DISCOUNT")
    private BigDecimal discount;

      @ApiModelProperty(value = "折后价")
      @TableField("PRICE")
    private BigDecimal price;

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

      @ApiModelProperty(value = "备注")
      @TableField("REMARK")
    private String remark;

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

      @ApiModelProperty(value = "标准金额")
      @TableField("STD_AMT")
    private BigDecimal stdAmt;


}

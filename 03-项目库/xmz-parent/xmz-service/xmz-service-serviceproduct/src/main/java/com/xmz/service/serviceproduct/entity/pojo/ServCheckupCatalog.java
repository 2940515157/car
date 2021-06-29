package com.xmz.service.serviceproduct.entity.pojo;

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
 * 点检项目
 * </p>
 *
 * @author zt
 * @since 2021-05-28
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @ApiModel(value="ServCheckupCatalog对象", description="点检项目")
public class ServCheckupCatalog implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "主键")
        @TableId(value = "SCC_UUID", type = IdType.ASSIGN_UUID)
      private String sccUuid;

      @ApiModelProperty(value = "目录ID")
      @TableField("SCD_UUID")
    private String scdUuid;

      @ApiModelProperty(value = "点检项目名称")
      @TableField("CATALOG_NAME")
    private String catalogName;

      @ApiModelProperty(value = "点检项目代码")
      @TableField("CATALOG_CODE")
    private String catalogCode;

      @ApiModelProperty(value = "排序")
      @TableField("SEQ_NO")
    private BigDecimal seqNo;

      @ApiModelProperty(value = "正常类")
      @TableField("NORMAL_UUID")
    private String normalUuid;

      @ApiModelProperty(value = "轻微异常")
      @TableField("SLIGHT_UUID")
    private String slightUuid;

      @ApiModelProperty(value = "严重异常")
      @TableField("MORE_UUID")
    private String moreUuid;

      @ApiModelProperty(value = "剩余里程/时间")
      @TableField("IS_SET_MILE")
    private String isSetMile;

      @ApiModelProperty(value = "是否禁用(Y/N)")
      @TableField("DISNABLED")
    private String disnabled;

      @ApiModelProperty(value = "操作人")
      @TableField("OP_USER_UUID")
    private String opUserUuid;

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

      @ApiModelProperty(value = "拼音")
      @TableField("PINYIN")
    private String pinyin;

      @ApiModelProperty(value = "提醒产生时间(天)")
      @TableField("REMIND_START_TIME")
    private BigDecimal remindStartTime;

      @ApiModelProperty(value = "再次点检时间(天)")
      @TableField("AGIN_CHECKUP_TIME")
    private BigDecimal aginCheckupTime;


}

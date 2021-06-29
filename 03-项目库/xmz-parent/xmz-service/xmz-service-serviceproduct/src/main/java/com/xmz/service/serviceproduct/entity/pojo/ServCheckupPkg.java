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
 * 点检包
 * </p>
 *
 * @author zt
 * @since 2021-05-31
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @ApiModel(value="ServCheckupPkg对象", description="点检包")
public class ServCheckupPkg implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "主键")
        @TableId(value = "SCP_UUID", type = IdType.ASSIGN_UUID)
      private String scpUuid;

      @ApiModelProperty(value = "点检包名称")
      @TableField("PKG_NAME")
    private String pkgName;

      @ApiModelProperty(value = "状态(init[未上架],confirm[上架])")
      @TableField("STATUS")
    private String status;

      @ApiModelProperty(value = "点检包简介")
      @TableField("DESCRIPTION")
    private String description;

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

      @ApiModelProperty(value = "匹配里程大值")
      @TableField("MATCH_MILEAGE_MAX")
    private BigDecimal matchMileageMax;

      @ApiModelProperty(value = "匹配里程小值")
      @TableField("MATCH_MILEAGE_MIN")
    private BigDecimal matchMileageMin;


}

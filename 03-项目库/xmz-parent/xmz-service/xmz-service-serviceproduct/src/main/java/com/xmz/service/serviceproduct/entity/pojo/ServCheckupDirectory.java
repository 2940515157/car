package com.xmz.service.serviceproduct.entity.pojo;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 点检目录
 * </p>
 *
 * @author hwb
 * @since 2021-05-26
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @ApiModel(value="ServCheckupDirectory对象", description="点检目录")
public class ServCheckupDirectory implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "主键")
        @TableId(value = "SCD_UUID", type = IdType.ASSIGN_UUID)
      private String scdUuid;

      @ApiModelProperty(value = "目录名称")
      @TableField("DIRECTORY_NAME")
    private String directoryName;

      @ApiModelProperty(value = "名称代码")
      @TableField("DIRECTORY_CODE")
    private String directoryCode;

      @ApiModelProperty(value = "级别")
      @TableField("DIRECTORY_LEVEL")
    private BigDecimal directoryLevel;

      @ApiModelProperty(value = "排序")
      @TableField("SEQ_NO")
    private BigDecimal seqNo;

      @ApiModelProperty(value = "父类ID")
      @TableField("PARTENT_UUID")
    private String partentUuid;

      @ApiModelProperty(value = "录入人")
      @TableField("OP_USER_UUID")
    private String opUserUuid;

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
      @TableField(exist = false)
    private List<ServCheckupDirectory> children;
    @TableField(exist = false)
    private String label;



}

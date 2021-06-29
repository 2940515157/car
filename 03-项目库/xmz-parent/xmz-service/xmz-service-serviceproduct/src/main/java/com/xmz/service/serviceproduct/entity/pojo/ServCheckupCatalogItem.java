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
 * 点检项目明细
 * </p>
 *
 * @author zt
 * @since 2021-05-28
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @ApiModel(value="ServCheckupCatalogItem对象", description="点检项目明细")
public class ServCheckupCatalogItem implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "主键")
        @TableId(value = "SCCI_UUID", type = IdType.ASSIGN_UUID)
      private String scciUuid;

      @ApiModelProperty(value = "点检项目ID")
      @TableField("SCC_UUID")
    private String sccUuid;

      @ApiModelProperty(value = "类型(正常类[normal],轻微异常[slight],严重异常[more])")
      @TableField("TYPE")
    private String type;

      @ApiModelProperty(value = "异常描述")
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

      @ApiModelProperty(value = "排序")
      @TableField("SEQ_NO")
    private BigDecimal seqNo;


}

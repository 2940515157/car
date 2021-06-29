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
 * 服务类型
 * </p>
 *
 * @author hwb
 * @since 2021-06-03
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @ApiModel(value="BaseServType对象", description="服务类型")
public class BaseServType implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "uuid")
        @TableId(value = "ST_UUID", type = IdType.ASSIGN_UUID)
      private String stUuid;

      @ApiModelProperty(value = "服务类型名称")
      @TableField("SERV_TYPE_NAME")
    private String servTypeName;

      @ApiModelProperty(value = "服务类型编号")
      @TableField("SERV_TYPE_NO")
    private String servTypeNo;

      @ApiModelProperty(value = "上级类型ID")
      @TableField("PARENT_UUID")
    private String parentUuid;

      @ApiModelProperty(value = "树等级")
      @TableField("TREE_LEVEL")
    private BigDecimal treeLevel;

      @ApiModelProperty(value = "树代码")
      @TableField("TREE_CODE")
    private String treeCode;

      @ApiModelProperty(value = "大类（美容、机修、油漆、钣金）字典")
      @TableField("TOP_TYPE")
    private String topType;

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
      @TableField("SEQ")
    private BigDecimal seq;

    private String label;

    @TableField(exist = false)
    private List<BaseServType> children;
}

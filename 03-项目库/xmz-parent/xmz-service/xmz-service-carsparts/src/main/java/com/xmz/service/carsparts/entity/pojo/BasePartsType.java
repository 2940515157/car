package com.xmz.service.carsparts.entity.pojo;

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
 * 配件目录
 * </p>
 *
 * @author 江志威
 * @since 2021-05-31
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @ApiModel(value="BasePartsType对象", description="配件目录")
public class BasePartsType implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "UUID")
        @TableId(value = "PART_TYPE_UUID", type = IdType.ASSIGN_UUID)
      private String partTypeUuid;

      @ApiModelProperty(value = "配件大类（美容、机修、油漆）")
      @TableField("SERV_TYPE")
    private String servType;

      @ApiModelProperty(value = "配件类型编号")
      @TableField("CODE")
    private String code;

      @ApiModelProperty(value = "名称")
      @TableField("NAME")
    private String name;

      @ApiModelProperty(value = "名称拼音")
      @TableField("NAME_PY")
    private String namePy;

      @ApiModelProperty(value = "上级目录ID")
      @TableField("PARENT_TYPE_UUID")
    private String parentTypeUuid;

      @ApiModelProperty(value = "目录级别")
      @TableField("PARTS_LEVEL")
    private BigDecimal partsLevel;

      @ApiModelProperty(value = "数据维护方式（总部手动、立洋数据导入、手动+立洋）")
      @TableField("DATA_MT_TYPE")
    private String dataMtType;

      @ApiModelProperty(value = "树形编码，例如：100.001.001")
      @TableField("TREE_CODE")
    private String treeCode;

      @ApiModelProperty(value = "是否常用")
      @TableField("IS_COMMON")
    private String isCommon;

      @ApiModelProperty(value = "匹配类型（通用件、常用件）DICT_MATCH_TYPE")
      @TableField("MATCH_TYPE")
    private String matchType;

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
      @TableField("SEQ")
    private BigDecimal seq;

      @ApiModelProperty(value = "匹配的base_car_parts_sku表字段名(当匹配类型为通用件时适用)")
      @TableField("MATCH_COLUMN")
    private String matchColumn;

      @ApiModelProperty(value = "基本单位")
      @TableField("UNIT")
    private String unit;

      @ApiModelProperty(value = "别名1")
      @TableField("ALIAS1")
    private String alias1;

      @ApiModelProperty(value = "别名2")
      @TableField("ALIAS2")
    private String alias2;

      @ApiModelProperty(value = "别名3")
      @TableField("ALIAS3")
    private String alias3;

      @ApiModelProperty(value = "别名4")
      @TableField("ALIAS4")
    private String alias4;

      @ApiModelProperty(value = "别名5")
      @TableField("ALIAS5")
    private String alias5;

      @ApiModelProperty(value = "精品类型(精品专用)")
      @TableField("ACCE_TYPE")
    private String acceType;

      @ApiModelProperty(value = "精品增值系数(精品专用)")
      @TableField("ACCE_RATE")
    private BigDecimal acceRate;

      @ApiModelProperty(value = "门店是否能增加sku")
      @TableField("BR_CAN_ADD")
    private String brCanAdd;

      @ApiModelProperty(value = "统计分类 DICT_STAT_TYPE")
      @TableField("STAT_TYPE")
    private String statType;

      @ApiModelProperty(value = "统计方法 DICT_STAT_METHOD")
      @TableField("STAT_METHOD")
    private String statMethod;

      @ApiModelProperty(value = "精友目录编号")
      @TableField("JY_TYPE_CODE")
    private String jyTypeCode;

      @ApiModelProperty(value = "配件类型（易损件、车型件）DICT_PART_MKT_TYPE")
      @TableField("PART_MKT_TYPE")
    private String partMktType;

      @ApiModelProperty(value = "加价率")
      @TableField("PRICE_ADD_RATE")
    private BigDecimal priceAddRate;

      @ApiModelProperty(value = "报表统计分类 DICT_RPT_STAT_TYPE")
      @TableField("RPT_STAT_TYPE")
    private String rptStatType;

      @ApiModelProperty(value = "是否启用Y/N")
      @TableField("USABLE_FLAG")
    private String usableFlag;

    @TableField(exist = false)
    private List<BasePartsType> children;

}

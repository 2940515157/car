package com.xmz.service.carsparts.entity.pojo;

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
 * 配件产品库(含品牌规格型号)(sku级)
 * </p>
 *
 * @author 江志威
 * @since 2021-06-16
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @ApiModel(value="BaseCarPartsSku对象", description="配件产品库(含品牌规格型号)(sku级)")
public class BaseCarPartsSku implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "uuid")
        @TableId(value = "PART_UUID", type = IdType.ASSIGN_UUID)
      private String partUuid;

      @ApiModelProperty(value = "配件目录，对应配件目录表")
      @TableField("PART_TYPE")
    private String partType;

      @ApiModelProperty(value = "立洋编号")
      @TableField("LY_CODE")
    private String lyCode;

      @ApiModelProperty(value = "商品名称")
      @TableField("PART_NAME")
    private String partName;

      @ApiModelProperty(value = "编号")
      @TableField("PART_NO")
    private String partNo;

      @ApiModelProperty(value = "品牌")
      @TableField("BRAND")
    private String brand;

      @ApiModelProperty(value = "型号")
      @TableField("MODEL")
    private String model;

      @ApiModelProperty(value = "规格")
      @TableField("SPEC")
    private String spec;

      @ApiModelProperty(value = "雨刷接口")
      @TableField("WIPER_INTERFACE")
    private String wiperInterface;

      @ApiModelProperty(value = "主刷尺寸(英寸)")
      @TableField("MAIN_LENGTH_IN")
    private BigDecimal mainLengthIn;

      @ApiModelProperty(value = "主刷尺寸(毫米)")
      @TableField("MAIN_LENGTH_MM")
    private BigDecimal mainLengthMm;

      @ApiModelProperty(value = "副刷尺寸(英寸)")
      @TableField("SUB_LENGTH_IN")
    private BigDecimal subLengthIn;

      @ApiModelProperty(value = "副刷尺寸(毫米)")
      @TableField("SUB_LENGTH_MM")
    private BigDecimal subLengthMm;

      @ApiModelProperty(value = "外圆直径（mm）")
      @TableField("OUT_DIAM")
    private BigDecimal outDiam;

      @ApiModelProperty(value = "标准厚度（mm）")
      @TableField("STD_THICKNESS")
    private BigDecimal stdThickness;

      @ApiModelProperty(value = "极限厚度（mm）")
      @TableField("MIN_THICKNESS")
    private BigDecimal minThickness;

      @ApiModelProperty(value = "蹄片类型(舒适型、安全型)")
      @TableField("PAD_TYPE")
    private String padType;

      @ApiModelProperty(value = "宽度（mm）")
      @TableField("WIDTH")
    private BigDecimal width;

      @ApiModelProperty(value = "高度（mm）")
      @TableField("HIGHT")
    private BigDecimal hight;

      @ApiModelProperty(value = "厚度（mm）")
      @TableField("THICKNESS")
    private BigDecimal thickness;

      @ApiModelProperty(value = "标准内径（mm）")
      @TableField("STD_INNER_DIAM")
    private BigDecimal stdInnerDiam;

      @ApiModelProperty(value = "极限内径（mm）")
      @TableField("MIN_INNER_DIAM")
    private BigDecimal minInnerDiam;

      @ApiModelProperty(value = "直径（mm）")
      @TableField("DIAM")
    private BigDecimal diam;

      @ApiModelProperty(value = "总部标准价")
      @TableField("STD_PRICE")
    private BigDecimal stdPrice;

      @ApiModelProperty(value = "总部标准加价率")
      @TableField("STD_PRICE_ADD_RATE")
    private BigDecimal stdPriceAddRate;

      @ApiModelProperty(value = "备注")
      @TableField("REMARK")
    private String remark;

      @ApiModelProperty(value = "匹配类型（通用件、常用件）  废弃")
      @TableField("MATCH_TYPE")
    private String matchType;

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

      @ApiModelProperty(value = "扩展6")
      @TableField("EXT6")
    private String ext6;

      @ApiModelProperty(value = "扩展7")
      @TableField("EXT7")
    private String ext7;

      @ApiModelProperty(value = "扩展8")
      @TableField("EXT8")
    private String ext8;

      @ApiModelProperty(value = "扩展9")
      @TableField("EXT9")
    private String ext9;

      @ApiModelProperty(value = "扩展10")
      @TableField("EXT10")
    private String ext10;

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

      @ApiModelProperty(value = "单位")
      @TableField("UNIT")
    private String unit;

      @ApiModelProperty(value = "配件条码")
      @TableField("BARCODE")
    private String barcode;

      @ApiModelProperty(value = "内部编码（自动生成）")
      @TableField("PART_NO_INNER")
    private String partNoInner;

      @ApiModelProperty(value = "品牌+名称的拼音")
      @TableField("PINYIN")
    private String pinyin;

      @ApiModelProperty(value = "配件显示名称品牌+商品名称+规格+型号")
      @TableField("SHOW_NAME")
    private String showName;

      @ApiModelProperty(value = "销售促销信息")
      @TableField("SAL_REMARK")
    private String salRemark;

      @ApiModelProperty(value = "配件图片地址")
      @TableField("PIC_URL")
    private String picUrl;

      @ApiModelProperty(value = "配送类型 DICT_DELIVER_TYPE,集采/当地代理商/云代理商/自有库存/其他")
      @TableField("DELIVER_TYPE")
    private String deliverType;

      @ApiModelProperty(value = "精品类型")
      @TableField("PAT_UUID")
    private String patUuid;

      @ApiModelProperty(value = "门店UUID(为空表示总部定义的sku，所有门店都可用；不为空表示此门店专用)")
      @TableField("BR_UUID")
    private String brUuid;

      @ApiModelProperty(value = "是否为其他配件 Y/N")
      @TableField("OTHER_FLAG")
    private String otherFlag;

      @ApiModelProperty(value = "时间扩展1")
      @TableField("EXT_TIME1")
    private Date extTime1;

      @ApiModelProperty(value = "时间扩展2")
      @TableField("EXT_TIME2")
    private Date extTime2;

      @ApiModelProperty(value = "时间扩展3")
      @TableField("EXT_TIME3")
    private Date extTime3;

      @ApiModelProperty(value = "OE号(可能一个oe号对应多个配件)")
      @TableField("OE_NO")
    private String oeNo;

      @ApiModelProperty(value = "配件类型（易损件、车型件）DICT_PART_MKT_TYPE")
      @TableField("PART_MKT_TYPE")
    private String partMktType;

      @ApiModelProperty(value = "车型件类型（原厂件、配套件、品牌件、适用件） DICT_PART_MFR_TYPE")
      @TableField("PART_MFR_TYPE")
    private String partMfrType;

      @ApiModelProperty(value = "配件品牌(废弃)")
      @TableField("BRAND_UUID")
    private String brandUuid;

      @ApiModelProperty(value = "精友配件库UUID jy_part_result")
      @TableField("JPR_UUID")
    private String jprUuid;

      @ApiModelProperty(value = "精友标准零件编号")
      @TableField("JY_STANDARD_PART_CODE")
    private String jyStandardPartCode;

      @ApiModelProperty(value = "精友标准零件名称")
      @TableField("JY_STANDARD_PART_NAME")
    private String jyStandardPartName;

      @ApiModelProperty(value = "精友标准零件备注")
      @TableField("JY_STANDARD_PART_REMARK")
    private String jyStandardPartRemark;

      @ApiModelProperty(value = "包装规格")
      @TableField("PKG_SPEC")
    private String pkgSpec;

      @ApiModelProperty(value = "生产厂家")
      @TableField("MANUFACTURER")
    private String manufacturer;

      @ApiModelProperty(value = "是否启用Y/N")
      @TableField("USABLE_FLAG")
    private String usableFlag;

      @ApiModelProperty(value = "别名1")
      @TableField("SKU_ALIAS1")
    private String skuAlias1;

      @ApiModelProperty(value = "别名2")
      @TableField("SKU_ALIAS2")
    private String skuAlias2;

      @ApiModelProperty(value = "别名3")
      @TableField("SKU_ALIAS3")
    private String skuAlias3;

      @ApiModelProperty(value = "别名4")
      @TableField("SKU_ALIAS4")
    private String skuAlias4;

      @ApiModelProperty(value = "别名5")
      @TableField("SKU_ALIAS5")
    private String skuAlias5;

      @ApiModelProperty(value = "是否手工输入(Y/N)")
      @TableField("IS_MANUAL_INPUT")
    private String isManualInput;


}

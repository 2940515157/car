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
 * 套餐
 * </p>
 *
 * @author liujiaxi
 * @since 2021-06-03
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @ApiModel(value="AcctPackage对象", description="套餐")
public class AcctPackage implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "主键ID")
        @TableId(value = "AP_UUID", type = IdType.ASSIGN_UUID)
      private String apUuid;

      @ApiModelProperty(value = "套餐名称")
      @TableField("AP_NAME")
    private String apName;

      @ApiModelProperty(value = "套餐类")
      @TableField("AP_TYPE")
    private String apType;

      @ApiModelProperty(value = "套餐拼音")
      @TableField("PINYIN")
    private String pinyin;

      @ApiModelProperty(value = "套餐售价")
      @TableField("TOTAL_PRICE")
    private BigDecimal totalPrice;

      @ApiModelProperty(value = "有效期(开始)")
      @TableField("START_DATE")
    private Date startDate;

      @ApiModelProperty(value = "有效期(结束)")
      @TableField("END_DATE")
    private Date endDate;

      @ApiModelProperty(value = "状态")
      @TableField("STATUS")
    private String status;

      @ApiModelProperty(value = "发起类型")
      @TableField("ORIGION_TYPE")
    private String origionType;

      @ApiModelProperty(value = "创建人UUID")
      @TableField("CREATOR_UUID")
    private String creatorUuid;

      @ApiModelProperty(value = "核算人UUID")
      @TableField("CHECK_UUID")
    private String checkUuid;

      @ApiModelProperty(value = "设定佣金分成比率人UUID")
      @TableField("SET_CR_UUID")
    private String setCrUuid;

      @ApiModelProperty(value = "销售套餐提成比率")
      @TableField("SALE_RATE")
    private BigDecimal saleRate;

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

      @ApiModelProperty(value = "使用有效期（月）")
      @TableField("VALID_MONTH")
    private BigDecimal validMonth;

      @ApiModelProperty(value = "是否自动专属")
      @TableField("CAN_OWNER")
    private String canOwner;

      @ApiModelProperty(value = "是否属于组合产品,Y/N")
      @TableField("COMPOSITE_FLAG")
    private String compositeFlag;

      @ApiModelProperty(value = "业务分类 DICT_PKG_BIZ_TYPE")
      @TableField("BIZ_TYPE")
    private String bizType;

      @ApiModelProperty(value = "可返B币")
      @TableField("B_RETURN")
    private BigDecimal bReturn;

      @ApiModelProperty(value = "套餐模板UUID")
      @TableField("APM_UUID")
    private String apmUuid;

      @ApiModelProperty(value = "服务类型(美容、机修、油漆、综合)")
      @TableField("SERVICE_TYPE")
    private String serviceType;

      @ApiModelProperty(value = "门店UUID")
      @TableField("BR_UUID")
    private String brUuid;

      @ApiModelProperty(value = "折扣率")
      @TableField("DISCOUNT_RATE")
    private BigDecimal discountRate;

      @ApiModelProperty(value = "匹配里程大值")
      @TableField("MATCH_MILEAGE_MAX")
    private BigDecimal matchMileageMax;

      @ApiModelProperty(value = "匹配里程小值")
      @TableField("MATCH_MILEAGE_MIN")
    private BigDecimal matchMileageMin;

      @ApiModelProperty(value = "匹配车龄大值")
      @TableField("MATCH_CARAGE_MAX")
    private BigDecimal matchCarageMax;

      @ApiModelProperty(value = "匹配车龄小值")
      @TableField("MATCH_CARAGE_MIN")
    private BigDecimal matchCarageMin;

      @ApiModelProperty(value = "套餐原价(所有标准价格相加)")
      @TableField("STD_TOTAL_PRICE")
    private BigDecimal stdTotalPrice;


}

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
 * 总部统一服务目录
 * </p>
 *
 * @author liujiaxi
 * @since 2021-06-08
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @ApiModel(value="BaseServCatalog对象", description="总部统一服务目录")
public class BaseServCatalog implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "uuid")
        @TableId(value = "CATALOG_UUID", type = IdType.ASSIGN_UUID)
      private String catalogUuid;

      @ApiModelProperty(value = "服务类型")
      @TableField("SERV_TYPE_UUID")
    private String servTypeUuid;

      @ApiModelProperty(value = "门店UUID，为空表示为总部统一服务目录")
      @TableField("BR_UUID")
    private String brUuid;

      @ApiModelProperty(value = "总部统一服务目录UUID")
      @TableField("STD_CATALOG_UUID")
    private String stdCatalogUuid;

      @ApiModelProperty(value = "服务目录编号")
      @TableField("CATALOG_CODE")
    private String catalogCode;

      @ApiModelProperty(value = "服务目录名称")
      @TableField("CATALOG_NAME")
    private String catalogName;

      @ApiModelProperty(value = "服务目录拼音简称")
      @TableField("PINYIN")
    private String pinyin;

      @ApiModelProperty(value = "标准工时")
      @TableField("STD_MAN_HOUR")
    private BigDecimal stdManHour;

      @ApiModelProperty(value = "标准价格")
      @TableField("STD_PRICE")
    private BigDecimal stdPrice;

      @ApiModelProperty(value = "标准面数量")
      @TableField("STD_SIDE_NUM")
    private BigDecimal stdSideNum;

      @ApiModelProperty(value = "SA提成率？")
      @TableField("COMM_RATE_SA")
    private BigDecimal commRateSa;

      @ApiModelProperty(value = "技师提成率？")
      @TableField("COMM_RATE_WORKER")
    private BigDecimal commRateWorker;

      @ApiModelProperty(value = "服务顾问是否可改价格")
      @TableField("SA_CAN_EDIT_PRICE")
    private String saCanEditPrice;

      @ApiModelProperty(value = "是否不同车型价格一致")
      @TableField("IS_SAME_PRICE")
    private String isSamePrice;

      @ApiModelProperty(value = "门店是否可修改名称")
      @TableField("BR_CAN_EDIT_NAME")
    private String brCanEditName;

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

      @ApiModelProperty(value = "是否为快速结算项目")
      @TableField("IS_QUICK")
    private String isQuick;

      @ApiModelProperty(value = "是否常用 DICT_YESNO")
      @TableField("IS_COMMON")
    private String isCommon;

      @ApiModelProperty(value = "简称(创建的时候默认等于名称)")
      @TableField("CATALOG_NAME_SHORT")
    private String catalogNameShort;

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

      @ApiModelProperty(value = "统计分类 DICT_STAT_TYPE")
      @TableField("STAT_TYPE")
    private String statType;

      @ApiModelProperty(value = "统计方法 DICT_STAT_METHOD")
      @TableField("STAT_METHOD")
    private String statMethod;

      @ApiModelProperty(value = "排序")
      @TableField("SEQ_NO")
    private BigDecimal seqNo;

      @ApiModelProperty(value = "是否启用Y/N")
      @TableField("USABLE_FLAG")
    private String usableFlag;

      @ApiModelProperty(value = "停用操作人角色：总部、门店  DICT_MGN_ROLE_TYPE")
      @TableField("FORBID_ROLE")
    private String forbidRole;


}

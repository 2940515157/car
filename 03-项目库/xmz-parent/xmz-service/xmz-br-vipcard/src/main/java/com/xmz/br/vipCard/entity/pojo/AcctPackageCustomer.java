package com.xmz.br.vipCard.entity.pojo;

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
 * 客户已购买套餐
 * </p>
 *
 * @author zt
 * @since 2021-06-11
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @ApiModel(value="AcctPackageCustomer对象", description="客户已购买套餐")
public class AcctPackageCustomer implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "主键ID")
        @TableId(value = "APC_UUID", type = IdType.ASSIGN_UUID)
      private String apcUuid;

      @ApiModelProperty(value = "客户UUID")
      @TableField("CUSTOMER_UUID")
    private String customerUuid;

      @ApiModelProperty(value = "套餐名称")
      @TableField("APC_NAME")
    private String apcName;

      @ApiModelProperty(value = "套餐类型  数据字典：服务套餐、服务配件套餐")
      @TableField("APC_TYPE")
    private String apcType;

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

      @ApiModelProperty(value = "状态 数据字典：草稿 申请中 上架 下架")
      @TableField("STATUS")
    private String status;

      @ApiModelProperty(value = "发起类型 数据字典：门店 总部运营中心")
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

      @ApiModelProperty(value = "已购买套餐状态(未付款、已付款、已结案)DICT_PACKAGE_USED_STATUS")
      @TableField("APC_STATUS")
    private String apcStatus;

      @ApiModelProperty(value = "使用有效期（月）")
      @TableField("VALID_MONTH")
    private BigDecimal validMonth;

      @ApiModelProperty(value = "门店UUID")
      @TableField("BR_UUID")
    private String brUuid;

      @ApiModelProperty(value = "SA_UUID")
      @TableField("SA_UUID")
    private String saUuid;

      @ApiModelProperty(value = "使用截止时间")
      @TableField("USE_END_DATE")
    private Date useEndDate;

      @ApiModelProperty(value = "实收金额")
      @TableField("REL_PRICE")
    private BigDecimal relPrice;

      @ApiModelProperty(value = "是否使用完(Y/N）")
      @TableField("USED_FLAG")
    private String usedFlag;

      @ApiModelProperty(value = "套餐ID")
      @TableField("ACCT_PACKAGE_UUID")
    private String acctPackageUuid;

      @ApiModelProperty(value = "客户已购买组合产品的UUID")
      @TableField("ACPC_UUID")
    private String acpcUuid;

      @ApiModelProperty(value = "使用开始时间")
      @TableField("USE_START_DATE")
    private Date useStartDate;

      @ApiModelProperty(value = "是否导入Y/N")
      @TableField("IMP_FLAG")
    private String impFlag;

      @ApiModelProperty(value = "结案人uuid")
      @TableField("CLOSE_SA_UUID")
    private String closeSaUuid;

      @ApiModelProperty(value = "结案人(SA)")
      @TableField("CLOSE_SA_NAME")
    private String closeSaName;

      @ApiModelProperty(value = "结案时间")
      @TableField("CLOSE_DATE")
    private Date closeDate;

      @ApiModelProperty(value = "可返B币")
      @TableField("B_RETURN")
    private BigDecimal bReturn;

      @ApiModelProperty(value = "资金是否归结总部(Y/N)")
      @TableField("IS_FUND_HEADQUARTER")
    private String isFundHeadquarter;


}

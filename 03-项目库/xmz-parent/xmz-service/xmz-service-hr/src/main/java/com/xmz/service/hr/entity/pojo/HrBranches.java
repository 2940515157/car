package com.xmz.service.hr.entity.pojo;

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
 * 门店信息
 * </p>
 *
 * @author hwb
 * @since 2021-06-04
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @ApiModel(value="HrBranches对象", description="门店信息")
public class HrBranches implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "uuid")
        @TableId(value = "BR_UUID", type = IdType.ASSIGN_UUID)
      private String brUuid;

      @ApiModelProperty(value = "门店编号")
      @TableField("BR_CODE")
    private String brCode;

      @ApiModelProperty(value = "门店名称")
      @TableField("BR_NAME")
    private String brName;

      @ApiModelProperty(value = "简称")
      @TableField("SHORT_NAME")
    private String shortName;

      @ApiModelProperty(value = "门店类型(字典：加盟，自营)")
      @TableField("BR_TYPE")
    private String brType;

      @ApiModelProperty(value = "门店车牌前缀")
      @TableField("PLATE_PREFIX")
    private String platePrefix;

      @ApiModelProperty(value = "区域")
      @TableField("AREA_UUID")
    private String areaUuid;

      @ApiModelProperty(value = "大区")
      @TableField("ORG_UUID")
    private String orgUuid;

      @ApiModelProperty(value = "地址")
      @TableField("ADDRESS")
    private String address;

      @ApiModelProperty(value = "Email")
      @TableField("EMAIL")
    private String email;

      @ApiModelProperty(value = "传真")
      @TableField("FAX")
    private String fax;

      @ApiModelProperty(value = "门店电话")
      @TableField("BR_PHONE")
    private String brPhone;

      @ApiModelProperty(value = "店长")
      @TableField("BR_MANAGER")
    private String brManager;

      @ApiModelProperty(value = "店长电话")
      @TableField("MANAGER_PHONE")
    private String managerPhone;

      @ApiModelProperty(value = "开户银行")
      @TableField("BANK")
    private String bank;

      @ApiModelProperty(value = "银行账号")
      @TableField("BANK_ACCOUNT")
    private String bankAccount;

      @ApiModelProperty(value = "户名")
      @TableField("ACCOUNT_NAME")
    private String accountName;

      @ApiModelProperty(value = "税号")
      @TableField("TARIFF")
    private String tariff;

      @ApiModelProperty(value = "工位")
      @TableField("STATION")
    private BigDecimal station;

      @ApiModelProperty(value = "加价率")
      @TableField("PRICE_MARKUP_RATE")
    private BigDecimal priceMarkupRate;

      @ApiModelProperty(value = "法人代表")
      @TableField("LEGAL_PERSON")
    private String legalPerson;

      @ApiModelProperty(value = "法人电话")
      @TableField("LEGAL_PHONE")
    private String legalPhone;

      @ApiModelProperty(value = "B币额度")
      @TableField("LINES")
    private BigDecimal lines;

      @ApiModelProperty(value = "纬度")
      @TableField("LATITUDE")
    private BigDecimal latitude;

      @ApiModelProperty(value = "经度")
      @TableField("LONGITUDE")
    private BigDecimal longitude;

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

      @ApiModelProperty(value = "省份")
      @TableField("PROV_UUID")
    private String provUuid;

      @ApiModelProperty(value = "城市")
      @TableField("CITY_UUID")
    private String cityUuid;

      @ApiModelProperty(value = "信用额度")
      @TableField("CREDIT_LINES")
    private BigDecimal creditLines;

      @ApiModelProperty(value = "市场费用比率")
      @TableField("MARKET_COST_RATIO")
    private BigDecimal marketCostRatio;

      @ApiModelProperty(value = "门店成熟度")
      @TableField("BR_MATURITY")
    private BigDecimal brMaturity;

      @ApiModelProperty(value = "拼音")
      @TableField("PINYIN")
    private String pinyin;

      @ApiModelProperty(value = "门店自己审核保单标示")
      @TableField("INSUR_CHECK_FLAG")
    private String insurCheckFlag;

      @ApiModelProperty(value = "打印单据-总部标识")
      @TableField("PRINT_BASE_TITLE")
    private String printBaseTitle;

      @ApiModelProperty(value = "打印单据-总部统一服务热线")
      @TableField("PRINT_BASE_TEL")
    private String printBaseTel;

      @ApiModelProperty(value = "打印单据-广告")
      @TableField("PRINT_BASE_ADV")
    private String printBaseAdv;

      @ApiModelProperty(value = "一日贷信用额度")
      @TableField("CREDIT_QUOTA")
    private BigDecimal creditQuota;

      @ApiModelProperty(value = "一日贷剩余额度")
      @TableField("REMAIN_CREDIT_QUOTA")
    private BigDecimal remainCreditQuota;

      @ApiModelProperty(value = "合同号")
      @TableField("CONTRACT_NO")
    private String contractNo;

      @ApiModelProperty(value = "地址2")
      @TableField("ADDRESS2")
    private String address2;

      @ApiModelProperty(value = "地址3")
      @TableField("ADDRESS3")
    private String address3;

      @ApiModelProperty(value = "地址4")
      @TableField("ADDRESS4")
    private String address4;

      @ApiModelProperty(value = "地址5")
      @TableField("ADDRESS5")
    private String address5;

      @ApiModelProperty(value = "是否参与游戏排名 Y/N")
      @TableField("GAME_FLAG")
    private String gameFlag;

      @ApiModelProperty(value = "套餐卡信用额度")
      @TableField("PKG_CREDIT_QUOTA")
    private BigDecimal pkgCreditQuota;

      @ApiModelProperty(value = "登录后主页跳转类型(1.门店 2.商城首页)")
      @TableField("AFTER_LOGIN_REDIRECT_TYPE")
    private String afterLoginRedirectType;

      @ApiModelProperty(value = "是否麻雀店(支持手工输入配件)Y/N")
      @TableField("SPARROW_FLAG")
    private String sparrowFlag;

      @ApiModelProperty(value = "系统是否开通")
      @TableField("IS_OPEN")
    private String isOpen;

      @ApiModelProperty(value = "开通日期")
      @TableField("OPEN_DATE")
    private Date openDate;

      @ApiModelProperty(value = "是否上线")
      @TableField("IS_ONLINE")
    private String isOnline;

      @ApiModelProperty(value = "上线日期")
      @TableField("ONLINE_DATE")
    private Date onlineDate;

      @ApiModelProperty(value = "总部套餐是否上线")
      @TableField("PKG_ONLINE")
    private String pkgOnline;

      @ApiModelProperty(value = "套餐首次成交日期")
      @TableField("PKG_DEAL_DATE")
    private Date pkgDealDate;

      @ApiModelProperty(value = "配件商城是否成交")
      @TableField("EB_DEAL")
    private String ebDeal;

      @ApiModelProperty(value = "配件首次成交日期")
      @TableField("EB_DEAL_DATE")
    private Date ebDealDate;

      @ApiModelProperty(value = "是否发送短信")
      @TableField("SEND_MSG")
    private String sendMsg;

      @ApiModelProperty(value = "会员卡信用额度留底比例")
      @TableField("PKG_CREDIT_RATE")
    private BigDecimal pkgCreditRate;

      @ApiModelProperty(value = "设置门店资金是否归结总部(Y/N)")
      @TableField("IS_FUND_HEADQUARTER")
    private String isFundHeadquarter;


}

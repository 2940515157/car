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
 * 客户信息
 * </p>
 *
 * @author zt
 * @since 2021-06-04
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @ApiModel(value="CustCustInfo对象", description="客户信息")
public class CustCustInfo implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "uuid")
        @TableId(value = "CUST_UUID", type = IdType.ASSIGN_UUID)
      private String custUuid;

      @ApiModelProperty(value = "手机")
      @TableField("CUST_PHONE")
    private String custPhone;

      @ApiModelProperty(value = "车主编号")
      @TableField("CUST_NO")
    private String custNo;

      @ApiModelProperty(value = "客户名称")
      @TableField("CUST_NAME")
    private String custName;

      @ApiModelProperty(value = "车主拼音(全拼)")
      @TableField("CUST_NAME_PY")
    private String custNamePy;

      @ApiModelProperty(value = "性别")
      @TableField("GENDER")
    private String gender;

      @ApiModelProperty(value = "客户等级(随机、专属)")
      @TableField("CUST_LEVEL")
    private String custLevel;

      @ApiModelProperty(value = "座机")
      @TableField("CUST_TEL")
    private String custTel;

      @ApiModelProperty(value = "生日")
      @TableField("BIRTHDAY")
    private Date birthday;

      @ApiModelProperty(value = "Email")
      @TableField("EMAIL")
    private String email;

      @ApiModelProperty(value = "证件号码")
      @TableField("CERTIFICATE")
    private String certificate;

      @ApiModelProperty(value = "邮政编码")
      @TableField("POSTAL_CODE")
    private String postalCode;

      @ApiModelProperty(value = "地址")
      @TableField("ADDRESS")
    private String address;

      @ApiModelProperty(value = "省份")
      @TableField("PROVINCE")
    private String province;

      @ApiModelProperty(value = "城市")
      @TableField("CITY")
    private String city;

      @ApiModelProperty(value = "地区")
      @TableField("CITY_AREA")
    private String cityArea;

      @ApiModelProperty(value = "所在行业")
      @TableField("CUST_TRADE")
    private String custTrade;

      @ApiModelProperty(value = "月收入")
      @TableField("MONTH_INCOME")
    private String monthIncome;

      @ApiModelProperty(value = "婚姻状况")
      @TableField("MARRIAGE_STATUS")
    private String marriageStatus;

      @ApiModelProperty(value = "学历")
      @TableField("EDUCATION")
    private String education;

      @ApiModelProperty(value = "爱好")
      @TableField("INTEREST")
    private String interest;

      @ApiModelProperty(value = "微信号")
      @TableField("WEIXIN")
    private String weixin;

      @ApiModelProperty(value = "qq号")
      @TableField("QQ")
    private String qq;

      @ApiModelProperty(value = "上次来店时间")
      @TableField("LAST_TIME")
    private Date lastTime;

      @ApiModelProperty(value = "上次服务SA")
      @TableField("LAST_SA_UUID")
    private String lastSaUuid;

      @ApiModelProperty(value = "上次服务的门店")
      @TableField("LAST_BR_UUID")
    private String lastBrUuid;

      @ApiModelProperty(value = "专属SA")
      @TableField("OWED_SA_UUID")
    private String owedSaUuid;

      @ApiModelProperty(value = "专属门店")
      @TableField("OWED_BR_UUID")
    private String owedBrUuid;

      @ApiModelProperty(value = "专属化时间")
      @TableField("OWED_TIME")
    private Date owedTime;

      @ApiModelProperty(value = "首次服务门店")
      @TableField("FIRST_SERV_BR_UUID")
    private String firstServBrUuid;

      @ApiModelProperty(value = "首次服务SA")
      @TableField("FIRST_SERV_SA_UUID")
    private String firstServSaUuid;

      @ApiModelProperty(value = "首次专属化门店")
      @TableField("FIRST_OWED_BR_UUID")
    private String firstOwedBrUuid;

      @ApiModelProperty(value = "首次专属化SA")
      @TableField("FIRST_OWED_SA_UUID")
    private String firstOwedSaUuid;

      @ApiModelProperty(value = "资料完整度")
      @TableField("INTEGRITY")
    private BigDecimal integrity;

      @ApiModelProperty(value = "备注")
      @TableField("REMARK")
    private String remark;

      @ApiModelProperty(value = "客户状态（活跃客户、正常客户、流失客户）")
      @TableField("STATUS")
    private String status;

      @ApiModelProperty(value = "客户来源(直接到店、转介绍、总部流量)")
      @TableField("CUST_SRC")
    private String custSrc;

      @ApiModelProperty(value = "客户价值")
      @TableField("CUST_VALUE")
    private BigDecimal custValue;

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

      @ApiModelProperty(value = "录入门店")
      @TableField("RECORD_BR_UUID")
    private String recordBrUuid;

      @ApiModelProperty(value = "证件类型")
      @TableField("CARD_TYEP")
    private String cardTyep;

      @ApiModelProperty(value = "初次领证时间")
      @TableField("INIT_LICENS_TIME")
    private Date initLicensTime;

      @ApiModelProperty(value = "转介绍人ID")
      @TableField("REFERRAL_UUID")
    private String referralUuid;

      @ApiModelProperty(value = "驾驶证到期时间")
      @TableField("END_LICENS_TIME")
    private Date endLicensTime;

      @ApiModelProperty(value = "附件UUID")
      @TableField("ATTACHMENT_UUID")
    private String attachmentUuid;

      @ApiModelProperty(value = "是否导入Y/N")
      @TableField("IMP_FLAG")
    private String impFlag;

      @ApiModelProperty(value = "介绍人")
      @TableField("REFERRAL_USER")
    private String referralUser;


}

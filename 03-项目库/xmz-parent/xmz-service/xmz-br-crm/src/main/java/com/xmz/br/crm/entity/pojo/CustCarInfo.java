package com.xmz.br.crm.entity.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 车辆信息
 * </p>
 *
 * @author weikang
 * @since 2021-06-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "CustCarInfo对象", description = "车辆信息")
public class CustCarInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "uuid")
    @TableId(value = "CAR_UUID", type = IdType.ASSIGN_UUID)
    private String carUuid;

    @ApiModelProperty(value = "车主客户UUID")
    @TableField("CUST_UUID")
    private String custUuid;

    @ApiModelProperty(value = "车牌号")
    @TableField("LICENSE_PLATE")
    private String licensePlate;

    @ApiModelProperty(value = "VIN码")
    @TableField("VIN")
    private String vin;

    @ApiModelProperty(value = "车型ID（对应车型表）")
    @TableField("CAR_TYPE_UUID")
    private String carTypeUuid;

    @ApiModelProperty(value = "发动机号")
    @TableField("ENGINE_NO")
    private String engineNo;

    @ApiModelProperty(value = "颜色")
    @TableField("COLOR")
    private String color;

    @ApiModelProperty(value = "购车年份")
    @TableField("BUY_YEAR")
    private BigDecimal buyYear;

    @ApiModelProperty(value = "购车月份")
    @TableField("BUY_MONTH")
    private BigDecimal buyMonth;

    @ApiModelProperty(value = "车辆性质(公车、私车)")
    @TableField("BELONG_TYPE")
    private String belongType;

    @ApiModelProperty(value = "行驶里程KM")
    @TableField("MILES")
    private BigDecimal miles;

    @ApiModelProperty(value = "里程数更新时间")
    @TableField("MILES_DATE")
    private Date milesDate;

    @ApiModelProperty(value = "月均里程KM")
    @TableField("MILES_MONTH")
    private BigDecimal milesMonth;

    @ApiModelProperty(value = "车辆档次级别")
    @TableField("VEHICLE_LEVEL")
    private String vehicleLevel;

    @ApiModelProperty(value = "商业保险到期日期")
    @TableField("INS_BIZ_END_DATE")
    private Date insBizEndDate;

    @ApiModelProperty(value = "交强险到期日期")
    @TableField("INS_FORCE_END_DATE")
    private Date insForceEndDate;

    @ApiModelProperty(value = "上次来店时间")
    @TableField("LAST_TIME")
    private Date lastTime;

    @ApiModelProperty(value = "上次服务的门店")
    @TableField("LAST_SERV_BR_UUID")
    private String lastServBrUuid;

    @ApiModelProperty(value = "上次服务SA")
    @TableField("LAST_SA_UUID")
    private String lastSaUuid;

    @ApiModelProperty(value = "首次来店时间")
    @TableField("FIRST_TIME")
    private Date firstTime;

    @ApiModelProperty(value = "首次服务的门店")
    @TableField("FIRST_SERV_BR_UUID")
    private String firstServBrUuid;

    @ApiModelProperty(value = "首次服务SA")
    @TableField("FIRST_SA_UUID")
    private String firstSaUuid;

    @ApiModelProperty(value = "资料完整度")
    @TableField("INTEGRITY")
    private BigDecimal integrity;

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

    @ApiModelProperty(value = "年检到期时间")
    @TableField("INSPECTION_END_DATE")
    private Date inspectionEndDate;

    @ApiModelProperty(value = "年检时间")
    @TableField("INSPECTION_DATE")
    private Date inspectionDate;

    @ApiModelProperty(value = "vin码是否验证通过")
    @TableField("IS_VIN_CHECK")
    private String isVinCheck;

    @ApiModelProperty(value = "环保到期日期")
    @TableField("ENV_PROT_END_DATE")
    private Date envProtEndDate;

    @ApiModelProperty(value = "商业险保险公司")
    @TableField("INS_BIZ_COMPANY")
    private String insBizCompany;

    @ApiModelProperty(value = "交强险保险公司")
    @TableField("INS_FORCE_COMPANY")
    private String insForceCompany;

    @ApiModelProperty(value = "头像附件UUID")
    @TableField("PIC_ATTACHMENT_UUID")
    private String picAttachmentUuid;

    @ApiModelProperty(value = "车型备注信息（当车型为通用车型时，此字段必填）")
    @TableField("CAR_TYPE_REMARK")
    private String carTypeRemark;

    @ApiModelProperty(value = "车架号附件UUID")
    @TableField("VIN_ATTACHMENT_UUID")
    private String vinAttachmentUuid;

    @ApiModelProperty(value = "车牌附件UUID")
    @TableField("LP_ATTACHMENT_UUID")
    private String lpAttachmentUuid;

    @ApiModelProperty(value = "是否导入Y/N")
    @TableField("IMP_FLAG")
    private String impFlag;

    @ApiModelProperty(value = "OBD终端类型")
    @TableField("OBD_SENDTYPE")
    private String obdSendtype;

    @ApiModelProperty(value = "OBD安装初始里程")
    @TableField("OBD_INIT_MILES")
    private BigDecimal obdInitMiles;

    @ApiModelProperty(value = "OBD里程(总里程)")
    @TableField("OBD_MILES")
    private BigDecimal obdMiles;

    @ApiModelProperty(value = "是否绑定微信")
    @TableField("IS_BINDING_WEIXIN")
    private String isBindingWeixin;


}

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
 * 接车单
 * </p>
 *
 * @author hwb
 * @since 2021-06-04
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @ApiModel(value="ServReceiveCar对象", description="接车单")
public class ServReceiveCar implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "主键")
        @TableId(value = "REV_UUID", type = IdType.ASSIGN_UUID)
      private String revUuid;

      @ApiModelProperty(value = "接车单据号")
      @TableField("REV_NO")
    private String revNo;

      @ApiModelProperty(value = "车主")
      @TableField("CUST_NAME")
    private String custName;

      @ApiModelProperty(value = "车主电话")
      @TableField("CUST_PHONE")
    private String custPhone;

      @ApiModelProperty(value = "车牌")
      @TableField("LICENSE_PLATE")
    private String licensePlate;

      @ApiModelProperty(value = "公里数")
      @TableField("CAR_KM")
    private BigDecimal carKm;

      @ApiModelProperty(value = "出厂公里数")
      @TableField("CAR_LEAVE_KM")
    private BigDecimal carLeaveKm;

      @ApiModelProperty(value = "油表数")
      @TableField("OIL_NUMBER")
    private String oilNumber;

      @ApiModelProperty(value = "车辆信息uuid")
      @TableField("CAR_UUID")
    private String carUuid;

      @ApiModelProperty(value = "车主信息uuid")
      @TableField("CUST_UUID")
    private String custUuid;

      @ApiModelProperty(value = "接车人uuid")
      @TableField("REV_SA_UUID")
    private String revSaUuid;

      @ApiModelProperty(value = "门店信息")
      @TableField("BR_UUID")
    private String brUuid;

      @ApiModelProperty(value = "接车人(SA)")
      @TableField("REV_SA_NAME")
    private String revSaName;

      @ApiModelProperty(value = "专属SA")
      @TableField("OWNED_SA_UUID")
    private String ownedSaUuid;

      @ApiModelProperty(value = "兼职云SA的uuid")
      @TableField("PART_TIME_SA")
    private String partTimeSa;

      @ApiModelProperty(value = "是否预约(Y:是；N:否)")
      @TableField("IS_BOOKING")
    private String isBooking;

      @ApiModelProperty(value = "如果预约此处不为空")
      @TableField("BOOK_UUID")
    private String bookUuid;

      @ApiModelProperty(value = "是否询价(Y:是；N:否)")
      @TableField("IS_INQUIRY")
    private String isInquiry;

      @ApiModelProperty(value = "状态 初始、已确认、已派工、已完工、已收款、已内部结算")
      @TableField("REV_STATUS")
    private String revStatus;

      @ApiModelProperty(value = "如果询价此处不为空")
      @TableField("INQU_UUID")
    private String inquUuid;

      @ApiModelProperty(value = "上次维修时间")
      @TableField("LAST_ARRIVE_DATE")
    private Date lastArriveDate;

      @ApiModelProperty(value = "上次服务门店")
      @TableField("LAST_SERV_BR_UUID")
    private String lastServBrUuid;

      @ApiModelProperty(value = "上次服务SA")
      @TableField("LAST_SA_UUID")
    private String lastSaUuid;

      @ApiModelProperty(value = "到店时间")
      @TableField("ARRIVE_DATE")
    private Date arriveDate;

      @ApiModelProperty(value = "离店时间")
      @TableField("LEAVE_DATE")
    private Date leaveDate;

      @ApiModelProperty(value = "计划完工时间")
      @TableField("PLAN_DATE")
    private Date planDate;

      @ApiModelProperty(value = "实际完工时间")
      @TableField("FINISH_DATE")
    private Date finishDate;

      @ApiModelProperty(value = "是否快速结算(Y:是；N:否)")
      @TableField("IS_QUICK_BILL")
    private String isQuickBill;

      @ApiModelProperty(value = "导致返工的接车单uuid")
      @TableField("LAST_SERV_UUID")
    private String lastServUuid;

      @ApiModelProperty(value = "是否返工(Y:是；N:否)")
      @TableField("IS_REWORK")
    private String isRework;

      @ApiModelProperty(value = "驾驶人联系电话")
      @TableField("DRIVER_PHONE")
    private String driverPhone;

      @ApiModelProperty(value = "驾驶人")
      @TableField("DRIVER")
    private String driver;

      @ApiModelProperty(value = "客户来源(字典：户外广告，报纸，电台，网络)字典：DICT_CUST_FROM")
      @TableField("CUST_FROM")
    private String custFrom;

      @ApiModelProperty(value = "客户等级(随机,准专属,专属)字典:DICT_CUST_TYPE")
      @TableField("CUST_LEVEL")
    private String custLevel;

      @ApiModelProperty(value = "车辆等级(需要根据车型等级+车辆的年份+公里数)")
      @TableField("CAR_LEVEL")
    private String carLevel;

      @ApiModelProperty(value = "保险公司(字典:DICT_INSURANCE_COMPANY)")
      @TableField("INSURANCE_COMPANY")
    private String insuranceCompany;

      @ApiModelProperty(value = "车辆备注信息")
      @TableField("REMARK")
    private String remark;

      @ApiModelProperty(value = "是否点检")
      @TableField("IS_CHECKED")
    private String isChecked;

      @ApiModelProperty(value = "零配件状态")
      @TableField("PARTS_STATUS")
    private String partsStatus;

      @ApiModelProperty(value = "打印状态")
      @TableField("PRINT_STATUS")
    private String printStatus;

      @ApiModelProperty(value = "完工检查情况")
      @TableField("FINISH_REMARK")
    private String finishRemark;

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

      @ApiModelProperty(value = "是否回访")
      @TableField("IS_VISIT")
    private String isVisit;

      @ApiModelProperty(value = "结算人uuid")
      @TableField("STL_SA_UUID")
    private String stlSaUuid;

      @ApiModelProperty(value = "结算人(SA)")
      @TableField("STL_SA_NAME")
    private String stlSaName;

      @ApiModelProperty(value = "结算时间")
      @TableField("STL_DATE")
    private Date stlDate;

      @ApiModelProperty(value = "收款时间")
      @TableField("REV_DATE")
    private Date revDate;

      @ApiModelProperty(value = "结案人uuid")
      @TableField("CLOSE_SA_UUID")
    private String closeSaUuid;

      @ApiModelProperty(value = "结案人(SA)")
      @TableField("CLOSE_SA_NAME")
    private String closeSaName;

      @ApiModelProperty(value = "结案时间")
      @TableField("CLOSE_DATE")
    private Date closeDate;

      @ApiModelProperty(value = "增值金额")
      @TableField("TTL_AMT_VAT")
    private BigDecimal ttlAmtVat;

      @ApiModelProperty(value = "总金额")
      @TableField("TTL_AMT")
    private BigDecimal ttlAmt;

      @ApiModelProperty(value = "收款人uuid")
      @TableField("REV_PAY_SA_UUID")
    private String revPaySaUuid;

      @ApiModelProperty(value = "收款人(SA)")
      @TableField("REV_PAY_SA_NAME")
    private String revPaySaName;

      @ApiModelProperty(value = "接车的客户端类别，pc/pad")
      @TableField("CLIENT_TYPE")
    private String clientType;

      @ApiModelProperty(value = "业务大类（美容、机修、油漆、钣金）")
      @TableField("TOP_TYPE")
    private String topType;

      @ApiModelProperty(value = "采购状态(DICT_REV_PUR_STATUS 待采购，已采购，无需采购)")
      @TableField("PUR_STATUS")
    private String purStatus;

      @ApiModelProperty(value = "统计分类名称，多个分类用逗号分隔,用于界面显示")
      @TableField("STAT_TYPE_NAME")
    private String statTypeName;

      @ApiModelProperty(value = "是否需要点检(Y/N)")
      @TableField("NEED_CHECK")
    private String needCheck;

      @ApiModelProperty(value = "是否无纸化结算(Y/N)")
      @TableField("IS_WEIXIN")
    private String isWeixin;

      @ApiModelProperty(value = "C端接车单是否确认")
      @TableField("IS_CONFIRMED_WEIXIN")
    private String isConfirmedWeixin;

      @ApiModelProperty(value = "点检包")
      @TableField("SCP_UUID")
    private String scpUuid;


}

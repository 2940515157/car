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
 * 回访记录
 * </p>
 *
 * @author hwb
 * @since 2021-06-04
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @ApiModel(value="ServVisitRecord对象", description="回访记录")
public class ServVisitRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "名称")
    @TableField("AREA_NAME")
    private String areaName;

    @ApiModelProperty(value = "名称")
    @TableField("ORG_NAME")
    private String orgName;

    @ApiModelProperty(value = "上次维修时间")
    @TableField("LAST_ARRIVE_DATE")
    private Date lastArriveDate;

    @ApiModelProperty(value = "总金额")
    @TableField("TTL_AMT")
    private BigDecimal ttlAmt;

    @ApiModelProperty(value = "车牌")
    @TableField("LICENSE_PLATE")
    private String licensePlate;

        @ApiModelProperty(value = "门店名称")
             @TableField("BR_NAME")
        private String brName;

        @ApiModelProperty(value = "门店编号")
            @TableField("BR_CODE")
        private String brCode;

      @ApiModelProperty(value = "主键")
        @TableId(value = "VISIT_UUID", type = IdType.ASSIGN_UUID)
      private String visitUuid;

      @ApiModelProperty(value = "回访单据号")
      @TableField("VISIT_NO")
    private String visitNo;

      @ApiModelProperty(value = "SA的uuid")
      @TableField("SA_UUID")
    private String saUuid;

      @ApiModelProperty(value = "回访客户uuid")
      @TableField("CUST_UUID")
    private String custUuid;

      @ApiModelProperty(value = "客户姓名")
      @TableField("CUST_NAME")
    private String custName;

      @ApiModelProperty(value = "客户电话")
      @TableField("CUST_PHONE")
    private String custPhone;

      @ApiModelProperty(value = "接车单uuid")
      @TableField("REV_UUID")
    private String revUuid;

      @ApiModelProperty(value = "接车单号")
      @TableField("REV_NO")
    private String revNo;

      @ApiModelProperty(value = "预约单uuid")
      @TableField("BOOK_UUID")
    private String bookUuid;

      @ApiModelProperty(value = "预约单号")
      @TableField("SBL_NO")
    private String sblNo;

      @ApiModelProperty(value = "回访类型(字典：电话，短信，IM，信件，上门拜访)字典：DICT_VISIT_TYPE")
      @TableField("VISIT_TYPE")
    private String visitType;

      @ApiModelProperty(value = "回访途径(字典：SA，外呼)")
      @TableField("VISIT_ROLE")
    private String visitRole;

      @ApiModelProperty(value = "回访情况(字典：效果很好,效果一般,无效果)字典：DICT_VISIT_RESULT")
      @TableField("VISIT_STATUS")
    private String visitStatus;

      @ApiModelProperty(value = "门店维修质量(字典：DICT_VISIT_SATIS)")
      @TableField("SERVICE_STATISF")
    private String serviceStatisf;

      @ApiModelProperty(value = "门店环境(字典：DICT_VISIT_SATIS)")
      @TableField("BR_STATISF")
    private String brStatisf;

      @ApiModelProperty(value = "门店交车的及时性(字典：DICT_VISIT_SATIS)")
      @TableField("WORKER_STATISF")
    private String workerStatisf;

      @ApiModelProperty(value = "服务人员的态度和专业性(字典：DICT_VISIT_SATIS)")
      @TableField("SA_STATISF")
    private String saStatisf;

      @ApiModelProperty(value = "联系是否成功(Y：是；N：否)")
      @TableField("IS_VISIT_SUCC")
    private String isVisitSucc;

      @ApiModelProperty(value = "联系时间")
      @TableField("VISIT_DATE")
    private Date visitDate;

      @ApiModelProperty(value = "反馈意见")
      @TableField("FEEDBACK")
    private String feedback;

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

      @ApiModelProperty(value = "维修位置")
      @TableField("MAINT_POSITION")
    private String maintPosition;

      @ApiModelProperty(value = "对门店维修质量不满意的原因")
      @TableField("DISSATISFIED_DESC_SERV")
    private String dissatisfiedDescServ;

      @ApiModelProperty(value = "对门店交车不满意的原因")
      @TableField("DISSATISFIED_DESC_WORK")
    private String dissatisfiedDescWork;

      @ApiModelProperty(value = "对门店环境不满意的原因")
      @TableField("DISSATISFIED_DESC_ENVT")
    private String dissatisfiedDescEnvt;

      @ApiModelProperty(value = "对服务人员的态度和专业专业性不满意的原因")
      @TableField("DISSATISFIED_DESC_SA")
    private String dissatisfiedDescSa;

      @ApiModelProperty(value = "获知小拇指门店的途径(字典：DICT_CUST_FROM)")
      @TableField("CUST_FROM")
    private String custFrom;

      @ApiModelProperty(value = "回访门店")
      @TableField("BR_UUID")
    private String brUuid;

      @ApiModelProperty(value = "是否已回访")
      @TableField("IS_VISITED")
    private String isVisited;


}

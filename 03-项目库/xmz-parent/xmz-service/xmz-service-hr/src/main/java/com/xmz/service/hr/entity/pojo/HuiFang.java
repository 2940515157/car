package com.xmz.service.hr.entity.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class HuiFang {

    @ApiModelProperty(value = "主键")
    @TableId(value = "VISIT_UUID", type = IdType.ASSIGN_UUID)
    private String visitUuid;

    @ApiModelProperty(value = "门店编号")
    @TableField("BR_CODE")
    private String brCode;

    @ApiModelProperty(value = "门店名称")
    @TableField("BR_NAME")
    private String brName;

    @ApiModelProperty(value = "车主")
    @TableField("CUST_NAME")
    private String custName;

    @ApiModelProperty(value = "车主电话")
    @TableField("CUST_PHONE")
    private String custPhone;

    @ApiModelProperty(value = "车牌")
    @TableField("LICENSE_PLATE")
    private String licensePlate;

    @ApiModelProperty(value = "维修位置")
    @TableField("MAINT_POSITION")
    private String maintPosition;

    @ApiModelProperty(value = "总金额")
    @TableField("TTL_AMT")
    private BigDecimal ttlAmt;

    @ApiModelProperty(value = "上次维修时间")
    @TableField("LAST_ARRIVE_DATE")
    private Date lastArriveDate;

    @ApiModelProperty(value = "接车单据号")
    @TableField("REV_NO")
    private String revNo;

    @ApiModelProperty(value = "名称")
    @TableField("ORG_NAME")
    private String orgName;

    @ApiModelProperty(value = "名称")
    @TableField("AREA_NAME")
    private String areaName;
}

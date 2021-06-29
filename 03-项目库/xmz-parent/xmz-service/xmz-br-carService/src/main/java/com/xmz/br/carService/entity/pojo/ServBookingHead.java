package com.xmz.br.carService.entity.pojo;

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
 * 预约单
 * </p>
 *
 * @author 罗俊
 * @since 2021-06-17
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @ApiModel(value="ServBookingHead对象", description="预约单")
public class ServBookingHead implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "主键ID")
        @TableId(value = "SB_UUID", type = IdType.ASSIGN_UUID)
      private String sbUuid;

      @ApiModelProperty(value = "手机")
      @TableField("CUST_PHONE")
    private String custPhone;

      @ApiModelProperty(value = "预约单号")
      @TableField("SBL_NO")
    private String sblNo;

      @ApiModelProperty(value = "所属门店ID")
      @TableField("BR_UUID")
    private String brUuid;

      @ApiModelProperty(value = "客户UUID")
      @TableField("CUST_UUID")
    private String custUuid;

      @ApiModelProperty(value = "车辆uuid")
      @TableField("CAR_UUID")
    private String carUuid;

      @ApiModelProperty(value = "SA UUID")
      @TableField("SA_UUID")
    private String saUuid;

      @ApiModelProperty(value = "车牌")
      @TableField("LICENSE_PLATE")
    private String licensePlate;

      @ApiModelProperty(value = "预约时间")
      @TableField("BL_DATE")
    private Date blDate;

      @ApiModelProperty(value = "预约类型(客户预约、SA预约、门店现场预约)")
      @TableField("SBL_TYPE")
    private String sblType;

      @ApiModelProperty(value = "备注")
      @TableField("REMARK")
    private String remark;

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

      @ApiModelProperty(value = "预约单状态")
      @TableField("STATUS")
    private String status;


}

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
 * 门店-SA-客户-车辆明细
 * </p>
 *
 * @author weikang
 * @since 2021-06-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "CustCustSasItem对象", description = "门店-SA-客户-车辆明细")
public class CustCustSasItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "CCSI_UUID", type = IdType.ASSIGN_UUID)
    private String ccsiUuid;

    @ApiModelProperty(value = "客户ID")
    @TableField("CUST_UUID")
    private String custUuid;

    @ApiModelProperty(value = "SA")
    @TableField("SA_UUID")
    private String saUuid;

    @ApiModelProperty(value = "门店")
    @TableField("BR_UUID")
    private String brUuid;

    @ApiModelProperty(value = "录入时间")
    @TableField("FIRST_TIME")
    private Date firstTime;

    @ApiModelProperty(value = "最近一次录入时间")
    @TableField("LAST_TIME")
    private Date lastTime;

    @TableField("EXT1")
    private String ext1;

    @TableField("EXT2")
    private String ext2;

    @TableField("EXT3")
    private String ext3;

    @TableField("EXT4")
    private String ext4;

    @TableField("EXT5")
    private String ext5;

    @TableField("IS_ACTIVE")
    private String isActive;

    @TableField("CREATED_DATE")
    private Date createdDate;

    @TableField("UPDATED_DATE")
    private Date updatedDate;

    @TableField("CREATED_BY")
    private String createdBy;

    @TableField("UPDATED_BY")
    private String updatedBy;

    @TableField("VERSION")
    private BigDecimal version;

    @ApiModelProperty(value = "车辆id")
    @TableField("CAR_UUID")
    private String carUuid;


}

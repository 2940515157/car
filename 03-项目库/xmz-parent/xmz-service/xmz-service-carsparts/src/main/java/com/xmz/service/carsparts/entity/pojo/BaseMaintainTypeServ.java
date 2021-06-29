package com.xmz.service.carsparts.entity.pojo;

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
 * 立洋保养类型与服务项目的对照表
 * </p>
 *
 * @author leiyaping
 * @since 2021-06-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "BaseMaintainTypeServ对象", description = "立洋保养类型与服务项目的对照表")
public class BaseMaintainTypeServ implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "MTS_UUID", type = IdType.ASSIGN_UUID)
    private String mtsUuid;

    @ApiModelProperty(value = "保养类型,对应数据字典:DICT_MAINTAIN_TYPE")
    @TableField("MAINTAIN_TYPE")
    private String maintainType;

    @ApiModelProperty(value = "总部统一服务目录主键")
    @TableField("CATALOG_UUID")
    private String catalogUuid;

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


}

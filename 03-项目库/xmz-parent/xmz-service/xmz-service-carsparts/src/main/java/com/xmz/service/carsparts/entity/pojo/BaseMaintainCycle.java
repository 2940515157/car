package com.xmz.service.carsparts.entity.pojo;

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
 * 保养周期
 * </p>
 *
 * @author weikang
 * @since 2021-05-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="BaseMaintainCycle对象", description="保养周期")
public class BaseMaintainCycle implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "MC_UUID", type = IdType.ASSIGN_UUID)
    private String mcUuid;

    @ApiModelProperty(value = "车型ID")
    @TableField("CAR_TYPE_UUID")
    private String carTypeUuid;

    @ApiModelProperty(value = "保养类型,对应数据字典")
    @TableField("MAINTAIN_TYPE")
    private String maintainType;

    @ApiModelProperty(value = "首保里程")
    @TableField("MF_MILEAGE")
    private BigDecimal mfMileage;

    @ApiModelProperty(value = "首保时间(月)")
    @TableField("MF_DATE")
    private BigDecimal mfDate;

    @ApiModelProperty(value = "二保里程")
    @TableField("MS_MILEAGE")
    private BigDecimal msMileage;

    @ApiModelProperty(value = "二保时间(月)")
    @TableField("MS_DATE")
    private BigDecimal msDate;

    @ApiModelProperty(value = "间隔里程")
    @TableField("INTERVAL_MILEAGE")
    private BigDecimal intervalMileage;

    @ApiModelProperty(value = "间隔时间(月)")
    @TableField("INTERVAL_DATE")
    private BigDecimal intervalDate;

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

    @ApiModelProperty(value = "首保操作")
    @TableField("MF_OP")
    private String mfOp;

    @ApiModelProperty(value = "二保操作")
    @TableField("MS_OP")
    private String msOp;

    @ApiModelProperty(value = "间隔操作")
    @TableField("INTERVAL_OP")
    private String intervalOp;


}

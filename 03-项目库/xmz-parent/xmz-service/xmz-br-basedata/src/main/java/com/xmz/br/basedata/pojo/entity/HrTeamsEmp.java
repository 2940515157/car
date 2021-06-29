package com.xmz.br.basedata.pojo.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 班组成员信息
 * </p>
 *
 * @author 驰骋
 * @since 2021-06-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="HrTeamsEmp对象", description="班组成员信息")
public class HrTeamsEmp implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "uuid")
    @TableId(value = "TEAM_EMP_UUID", type = IdType.ID_WORKER)
    private String teamEmpUuid;

    @ApiModelProperty(value = "班组UUID")
    @TableField("TEAM_UUID")
    private String teamUuid;

    @ApiModelProperty(value = "员工UUID")
    @TableField("EMP_UUID")
    private String empUuid;

    @ApiModelProperty(value = "分成比例")
    @TableField("RATE")
    private BigDecimal rate;

    @ApiModelProperty(value = "是否有效(Y:有效；N:无效)")
    @TableField("IS_ACTIVE")
    @TableLogic
    private String isActive;

    @ApiModelProperty(value = "创建日期")
    @TableField(value = "CREATED_DATE",fill = FieldFill.INSERT)
    private Date createdDate;

    @ApiModelProperty(value = "修改日期")
    @TableField(value = "UPDATED_DATE",fill =FieldFill.INSERT_UPDATE)
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

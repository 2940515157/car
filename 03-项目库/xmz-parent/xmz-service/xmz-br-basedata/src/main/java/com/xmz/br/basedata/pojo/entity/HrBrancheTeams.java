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
 * 班组信息
 * </p>
 *
 * @author 驰骋
 * @since 2021-06-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="HrBrancheTeams对象", description="班组信息")
public class HrBrancheTeams implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "uuid")
    @TableId(value = "TEAM_UUID", type = IdType.ID_WORKER)
    private String teamUuid;

    @ApiModelProperty(value = "名称")
    @TableField("TEAM_NAME")
    private String teamName;

    @ApiModelProperty(value = "门店UUID")
    @TableField("BR_UUID")
    private String brUuid;

    @ApiModelProperty(value = "车间")
    @TableField("BD_UUID")
    private String bdUuid;

    @ApiModelProperty(value = "班组类型（标准班组、自组织班组）")
    @TableField("TEAM_TYPE")
    private String teamType;

    @ApiModelProperty(value = "是否有效(Y:有效；N:无效)")
    @TableField("IS_ACTIVE")
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

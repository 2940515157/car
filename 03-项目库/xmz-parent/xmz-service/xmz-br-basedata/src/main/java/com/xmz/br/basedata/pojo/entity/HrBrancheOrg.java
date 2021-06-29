package com.xmz.br.basedata.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 车间信息
 * </p>
 *
 * @author 驰骋
 * @since 2021-05-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="HrBrancheOrg对象", description="车间信息")
public class HrBrancheOrg implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "uuid")
    @TableId(value = "BD_UUID", type = IdType.ID_WORKER)
    private String bdUuid;

    @ApiModelProperty(value = "车间名称")
    @TableField("ORG_NAME")
    private String orgName;

    @ApiModelProperty(value = "门店UUID")
    @TableField("BR_UUID")
    private String brUuid;

    @ApiModelProperty(value = "车间类型（油漆、机修、美容）")
    @TableField("ORG_TYPE")
    private String orgType;

    @ApiModelProperty(value = "是否有效(Y:有效；N:无效)")
    @TableField("IS_ACTIVE")
    private String isActive;

    @ApiModelProperty(value = "创建日期")
    @TableField(value = "CREATED_DATE",fill = FieldFill.INSERT)
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd hh:mm:ss")
    private Date createdDate;

    @ApiModelProperty(value = "修改日期")
    @TableField(value = "UPDATED_DATE",fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd hh:mm:ss")
    private Date updatedDate;

    @ApiModelProperty(value = "创建人")
    @TableField("CREATED_BY")
    private String createdBy;

    @ApiModelProperty(value = "修改人")
    @TableField("UPDATED_BY")
    private String updatedBy;

    @ApiModelProperty(value = "版本")
    @TableField("`VERSION`")
    private BigDecimal version;


}

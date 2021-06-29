package com.xmz.br.basedata.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
 * 区域表
 * </p>
 *
 * @author 驰骋
 * @since 2021-05-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="HrArea对象", description="区域表")
public class HrArea implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "uuid")
    @TableId(value = "AREA_UUID", type = IdType.ID_WORKER)
    private String areaUuid;

    @ApiModelProperty(value = "父类区域")
    @TableField("PARENT_UUID")
    private String parentUuid;

    @ApiModelProperty(value = "大区")
    @TableField("ORG_UUID")
    private String orgUuid;

    @ApiModelProperty(value = "名称")
    @TableField("AREA_NAME")
    private String areaName;

    @ApiModelProperty(value = "区域代码")
    @TableField("AREA_CODE")
    private String areaCode;

    @ApiModelProperty(value = "备注")
    @TableField("REMARK")
    private String remark;

    @ApiModelProperty(value = "是否有效(Y:有效；N:无效)")
    @TableField("IS_ACTIVE")
    @TableLogic
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

    @ApiModelProperty(value = "区域名称的拼音")
    @TableField("PINYIN")
    private String pinyin;


}

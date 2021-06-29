package com.xmz.br.vipCard.entity.pojo;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * 门店与优惠券对应关系
 * </p>
 *
 * @author LLY
 * @since 2021-06-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("br_coupon")
@ApiModel(value="BrCouponPojo对象", description="门店与优惠券对应关系")
public class BrCouponPojo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "uuid")
    @TableId("BC_UUID")
    private String bcUuid;

    @ApiModelProperty(value = "门店UUID")
    @TableField("BR_UUID")
    private String brUuid;

    @ApiModelProperty(value = "优惠券UUID")
    @TableField("COUPON_UUID")
    private String couponUuid;

    @ApiModelProperty(value = "状态")
    @TableField("STATUS")
    private String status;

    @ApiModelProperty(value = "上架日期")
    @TableField("PUT_ON_DATE")
    private Date putOnDate;

    @ApiModelProperty(value = "下架日期")
    @TableField("PULL_OFF_DATE")
    private Date pullOffDate;

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

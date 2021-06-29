package com.xmz.br.storages.entity.pojo;

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
 * 出库单
 * </p>
 *
 * @author hwb
 * @since 2021-06-19
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @ApiModel(value="WmWarehouseOut对象", description="出库单")
public class WmWarehouseOut implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "主键ID")
        @TableId(value = "WWO_UUID", type = IdType.ASSIGN_UUID)
      private String wwoUuid;

    @ApiModelProperty(value = "仓库名")
    @TableField("WM_WAREHOUSE_NAME")
    private String wmWarehouseName;

    @ApiModelProperty(value = "接车单据号")
    @TableField("REV_NO")
    private String revNo;

    @ApiModelProperty(value = "车主")
    @TableField("CUST_NAME")
    private String custName;

    @ApiModelProperty(value = "车主电话")
    @TableField("CUST_PHONE")
    private String custPhone;

    @ApiModelProperty(value = "车牌")
    @TableField("LICENSE_PLATE")
    private String licensePlate;

      @ApiModelProperty(value = "接车单UUID")
      @TableField("REV_UUID")
    private String revUuid;

      @ApiModelProperty(value = "仓库UUID")
      @TableField("WW_UUID")
    private String wwUuid;

      @ApiModelProperty(value = "出库单号")
      @TableField("WWO_NO")
    private String wwoNo;

      @ApiModelProperty(value = "出库类型")
      @TableField("WWO_TYPE")
    private String wwoType;

      @ApiModelProperty(value = "操作人UUID")
      @TableField("USER_UUID")
    private String userUuid;

      @ApiModelProperty(value = "来源UUID")
      @TableField("FROM_UUID")
    private String fromUuid;

      @ApiModelProperty(value = "来源类型")
      @TableField("FROM_TYPE")
    private String fromType;

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

      @ApiModelProperty(value = "状态")
      @TableField("STATUS")
    private String status;

      @ApiModelProperty(value = "总金额")
      @TableField("TOTAL_AMOUNT")
    private BigDecimal totalAmount;

      @ApiModelProperty(value = "是否为反向操作")
      @TableField("IS_REVERSE")
    private String isReverse;

      @ApiModelProperty(value = "来源单据号")
      @TableField("FROM_NO")
    private String fromNo;

      @ApiModelProperty(value = "库存锁定单UUID")
      @TableField("WILH_UUID")
    private String wilhUuid;

      @ApiModelProperty(value = "库存锁定单据号")
      @TableField("WILH_NO")
    private String wilhNo;


}

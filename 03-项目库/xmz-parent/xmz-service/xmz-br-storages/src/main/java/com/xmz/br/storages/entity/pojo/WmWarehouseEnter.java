package com.xmz.br.storages.entity.pojo;

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
 * 入库单
 * </p>
 *
 * @author hwb
 * @since 2021-06-16
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @ApiModel(value="WmWarehouseEnter对象", description="入库单")
public class WmWarehouseEnter implements Serializable {

    private static final long serialVersionUID = 1L;

        private String dictname1;

        private String dictname2;

      @ApiModelProperty(value = "主键ID")
        @TableId(value = "WWE_UUID", type = IdType.ASSIGN_UUID)
      private String wweUuid;

      @ApiModelProperty(value = "接车单UUID")
      @TableField("REV_UUID")
    private String revUuid;

      @ApiModelProperty(value = "仓库UUID")
      @TableField("WW_UUID")
    private String wwUuid;

      @ApiModelProperty(value = "仓库名")
      @TableField("WM_WAREHOUSE_NAME")
    private String wmWarehouseName;

      @ApiModelProperty(value = "入库单号")
      @TableField("WWE_NO")
    private String wweNo;

      @ApiModelProperty(value = "操作人UUID")
      @TableField("USER_UUID")
    private String userUuid;

      @ApiModelProperty(value = "入库类型")
      @TableField("WWE_TYPE")
    private String wweType;

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
    private String version;

      @ApiModelProperty(value = "状态")
      @TableField("STATUS")
    private String status;

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

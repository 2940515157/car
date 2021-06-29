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
 * 仓库
 * </p>
 *
 * @author hwb
 * @since 2021-06-16
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @ApiModel(value="WmWarehouse对象", description="仓库")
public class WmWarehouse implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "主键ID")
        @TableId(value = "WM_WAREHOUSE_UUID", type = IdType.ASSIGN_UUID)
      private String wmWarehouseUuid;

      @ApiModelProperty(value = "所属门店ID")
      @TableField("BR_UUID")
    private String brUuid;

      @ApiModelProperty(value = "仓库名")
      @TableField("WM_WAREHOUSE_NAME")
    private String wmWarehouseName;

      @ApiModelProperty(value = "仓库管理员")
      @TableField("WM_WAREHOUSE_MANAGER")
    private String wmWarehouseManager;

      @ApiModelProperty(value = "仓库业务类型")
      @TableField("WM_WAREHOUSE_TYPE")
    private String wmWarehouseType;

      @ApiModelProperty(value = "备注")
      @TableField("REMARK")
    private String remark;

      @ApiModelProperty(value = "是否有效(Y:有效；N:无效)")
      @TableField("IS_ACTIVE")
    private String isActive;

      @ApiModelProperty(value = "创建(签单)日期")
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

      @ApiModelProperty(value = "供应商ID")
      @TableField("VNDR_ID")
    private String vndrId;

      @ApiModelProperty(value = "仓库编号")
      @TableField("WM_WAREHOUSE_CODE")
    private String wmWarehouseCode;

      @ApiModelProperty(value = "理货员")
      @TableField("WM_WAREHOUSE_CHECKER")
    private String wmWarehouseChecker;

      @ApiModelProperty(value = "联系人")
      @TableField("CONTACT")
    private String contact;

      @ApiModelProperty(value = "手机")
      @TableField("PHONE")
    private String phone;

      @ApiModelProperty(value = "电话")
      @TableField("TEL")
    private String tel;

      @ApiModelProperty(value = "地址")
      @TableField("ADDRESS")
    private String address;

      @ApiModelProperty(value = "仓库类型(DICT_WAREHOUSE_TYPE)")
      @TableField("TYPE")
    private String type;

      @ApiModelProperty(value = "状态")
      @TableField("STATUS")
    private String status;

      @TableField(exist = false)
    private String empName;//员工名称
    @TableField(exist = false)
    private String brName;//门店名称
}

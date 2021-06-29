package com.xmz.service.system.entity.pojo;

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
 * 页面操作按钮
 * </p>
 *
 * @author 罗俊
 * @since 2021-06-02
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @ApiModel(value="SysOperation对象", description="页面操作按钮")
public class SysOperation implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "主键ID")
        @TableId(value = "OPERATION_ID", type = IdType.ASSIGN_UUID)
      private String operationId;

      @ApiModelProperty(value = "类型")
      @TableField("NAME")
    private String name;

      @ApiModelProperty(value = "按钮类型")
      @TableField("TYPE")
    private String type;

      @ApiModelProperty(value = "资源")
      @TableField("RESOURCE_ID")
    private String resourceId;

      @ApiModelProperty(value = "说明")
      @TableField("MEMO")
    private String memo;

      @ApiModelProperty(value = "序号")
      @TableField("SEQ_NO")
    private BigDecimal seqNo;

      @ApiModelProperty(value = "按钮代码")
      @TableField("OPERATION_NO")
    private String operationNo;

      @ApiModelProperty(value = "是否允许编辑")
      @TableField("IS_AUDIT")
    private String isAudit;

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

      @ApiModelProperty(value = "扩展字段1")
      @TableField("EXT1")
    private String ext1;

      @ApiModelProperty(value = "扩展字段2")
      @TableField("EXT2")
    private String ext2;


}

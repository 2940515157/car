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
 * 资源
 * </p>
 *
 * @author KONG_No001
 * @since 2021-05-28
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @ApiModel(value="SysResource对象", description="资源")
public class SysResource implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "资源UUID")
        @TableId(value = "RESOURCE_ID", type = IdType.ASSIGN_UUID)
      private String resourceId;

      @ApiModelProperty(value = "资源名称")
      @TableField("RESOURCE_NAME")
    private String resourceName;

      @ApiModelProperty(value = "资源类型 数据字典：页面、按钮")
      @TableField("RESOURCE_TYPE")
    private String resourceType;

      @ApiModelProperty(value = "资源来源 数据字典：总部、门店")
      @TableField("RESOURCE_ORIGIN")
    private String resourceOrigin;

      @ApiModelProperty(value = "资源编码")
      @TableField("RESOURCE_NO")
    private String resourceNo;

      @ApiModelProperty(value = "资源等级")
      @TableField("RESOURCE_LEVEL")
    private String resourceLevel;

      @ApiModelProperty(value = "父资源ID")
      @TableField("PARENT_ID")
    private String parentId;

      @ApiModelProperty(value = "URL")
      @TableField("URL")
    private String url;

      @ApiModelProperty(value = "资源树编码")
      @TableField("TREE_CODE")
    private String treeCode;

      @ApiModelProperty(value = "排序号")
      @TableField("TREE_SEQ")
    private BigDecimal treeSeq;

      @ApiModelProperty(value = "URI")
      @TableField("URI")
    private String uri;

      @ApiModelProperty(value = "描述")
      @TableField("MEMO")
    private String memo;

      @ApiModelProperty(value = "图标")
      @TableField("IMG")
    private String img;

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

      @ApiModelProperty(value = "是否显示")
      @TableField("IS_SHOW")
    private String isShow;


}

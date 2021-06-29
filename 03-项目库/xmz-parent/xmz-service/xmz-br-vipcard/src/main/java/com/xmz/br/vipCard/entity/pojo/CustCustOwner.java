package com.xmz.br.vipCard.entity.pojo;

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
 * 客户专属化记录
 * </p>
 *
 * @author zt
 * @since 2021-06-06
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @ApiModel(value="CustCustOwner对象", description="客户专属化记录")
public class CustCustOwner implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "uuid")
        @TableId(value = "OW_UUID", type = IdType.ASSIGN_UUID)
      private String owUuid;

      @ApiModelProperty(value = "专属服务顾问ID")
      @TableField("SA_UUID")
    private String saUuid;

      @ApiModelProperty(value = "门店")
      @TableField("BR_UUID")
    private String brUuid;

      @ApiModelProperty(value = "前一个服务顾问ID")
      @TableField("OLD_SA_UUID")
    private String oldSaUuid;

      @ApiModelProperty(value = "前一门店")
      @TableField("OLD_BR_UUID")
    private String oldBrUuid;

      @ApiModelProperty(value = "专属原因")
      @TableField("REASON")
    private String reason;

      @ApiModelProperty(value = "专属日期")
      @TableField("OWNED_DATE")
    private Date ownedDate;

      @ApiModelProperty(value = "专属化方式（随机转专属、客户自助更换、客户同意更换、原SA推荐更换、门店更换、总部更换）")
      @TableField("OWNED_TYPE")
    private String ownedType;

      @ApiModelProperty(value = "扩展1")
      @TableField("EXT1")
    private String ext1;

      @ApiModelProperty(value = "扩展2")
      @TableField("EXT2")
    private String ext2;

      @ApiModelProperty(value = "扩展3")
      @TableField("EXT3")
    private String ext3;

      @ApiModelProperty(value = "扩展4")
      @TableField("EXT4")
    private String ext4;

      @ApiModelProperty(value = "扩展5")
      @TableField("EXT5")
    private String ext5;

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

      @ApiModelProperty(value = "客户UUID")
      @TableField("CUST_UUID")
    private String custUuid;


}

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
 * 门店领卡
 * </p>
 *
 * @author zt
 * @since 2021-06-06
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @ApiModel(value="AcctBrCard对象", description="门店领卡")
public class AcctBrCard implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "主键")
        @TableId(value = "CARD_UUID", type = IdType.ASSIGN_UUID)
      private String cardUuid;

      @ApiModelProperty(value = "卡库主键")
      @TableField("CARD_STORE_UUID")
    private String cardStoreUuid;

      @ApiModelProperty(value = "卡号")
      @TableField("CARD_NO")
    private String cardNo;

      @ApiModelProperty(value = "领卡门店")
      @TableField("BR_UUID")
    private String brUuid;

      @ApiModelProperty(value = "领卡人")
      @TableField("SA_UUID")
    private String saUuid;

      @ApiModelProperty(value = "发放人（默认登陆用户）")
      @TableField("EXTEND_UUID")
    private String extendUuid;

      @ApiModelProperty(value = "卡商(字典:小拇指卡)")
      @TableField("CARD_COMPANY")
    private String cardCompany;

      @ApiModelProperty(value = "限制使用区域(门店，某区域[目前处理不了。不能用字典，要加表]，全国)")
      @TableField("USE_AREA")
    private String useArea;

      @ApiModelProperty(value = "是否已经发放给客户(Y:是；N:否)")
      @TableField("IS_EXTEND")
    private String isExtend;

      @ApiModelProperty(value = "有效期")
      @TableField("VALID_DATE")
    private Date validDate;

      @ApiModelProperty(value = "备注")
      @TableField("REMARK")
    private String remark;

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

      @ApiModelProperty(value = "卡片状态")
      @TableField("BR_CARD_STATUS")
    private String brCardStatus;

      @ApiModelProperty(value = "是否导入")
      @TableField("IMP_FLAG")
    private String impFlag;


}

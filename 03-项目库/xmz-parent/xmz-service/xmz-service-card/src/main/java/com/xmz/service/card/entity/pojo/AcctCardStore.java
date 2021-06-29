package com.xmz.service.card.entity.pojo;

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
 * 卡库
 * </p>
 *
 * @author liujiaxi
 * @since 2021-06-09
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @ApiModel(value="AcctCardStore对象", description="卡库")
public class AcctCardStore implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "主键")
        @TableId(value = "CARD_STORE_UUID", type = IdType.ASSIGN_UUID)
      private String cardStoreUuid;

      @ApiModelProperty(value = "卡号前缀")
      @TableField("CARD_PREFIX")
    private String cardPrefix;

      @ApiModelProperty(value = "卡号开始")
      @TableField("CARD_NO_BEG")
    private String cardNoBeg;

      @ApiModelProperty(value = "卡号结束")
      @TableField("CARD_NO_END")
    private String cardNoEnd;

      @ApiModelProperty(value = "共计张")
      @TableField("CARD_NUM")
    private BigDecimal cardNum;

      @ApiModelProperty(value = "剩余张")
      @TableField("REMAIN_CARD_NUM")
    private BigDecimal remainCardNum;

      @ApiModelProperty(value = "录卡人")
      @TableField("SA_UUID")
    private String saUuid;

      @ApiModelProperty(value = "类型（字典：白金，金卡，银卡，普通)")
      @TableField("CARD_TYPE")
    private String cardType;

      @ApiModelProperty(value = "卡商(字典:小拇指卡)")
      @TableField("CARD_COMPANY")
    private String cardCompany;

      @ApiModelProperty(value = "制卡时间")
      @TableField("MAKE_DATE")
    private Date makeDate;

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

      @ApiModelProperty(value = "是否导入")
      @TableField("IMP_FLAG")
    private String impFlag;


}

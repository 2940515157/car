package com.xmz.service.carsparts.entity.pojo;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author leiyaping
 * @since 2021-06-09
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @ApiModel(value="BaseServCatalogBack对象", description="")
public class BaseServCatalogBack implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "CATALOG_UUID",type = IdType.ASSIGN_UUID)
    private String catalogUuid;

    @TableField("SERV_TYPE_UUID")
    private String servTypeUuid;

    @TableField("BR_UUID")
    private String brUuid;

    @TableField("STD_CATALOG_UUID")
    private String stdCatalogUuid;

    @TableField("CATALOG_CODE")
    private String catalogCode;

    @TableField("CATALOG_NAME")
    private String catalogName;

    @TableField("PINYIN")
    private String pinyin;

    @TableField("STD_MAN_HOUR")
    private BigDecimal stdManHour;

    @TableField("STD_PRICE")
    private BigDecimal stdPrice;

    @TableField("STD_SIDE_NUM")
    private BigDecimal stdSideNum;

    @TableField("COMM_RATE_SA")
    private BigDecimal commRateSa;

    @TableField("COMM_RATE_WORKER")
    private BigDecimal commRateWorker;

    @TableField("SA_CAN_EDIT_PRICE")
    private String saCanEditPrice;

    @TableField("IS_SAME_PRICE")
    private String isSamePrice;

    @TableField("BR_CAN_EDIT_NAME")
    private String brCanEditName;

    @TableField("EXT1")
    private String ext1;

    @TableField("EXT2")
    private String ext2;

    @TableField("EXT3")
    private String ext3;

    @TableField("EXT4")
    private String ext4;

    @TableField("EXT5")
    private String ext5;

    @TableField("IS_ACTIVE")
    private String isActive;

    @TableField("CREATED_DATE")
    private Date createdDate;

    @TableField("UPDATED_DATE")
    private Date updatedDate;

    @TableField("CREATED_BY")
    private String createdBy;

    @TableField("UPDATED_BY")
    private String updatedBy;

    @TableField("VERSION")
    private BigDecimal version;

    @TableField("IS_QUICK")
    private String isQuick;

    @TableField("IS_COMMON")
    private String isCommon;

    @TableField("CATALOG_NAME_SHORT")
    private String catalogNameShort;

    @TableField("ALIAS1")
    private String alias1;

    @TableField("ALIAS2")
    private String alias2;

    @TableField("ALIAS3")
    private String alias3;

    @TableField("ALIAS4")
    private String alias4;

    @TableField("ALIAS5")
    private String alias5;

    @TableField("STAT_TYPE")
    private String statType;

    @TableField("STAT_METHOD")
    private String statMethod;


}

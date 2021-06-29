package com.xmz.service.basedata.entity.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author weikang
 * @since 2021-05-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="EosDictType对象", description="字典类型")
@TableName
public class EosDictType implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "DICTTYPEID", type = IdType.ASSIGN_UUID)
    private String dicttypeid;

    @TableField("DICTTYPENAME")
    private String dicttypename;

    @TableField("`RANK`")
    private BigDecimal rank;

    @TableField("PARENTID")
    private String parentid;

    @TableField("SEQNO")
    private String seqno;

    @ApiModelProperty(value = "类型(系统字典[sys],业务字典[busi])")
    @TableField("DICT_TYPE")
    private String dictType;

    @TableField("IS_ACTIVE")
    private String isActive;

    @TableField(exist = false)
    private List<EosDictEntry> dictEntryList;


}

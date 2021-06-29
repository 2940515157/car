package com.xmz.service.basedata.entity.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

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
@ApiModel(value="EosDictEntry对象", description="字典详情")
public class EosDictEntry implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("DICTTYPEID")
    private String dicttypeid;

    @TableId(value = "DICTID", type = IdType.ASSIGN_UUID)
    private String dictid;

    @TableField("DICTNAME")
    private String dictname;

    @TableField("STATUS")
    private BigDecimal status;

    @TableField("SORTNO")
    private BigDecimal sortno;

    @TableField("`RANK`")
    private BigDecimal rank;

    @TableField("PARENTID")
    private String parentid;

    @TableField("SEQNO")
    private String seqno;

    @TableField("FILTER1")
    private String filter1;

    @TableField("FILTER2")
    private String filter2;

    @TableField("IS_ACTIVE")
    private String isActive;


}

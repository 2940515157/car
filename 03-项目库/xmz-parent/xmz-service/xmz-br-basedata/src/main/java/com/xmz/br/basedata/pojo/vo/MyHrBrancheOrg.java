package com.xmz.br.basedata.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.xmz.br.basedata.pojo.entity.HrBrancheOrg;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MyHrBrancheOrg extends HrBrancheOrg {

    @ApiModelProperty(value = "车间类型")
    @TableField("dictname")
    private String dictname;

}

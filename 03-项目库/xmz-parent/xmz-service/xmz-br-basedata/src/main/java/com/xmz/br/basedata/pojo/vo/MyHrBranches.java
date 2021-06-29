package com.xmz.br.basedata.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.xmz.br.basedata.pojo.entity.HrBranches;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MyHrBranches extends HrBranches {




    //大区名称
    @ApiModelProperty(value = "大区")
    @TableField("ORG_NAME")
    private String orgName;

    //区域名称
    @ApiModelProperty(value = "区域")
    @TableField("AREA_NAME")
    private String areaName;



}

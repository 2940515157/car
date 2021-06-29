package com.xmz.br.basedata.pojo.vo;

import com.xmz.br.basedata.pojo.entity.HrBranches;
import lombok.Data;

@Data
public class HrBranchesPage extends HrBranches {

    private String orgName;//大区名字

    private String areaName;//区域名

    private String dictname;//门店类型名
}

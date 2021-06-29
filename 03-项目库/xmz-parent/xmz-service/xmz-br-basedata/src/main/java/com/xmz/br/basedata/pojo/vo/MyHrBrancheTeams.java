package com.xmz.br.basedata.pojo.vo;

import com.xmz.br.basedata.pojo.entity.HrBrancheOrg;
import com.xmz.br.basedata.pojo.entity.HrBrancheTeams;
import lombok.Data;

import java.io.Serializable;

@Data
public class MyHrBrancheTeams extends HrBrancheTeams implements Serializable {


    //班组类型名称
    private String dictname;

    //车间 信息
    private HrBrancheOrg hrBrancheOrg;
}

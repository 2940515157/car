package com.xmz.br.basedata.pojo.vo;


import lombok.Data;

@Data
public class QueryHrBrancheTeams {

    //门店的bruuid
    private String brUuid;

    //班组名称
    private String name;

    //班组类型
    private String type;

    //车间 bdUuid
    private String bdUuid;
}

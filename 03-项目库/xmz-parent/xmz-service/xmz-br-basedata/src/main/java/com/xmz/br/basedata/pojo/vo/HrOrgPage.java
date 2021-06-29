package com.xmz.br.basedata.pojo.vo;

import com.xmz.br.basedata.pojo.entity.HrOrg;
import lombok.Data;

import java.util.List;

@Data
public class HrOrgPage extends HrOrg {

    private String parentName;

    private List<HrOrgPage> children;
}

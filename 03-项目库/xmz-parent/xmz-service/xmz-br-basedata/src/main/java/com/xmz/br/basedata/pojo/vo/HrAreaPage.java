package com.xmz.br.basedata.pojo.vo;

import com.xmz.br.basedata.pojo.entity.HrArea;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class HrAreaPage extends HrArea {

    public String orgName;//大区名称
}

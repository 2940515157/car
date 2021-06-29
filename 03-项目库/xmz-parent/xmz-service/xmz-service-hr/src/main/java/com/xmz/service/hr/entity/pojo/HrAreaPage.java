package com.xmz.service.hr.entity.pojo;

import com.xmz.service.hr.entity.pojo.HrArea;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.lang.reflect.Type;

@Data
@EqualsAndHashCode(callSuper = false)
public class HrAreaPage extends HrArea {
    //大区名称
    public String orgName;
}

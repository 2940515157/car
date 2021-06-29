package com.xmz.service.hr.entity.vo;

import com.xmz.service.hr.entity.pojo.HrBranches;
import lombok.Data;

import java.io.Serializable;

/**
 * 用于门店信息的查询结果
 */
@Data
public class MyHrBranches extends HrBranches implements Serializable {

    private String sheng;//省份
    private String shi;//城市
    private String quYu;//区域
    private String daQu;//大区
    private String brTypeName;

}

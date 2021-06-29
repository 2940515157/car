package com.xmz.service.carsparts.entity.vo;

import com.xmz.service.carsparts.entity.pojo.BaseCarPartsSku;
import lombok.Data;

import java.io.Serializable;

@Data
public class MyBaseCarPartsSku extends BaseCarPartsSku implements Serializable {

    private String bptNAME;//配件目录
    private String hbBRCODE;//门店编号
    private String hbBRNAME;//门店名称
    private String brandName;//品牌名
}

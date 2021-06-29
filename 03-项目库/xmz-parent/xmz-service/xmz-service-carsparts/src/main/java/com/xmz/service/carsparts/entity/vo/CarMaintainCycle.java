package com.xmz.service.carsparts.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarMaintainCycle implements Serializable {
    private String mcUuid;
    private String carTypeUuid;
    private String maintainType;
    private BigDecimal mfMileage;
    private BigDecimal mfDate;
    private BigDecimal msMileage;
    private BigDecimal msDate;
    private BigDecimal intervalMileage;
    private BigDecimal intervalDate;
    private String remark;
    private String isActive;
    private Date createdDate;
    private Date updatedDate;
    private String createdBy;
    private String updatedBy;
    private BigDecimal version;
    private String mfOp;
    private String msOp;
    private String intervalOp;
    private String brand;
    private String carModel;
    private Integer carYear;
    private String displacement;
    private String saleName;
    private String showName;
    private String dictid;
    private String dictname;
}

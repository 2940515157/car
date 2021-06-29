package com.xmz.service.carsparts.entity.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CarTypeQueryParam {
    private String brand;
    private String model;
    private BigDecimal year;
    private String cc;
}

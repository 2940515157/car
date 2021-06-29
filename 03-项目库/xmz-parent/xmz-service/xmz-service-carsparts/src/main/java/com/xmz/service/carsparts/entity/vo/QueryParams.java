package com.xmz.service.carsparts.entity.vo;

import lombok.Data;

@Data
public class QueryParams {
    private String brand;
    private String model;
    private String year;
    private String cc;
    private String maintainType;
}

package com.xmz.service.carsparts.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaintainTypeServCatalogBack implements Serializable {
    private String mtsUuid;
    private String dictid;
    private String dictname;
    private String catalogUuid;
    private String catalogCode;
    private String catalogName;
    private String maintainType;
    private String remark;
}

package com.xmz.br.carService.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServBookingHeadListVo {
    private String sbUuid;
    private String licensePlate;
    private String custUuid;
    private String custName;
    private String custPhone;
    private String saUuid;
    private String saName;
    private String createdDate;
    private String blDate;
    private String carUuid;
    private String carName;
    private String status;
    private String sblNo;
    private String sblType;
    private String remark;
}

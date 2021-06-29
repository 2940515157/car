package com.xmz.br.carService.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServBookingHeadListQuery {
    private String licensePlate;
    private String custPhone;
    private String custName;
    private String sblNo;
    private String status;
    private String[] blDate;
    private String blDateStart;
    private String blDateEnd;
    private String[] createTime;
    private String createTimeStart;
    private String createTimeEnd;
}

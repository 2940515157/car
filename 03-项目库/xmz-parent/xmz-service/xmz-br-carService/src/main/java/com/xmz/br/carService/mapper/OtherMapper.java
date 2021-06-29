package com.xmz.br.carService.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.br.carService.entity.vo.Cust;

import java.util.List;
import java.util.Map;

public interface OtherMapper {

    Page<Cust> getCustPage(Page<Cust> page, String licensePlate);

    List<Map<String,Object>> getServBookingItems(String sbUuid);
    List<Map<String,Object>> getServBookingParts(String sbUuid);


}

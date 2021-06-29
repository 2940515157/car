package com.xmz.br.carService.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.br.carService.entity.vo.Cust;

public interface IOtherService {

    Page<Cust> getCustPage(Page<Cust> page, String licensePlate);


}

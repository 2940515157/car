package com.xmz.br.carService.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.br.carService.entity.vo.Cust;
import com.xmz.br.carService.service.IOtherService;
import com.xmz.common.utils.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/brCarService/other")
public class OtherController {

    @Autowired
    IOtherService otherService;

    @GetMapping("/custPage/{current}/{size}")
    public ResultMap getCustPage(
            @PathVariable Integer current,
            @PathVariable Integer size,
            String licensePlate){
        Page<Cust> page = new Page<>(current,size);
        otherService.getCustPage(page,licensePlate);
        return ResultMap.getOKInstance()
                .putData("records",page.getRecords())
                .putData("total",page.getTotal())
                .putData("current",page.getCurrent())
                .putData("size",page.getSize())
                .putData("pages",page.getPages());
    }

}

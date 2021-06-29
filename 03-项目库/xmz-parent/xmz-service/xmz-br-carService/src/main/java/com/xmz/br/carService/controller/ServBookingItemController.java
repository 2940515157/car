package com.xmz.br.carService.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.br.carService.entity.pojo.ServBookingItem;
import com.xmz.br.carService.service.IServBookingItemService;
import com.xmz.common.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 预约服务项目 前端控制器
 * </p>
 *
 * @author 罗俊
 * @since 2021-06-17
 */
@RestController
@RequestMapping("/brCarService/serv-booking-item")
public class ServBookingItemController {

    @Autowired
    IServBookingItemService bookingItemService;

    @GetMapping("/listPage")
    public Result<Object> listPage(){
        Page<ServBookingItem> page = new Page<>(1,20);
        return Result.getOKInstance(bookingItemService.page(page));
    }

}


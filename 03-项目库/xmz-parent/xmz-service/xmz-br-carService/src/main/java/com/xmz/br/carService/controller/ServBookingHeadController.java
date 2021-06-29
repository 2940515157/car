package com.xmz.br.carService.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.br.carService.entity.pojo.ServBookingHead;
import com.xmz.br.carService.entity.pojo.ServBookingItem;
import com.xmz.br.carService.entity.pojo.ServBookingPart;
import com.xmz.br.carService.entity.vo.ServBookingHeadListQuery;
import com.xmz.br.carService.entity.vo.ServBookingHeadListVo;
import com.xmz.br.carService.service.IServBookingHeadService;
import com.xmz.common.utils.Result;
import com.xmz.common.utils.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 预约单 前端控制器
 * </p>
 *
 * @author 罗俊
 * @since 2021-06-17
 */
@RestController
@RequestMapping("/brCarService/serv-booking-head")
public class ServBookingHeadController {

    @Autowired
    IServBookingHeadService bookingHeadService;

    @GetMapping("/listPage/{current}/{size}")
    public ResultMap listPage(
            @PathVariable Integer current, @PathVariable Integer size,
            ServBookingHeadListQuery query){
        Page<ServBookingHeadListVo> page = new Page<>(current,size);
        bookingHeadService.getVoPage(page,query);
        return ResultMap.getOKInstance()
                .putData("records",page.getRecords())
                .putData("total",page.getTotal())
                .putData("current",page.getCurrent())
                .putData("size",page.getSize())
                .putData("pages",page.getPages());
    }

    @PostMapping("/saveOrUpdate")
    public Result<Object> save(@RequestBody Map<String,String> data){
        ServBookingHead head = JSONObject.parseObject(data.get("form"),ServBookingHead.class);
        List<ServBookingItem> sctRow = JSONObject.parseArray(data.get("sctRow"), ServBookingItem.class);
        List<ServBookingPart> cptRow = JSONObject.parseArray(data.get("cptRow"), ServBookingPart.class);
        List<String> sctDIds = JSONObject.parseArray(data.get("sctDIds"), String.class);
        List<String> cptDIds = JSONObject.parseArray(data.get("cptDIds"), String.class);

        bookingHeadService.saveOrUpdate(head,sctRow,cptRow,sctDIds,cptDIds);
        return Result.getOKInstance("OK");
    }

    @GetMapping("/getAppointInfo/{sbUuid}")
    public Result<Object> getAppointInfo(@PathVariable String sbUuid){
        return Result.getOKInstance(bookingHeadService.getAppointInfo(sbUuid));
    }

}


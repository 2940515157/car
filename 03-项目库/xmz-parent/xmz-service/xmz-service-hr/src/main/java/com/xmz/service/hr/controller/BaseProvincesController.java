package com.xmz.service.hr.controller;


import com.xmz.common.utils.ResultMap;
import com.xmz.service.hr.entity.pojo.BaseProvinces;
import com.xmz.service.hr.entity.pojo.HrArea;
import com.xmz.service.hr.service.IBaseProvincesService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 省份信息 前端控制器
 * </p>
 *
 * @author 江志威
 * @since 2021-06-09
 */
@RestController
@RequestMapping("/serviceHr/baseProvinces")
public class BaseProvincesController {
    @Autowired
    private IBaseProvincesService iBaseProvincesService;


    // 查询全部省份信息
    @ApiOperation(value = "查询全部省份信息")
    @GetMapping("queryall")
    public ResultMap queryall(){
        List<BaseProvinces> list = iBaseProvincesService.list();
        return ResultMap.getOKInstance().putData("baseProvinces",list);

    }
}


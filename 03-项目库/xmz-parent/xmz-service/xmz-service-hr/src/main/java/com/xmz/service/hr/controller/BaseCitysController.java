package com.xmz.service.hr.controller;


import com.xmz.common.utils.ResultMap;
import com.xmz.service.hr.entity.pojo.BaseCitys;
import com.xmz.service.hr.entity.pojo.BaseProvinces;
import com.xmz.service.hr.entity.pojo.HrArea;
import com.xmz.service.hr.service.IBaseCitysService;
import com.xmz.service.hr.service.IBaseProvincesService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 城市 前端控制器
 * </p>
 *
 * @author 江志威
 * @since 2021-06-09
 */
@RestController
@RequestMapping("/serviceHr/baseCitys")
public class BaseCitysController {
    @Autowired
    private IBaseCitysService iBaseCitysService;


    // 查询全部城市信息
    @ApiOperation(value = "查询全部城市信息")
    @GetMapping("queryall")
    public ResultMap queryall(){
        List<BaseCitys> list = iBaseCitysService.list();
        return ResultMap.getOKInstance().putData("baseCitys",list);

    }

    //根据省份查询城市
    @PostMapping("queryallByProvUuid/{id}")
    @ApiOperation("根据省份查询城市")
    public ResultMap queryallByProvUuid(
            @PathVariable String id) {
        List<BaseCitys> list = iBaseCitysService.queryallByProvUuid(id);
        return ResultMap.getOKInstance().putData("baseCitys",list);
    }
}


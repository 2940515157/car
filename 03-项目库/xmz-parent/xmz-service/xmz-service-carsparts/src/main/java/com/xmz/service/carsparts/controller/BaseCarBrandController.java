package com.xmz.service.carsparts.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.common.utils.ResultMap;
import com.xmz.service.carsparts.entity.pojo.BaseCarBrand;
import com.xmz.service.carsparts.entity.pojo.BasePartsType;
import com.xmz.service.carsparts.entity.vo.BasePartsTypeQuery;
import com.xmz.service.carsparts.service.IBaseCarBrandService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 品牌(车辆) 前端控制器
 * </p>
 *
 * @author 江志威
 * @since 2021-06-15
 */
@RestController
@RequestMapping("/serviceCarsparts/base-car-brand")
public class BaseCarBrandController {

    @Autowired
    IBaseCarBrandService iBaseCarBrandService;

//    @ApiOperation("查询未选择品牌")
//    @PostMapping("queryCarBrandWei/{current}/{size}/{brandUuid}")
//    public ResultMap queryCarBrandWei(
//            @PathVariable Long current,
//            @PathVariable Long size,
//            @PathVariable String brandUuid) {
//        Page<BaseCarBrand> basePartsTypePage = new Page<>(current, size);
//
//        iBaseCarBrandService.queryQueryWei(basePartsTypePage);
//
//        return ResultMap.getOKInstance()
//                .putData("rows",basePartsTypePage.getRecords())
//                .putData("pages",basePartsTypePage.getPages())
//                .putData("total",basePartsTypePage.getTotal())
//                .putData("current",basePartsTypePage.getCurrent())
//                .putData("size",basePartsTypePage.getSize());
//
//    }


    @ApiOperation("查询以选择品牌")
    @PostMapping("queryCarBrandYi/{brandUuid}")
    public ResultMap queryCarBrandYi(
            @PathVariable String brandUuid) {
        List<BaseCarBrand> baseCarBrandList = iBaseCarBrandService.queryQueryYi(brandUuid);

        return ResultMap.getOKInstance().putData("carBrandList",baseCarBrandList);

    }

    @ApiOperation("查询所有品牌")
    @GetMapping("queryCarBrandAll")
    public ResultMap queryCarBrandAll() {
        List<BaseCarBrand> carBrandList = iBaseCarBrandService.list();

        return ResultMap.getOKInstance().putData("carBrandList",carBrandList);

    }
}


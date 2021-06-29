package com.xmz.service.carsparts.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.common.utils.Result;
import com.xmz.common.utils.ResultMap;

import com.xmz.service.carsparts.entity.pojo.BaseCarPartsSku;
import com.xmz.service.carsparts.entity.pojo.BasePartsType;
import com.xmz.service.carsparts.entity.vo.BaseCarPartsSkuQuery;
import com.xmz.service.carsparts.service.IBaseCarPartsSkuService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 配件产品库(含品牌规格型号)(sku级) 前端控制器
 * </p>
 *
 * @author 江志威
 * @since 2021-06-16
 */
@RestController
@RequestMapping("/serviceCarsparts/base-car-parts-sku")
public class BaseCarPartsSkuController {

    @Autowired
    IBaseCarPartsSkuService iBaseCarPartsSkuService;

    @ApiOperation("查询带条件")
    @PostMapping("queryCarPartsSku/{current}/{size}")
    public ResultMap queryCarPartsSku(
            @PathVariable Long current,
            @PathVariable Long size,
            @RequestBody BaseCarPartsSkuQuery baseCarPartsSkuQuery) {
        Page<BaseCarPartsSku> basePartsTypePage = new Page<>(current, size);

        iBaseCarPartsSkuService.queryQuery(basePartsTypePage, baseCarPartsSkuQuery);

        return ResultMap.getOKInstance()
                .putData("rows",basePartsTypePage.getRecords())
                .putData("pages",basePartsTypePage.getPages())
                .putData("total",basePartsTypePage.getTotal())
                .putData("current",basePartsTypePage.getCurrent())
                .putData("size",basePartsTypePage.getSize());

    }

    @PostMapping("/add")
    @ApiOperation("添加配件信息")
    public ResultMap add(
            @ApiParam(name = "basePartsType",value = "配件信息对象",required = true)
            @RequestBody BaseCarPartsSku baseCarPartsSku){
        boolean flag = iBaseCarPartsSkuService.save(baseCarPartsSku);

        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }

    // 根据id查询
    @GetMapping("queryById/{partUuid}")
    @ApiOperation("根据Id查询配件信息")
    public ResultMap queryById(
            @ApiParam(name = "partTypeUuid",value = "配件id",required = true)
            @PathVariable String partUuid){

        BaseCarPartsSku baseCarPartsSku = iBaseCarPartsSkuService.getById(partUuid);

        return ResultMap.getOKInstance()
                .putData("baseCarPartsSku",baseCarPartsSku);
    }

    //修改
    @PostMapping("update")
    @ApiOperation("修改配件信息")
    public ResultMap update(
            @ApiParam(name = "basePartsType",value = "配件目录",required = true)
            @RequestBody BaseCarPartsSku baseCarPartsSku){

        boolean flag = iBaseCarPartsSkuService.updateById(baseCarPartsSku);

        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }

    //删除
    @DeleteMapping("/delete/{id}")
    public Result<Object> delete(@PathVariable String id){
        if (iBaseCarPartsSkuService.removeById(id))
            return Result.getOKInstance("删除成功");
        return Result.getErrorInstance("删除失败");
    }

}


package com.xmz.service.basedata.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.common.utils.Result;
import com.xmz.common.utils.ResultMap;
import com.xmz.service.basedata.entity.pojo.BaseCarBrand;
import com.xmz.service.basedata.entity.vo.BaseCarQuery;
import com.xmz.service.basedata.service.IBaseCarBrandService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 品牌(车辆) 前端控制器
 * </p>
 *
 * @author CodeGenerator
 * @since 2021-05-25
 */
@RestController
@RequestMapping("/serviceBasedata/carBand")
@CrossOrigin
public class BaseCarBrandController {
    @Autowired
    private IBaseCarBrandService iBaseCarBrandService;


    //分页查询品牌信息
    @PostMapping("page/{current}/{size}")
    @ApiOperation("分页查询")
    public ResultMap myPage(
            @PathVariable Long current,
            @PathVariable Long size,
            @RequestBody BaseCarQuery baseCarQuery) {
        System.out.println(current+"---"+size);
        Page<BaseCarBrand> baseCarBrandPage = new Page<>(current, size);
        iBaseCarBrandService.myPage(baseCarBrandPage, baseCarQuery);
        return ResultMap.getOKInstance()
                .putData("rows",baseCarBrandPage.getRecords())
                .putData("pages",baseCarBrandPage.getPages())
                .putData("total",baseCarBrandPage.getTotal())
                .putData("current",baseCarBrandPage.getCurrent())
                .putData("size",baseCarBrandPage.getSize());
    }

    //添加
    @PostMapping("add")
    @ApiOperation("添加汽车品牌")
    public ResultMap add(
            @ApiParam(name = "baseCarBrand",value = "汽车对象",required = true)
            @RequestBody BaseCarBrand baseCarBrand){
        baseCarBrand.setVersion(new BigDecimal("1"));
        baseCarBrand.setCreatedDate(new Date());
        baseCarBrand.setCreatedBy("admin");

        boolean flag = iBaseCarBrandService.save(baseCarBrand);

        if(flag)

            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }

    //删除
    @DeleteMapping("{brandUuid}")
    @ApiOperation("删除汽车品牌")
    public ResultMap delete(
            @ApiParam(name = "brandUuid",value = "品牌id",required = true)
            @PathVariable String brandUuid){

        boolean flag = iBaseCarBrandService.removeById(brandUuid);

        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }


    //修改
    @PostMapping("update")
    @ApiOperation("修改汽车品牌")
    public ResultMap update(
            @ApiParam(name = "baseCarBrand",value = "汽车对象",required = true)
            @RequestBody BaseCarBrand baseCarBrand){
        baseCarBrand.setUpdatedDate(new Date());
        baseCarBrand.setUpdatedBy("admin");

        boolean flag = iBaseCarBrandService.updateById(baseCarBrand);

        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }

    // 根据id查询
    @GetMapping("queryById/{brandUuid}")
    @ApiOperation("根据Id查询汽车品牌信息")
    public ResultMap queryById(
            @ApiParam(name = "brandUuid",value = "品牌id",required = true)
            @PathVariable String brandUuid){

        BaseCarBrand carBrand = iBaseCarBrandService.getById(brandUuid);

        return ResultMap.getOKInstance()
                .putData("carBrand",carBrand);
    }

    //获取 所有 车 品牌
    @GetMapping("/queryAll")
    @ApiOperation("根据类型查询")
    public ResultMap queryAll(){
        QueryWrapper<BaseCarBrand> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("type","car");
        System.out.println(".......");
        return ResultMap.getOKInstance().putData("rows",iBaseCarBrandService.list(queryWrapper));
    }

}


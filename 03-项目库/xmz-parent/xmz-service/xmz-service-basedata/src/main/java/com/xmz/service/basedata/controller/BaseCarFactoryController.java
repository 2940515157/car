package com.xmz.service.basedata.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.common.utils.ResultMap;
import com.xmz.service.basedata.entity.pojo.BaseCarBrand;
import com.xmz.service.basedata.entity.pojo.BaseCarCategory;
import com.xmz.service.basedata.entity.pojo.BaseCarFactory;
import com.xmz.service.basedata.entity.vo.BaseCarQuery;
import com.xmz.service.basedata.entity.vo.BaseFactoryQuery;
import com.xmz.service.basedata.service.IBaseCarFactoryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 厂家 前端控制器
 * </p>
 *
 * @author lm
 * @since 2021-05-31
 */
@RestController
@RequestMapping("/serviceBasedata/carFactory")
@CrossOrigin
public class BaseCarFactoryController {

    @Autowired
    public IBaseCarFactoryService iBaseCarFactoryService;


    //分页查询学生信息
    @PostMapping("page/{current}/{size}")
    @ApiOperation("分页查询")
    public ResultMap myPage(
            @PathVariable Long current,
            @PathVariable Long size,
            @RequestBody BaseFactoryQuery baseFactoryQuery) {
        System.out.println(current+"---"+size);
        Page<BaseCarFactory> baseCarFactoryPage = new Page<>(current, size);
        iBaseCarFactoryService.myPage(baseCarFactoryPage, baseFactoryQuery);
        return ResultMap.getOKInstance()
                .putData("rows",baseCarFactoryPage.getRecords())
                .putData("pages",baseCarFactoryPage.getPages())
                .putData("total",baseCarFactoryPage.getTotal())
                .putData("current",baseCarFactoryPage.getCurrent())
                .putData("size",baseCarFactoryPage.getSize());
    }

    //添加
    @PostMapping("add")
    @ApiOperation("添加汽车厂家")
    public ResultMap add(
            @ApiParam(name = "baseCarFactory",value = "厂家对象",required = true)
            @RequestBody BaseCarFactory baseCarFactory){
        baseCarFactory.setNamePy("0");
        baseCarFactory.setVersion(new BigDecimal(1));
        boolean flag = iBaseCarFactoryService.save(baseCarFactory);

        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }

    //删除
    //FACTORY_UUID
    @DeleteMapping("{factoryUuid}")
    @ApiOperation("删除汽车厂家")
    public ResultMap delete(
            @ApiParam(name = "factoryUuid",value = "厂家id",required = true)
            @PathVariable String factoryUuid){

        boolean flag = iBaseCarFactoryService.removeById(factoryUuid);

        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }

    //修改
    @PostMapping("update")
    @ApiOperation("修改汽车厂家")
    public ResultMap update(
            @ApiParam(name = "baseCarFactory",value = "厂家对象",required = true)
            @RequestBody BaseCarFactory baseCarFactory){

        boolean flag = iBaseCarFactoryService.updateById(baseCarFactory);

        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }

    // 根据id查询
    @GetMapping("queryById/{factoryUuid}")
    @ApiOperation("根据Id查询汽车厂家信息")
    public ResultMap queryById(
            @ApiParam(name = "factoryUuid",value = "厂家id",required = true)
            @PathVariable String factoryUuid){

        BaseCarFactory carFactory = iBaseCarFactoryService.getById(factoryUuid);

        return ResultMap.getOKInstance()
                .putData("carFactory",carFactory);
    }

    //厂家下拉框
    @GetMapping("queryAllFName")
    @ApiOperation("查询厂家下拉框")
    public ResultMap fName(){
        List<BaseCarFactory> list = iBaseCarFactoryService.list();
        return ResultMap.getOKInstance()
                .putData("list",list);
    }
}


package com.xmz.service.basedata.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.common.utils.Result;
import com.xmz.common.utils.ResultMap;


import com.xmz.service.basedata.entity.pojo.BaseProvinces;
import com.xmz.service.basedata.service.IBaseProvincesService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 省份信息 前端控制器
 * </p>
 *
 * @author leiyaping
 * @since 2021-06-03
 */
@RestController
@RequestMapping("/serviceBasedata/baseProvinces")
public class BaseProvincesController {
    @Autowired
    private IBaseProvincesService iBaseProvincesService;


    @PostMapping("page/{countryUuid}/{current}/{size}")
    @ApiOperation("分页查询")
    public ResultMap page(
            @ApiParam(name="page",value = "当前页号",required = true)
            @PathVariable Integer current,
            @ApiParam(name="size",value = "页大小",required = true)
            @PathVariable Integer size,
            @PathVariable String countryUuid
    ){
        //分页参数
        Page<BaseProvinces> provincesPage=new Page<>(current,size);
        iBaseProvincesService.queryPage(provincesPage,countryUuid);
        return ResultMap.getOKInstance()
                .putData("rows",provincesPage.getRecords())
                .putData("total",provincesPage.getTotal())
                .putData("pages",provincesPage.getPages())
                .putData("current",provincesPage.getCurrent())
                .putData("size",provincesPage.getSize());
    }
    @GetMapping("queryById/{countryUuid}/{provinceUuid}")
    @ApiOperation("根据id查询省信息")
    public ResultMap queryById(
            @ApiParam(name="countryUuid",value = "国家id",required = true)
            @PathVariable String countryUuid,
            @ApiParam(name="provinceUuid",value = "省id",required = true)
            @PathVariable String provinceUuid){
        BaseProvinces baseProvinces=iBaseProvincesService.queryById(countryUuid,provinceUuid);
        return ResultMap.getOKInstance().putData("baseProvinces",baseProvinces);
    }
    @PostMapping("add")
    @ApiOperation("添加省信息")
    public Result add(
            @ApiParam(name="baseProvinces",value="添加省信息",required = true)
            @RequestBody BaseProvinces baseProvinces){

        boolean flag=iBaseProvincesService.save(baseProvinces);

        if(flag)
            return Result.getOKInstance();
        return Result.getErrorInstance();
    }

    @PostMapping("update")
    @ApiOperation("修改省信息")
    public Result update(
            @ApiParam(name="baseProvinces",value = "省对象",required = true)
            @RequestBody BaseProvinces baseProvinces){

        boolean flag=iBaseProvincesService.updateById(baseProvinces);

        if(flag)
            return Result.getOKInstance();
        return  Result.getErrorInstance();
    }

    @DeleteMapping("delete/{provinceUuid}")
    @ApiOperation(value = "根据用户编号逻辑删除省信息")
    public Result delete(
            @ApiParam(name = "provinceUuid", value = "省编号", required = true)
            @PathVariable String provinceUuid) {
        boolean flag = iBaseProvincesService.removeById(provinceUuid);
        if(flag)
            return Result.getOKInstance();
        return Result.getErrorInstance();
    }

}


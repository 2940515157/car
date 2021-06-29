package com.xmz.service.basedata.controller;


import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.common.utils.Result;
import com.xmz.common.utils.ResultMap;
import com.xmz.service.basedata.entity.pojo.BaseCountrys;
import com.xmz.service.basedata.service.IBaseCountrysService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 国家信息 前端控制器
 * </p>
 *
 * @author leiyaping
 * @since 2021-06-03
 */
@RestController
@RequestMapping("/serviceBasedata/baseCountrys")
public class BaseCountrysController {
    @Autowired
    private IBaseCountrysService iBaseCountrysService;

    @GetMapping("queryAll")
    @ApiOperation("查询全部")
    public ResultMap list(){
        List<BaseCountrys> countrysList=iBaseCountrysService.list();

        return ResultMap.getOKInstance().putData("country",countrysList);
    }
    @PostMapping("page/{current}/{size}")
    @ApiOperation("分页查询")
    public ResultMap page(
            @ApiParam(name="page",value = "当前页号",required = true)
            @PathVariable Integer current,
            @ApiParam(name="size",value = "页大小",required = true)
            @PathVariable Integer size){
        //分页参数
        Page<BaseCountrys> countrysPage=new Page<>(current,size);
        iBaseCountrysService.queryPage(countrysPage);
        return ResultMap.getOKInstance()
                .putData("rows",countrysPage.getRecords())
                .putData("total",countrysPage.getTotal())
                .putData("pages",countrysPage.getPages())
                .putData("current",countrysPage.getCurrent())
                .putData("size",countrysPage.getSize());
    }
    @GetMapping("queryById/{countryUuid}")
    @ApiOperation("根据id查询国家信息")
    public ResultMap queryById(
            @ApiParam(name="countryUuid",value = "国家id",required = true)
            @PathVariable String countryUuid){
        BaseCountrys baseCountrys=iBaseCountrysService.getById(countryUuid);
        return ResultMap.getOKInstance().putData("baseCountrys",baseCountrys);
    }
    @PostMapping("add")
    @ApiOperation("添加国家信息")
    public Result add(
            @ApiParam(name="baseCountrys",value="添加国家信息",required = true)
            @RequestBody BaseCountrys baseCountrys){

        boolean flag=iBaseCountrysService.save(baseCountrys);

        if(flag)
            return Result.getOKInstance();
        return Result.getErrorInstance();
    }

    @PostMapping("update")
    @ApiOperation("修改国家信息")
    public Result update(
            @ApiParam(name="baseCountrys",value = "国家对象",required = true)
            @RequestBody BaseCountrys baseCountrys){

        boolean flag=iBaseCountrysService.updateById(baseCountrys);

        if(flag)
            return Result.getOKInstance();
        return  Result.getErrorInstance();
    }

    @DeleteMapping("delete/{countryUuid}")
    @ApiOperation(value = "根据用户编号逻辑删除国家信息")
    public Result delete(
            @ApiParam(name = "countryUuid", value = "国家编号", required = true)
            @PathVariable String countryUuid) {
        boolean flag = iBaseCountrysService.removeById(countryUuid);
        if(flag)
            return Result.getOKInstance();
        return Result.getErrorInstance();
    }
}


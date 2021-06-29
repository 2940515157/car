package com.xmz.service.basedata.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.common.utils.Result;
import com.xmz.common.utils.ResultMap;
import com.xmz.service.basedata.entity.pojo.BaseCitys;
import com.xmz.service.basedata.service.IBaseCitysService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 城市 前端控制器
 * </p>
 *
 * @author leiyaping
 * @since 2021-06-03
 */
@RestController
@RequestMapping("/serviceBasedata/baseCitys")
public class BaseCitysController {
    @Autowired
    private IBaseCitysService iBaseCitysService;


    @PostMapping("page/{countryUuid}/{provUuid}/{current}/{size}")
    @ApiOperation("分页查询")
    public ResultMap page(
            @PathVariable String countryUuid,
            @PathVariable String provUuid,
            @ApiParam(name="page",value = "当前页号",required = true)
            @PathVariable Integer current,
            @ApiParam(name="size",value = "页大小",required = true)
            @PathVariable Integer size){
        //分页参数
        Page<BaseCitys> citysPage=new Page<>(current,size);
        iBaseCitysService.queryPage(citysPage,countryUuid,provUuid);
        return ResultMap.getOKInstance()
                .putData("rows",citysPage.getRecords())
                .putData("total",citysPage.getTotal())
                .putData("pages",citysPage.getPages())
                .putData("current",citysPage.getCurrent())
                .putData("size",citysPage.getSize());
    }
    @GetMapping("queryById/{countryUuid}/{provUuid}/{cityUuid}")
    @ApiOperation("根据id查询城市信息")
    public ResultMap queryById(
            @ApiParam(name="countryUuid",value = "国家id",required = true)
            @PathVariable String countryUuid,
            @ApiParam(name="provUuid",value = "省id",required = true)
            @PathVariable String provUuid,
            @ApiParam(name="cityUuid",value = "城市id",required = true)
            @PathVariable String cityUuid){
        BaseCitys baseCitys=iBaseCitysService.queryById(countryUuid,provUuid,cityUuid);
        return ResultMap.getOKInstance().putData("baseCitys",baseCitys);
    }
    @PostMapping("add")
    @ApiOperation("添加城市信息")
    public Result add(
            @ApiParam(name="baseCitys",value="添加城市信息",required = true)
            @RequestBody BaseCitys baseCitys){

        boolean flag=iBaseCitysService.save(baseCitys);

        if(flag)
            return Result.getOKInstance();
        return Result.getErrorInstance();
    }

    @PostMapping("update")
    @ApiOperation("修改城市信息")
    public Result update(
            @ApiParam(name="baseCitys",value = "城市对象",required = true)
            @RequestBody BaseCitys baseCitys){

        boolean flag=iBaseCitysService.updateById(baseCitys);

        if(flag)
            return Result.getOKInstance();
        return  Result.getErrorInstance();
    }

    @DeleteMapping("delete/{cityUuid}")
    @ApiOperation(value = "根据用户编号逻辑删除市信息")
    public Result delete(
            @ApiParam(name = "cityUuid", value = "城市编号", required = true)
            @PathVariable String cityUuid) {
        boolean flag = iBaseCitysService.removeById(cityUuid);
        if(flag)
            return Result.getOKInstance();
        return Result.getErrorInstance();
    }
}


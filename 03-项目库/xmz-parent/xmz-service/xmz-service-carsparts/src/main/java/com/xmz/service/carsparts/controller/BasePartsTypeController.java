package com.xmz.service.carsparts.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.common.utils.Result;
import com.xmz.common.utils.ResultMap;
import com.xmz.service.carsparts.entity.pojo.BasePartsType;
import com.xmz.service.carsparts.entity.vo.BasePartsTypeQuery;
import com.xmz.service.carsparts.service.IBasePartsTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 配件目录 前端控制器
 * </p>
 *
 * @author 江志威
 * @since 2021-05-31
 */
@Api("配件目录")
@RestController
@CrossOrigin
@RequestMapping("/serviceCarsparts/cartype")
public class BasePartsTypeController {

    @Autowired
    IBasePartsTypeService iBasePartsTypeService;

    /**
     * 获取自身子节点
     */
    @PostMapping("/carTypeZiTree")
    @ApiOperation("查询子节点")
    public ResultMap carTypeZiTree(){
        return ResultMap.getOKInstance().putData("rows",iBasePartsTypeService.carTypeZiTree(iBasePartsTypeService.carTypeFuTree()));
    }

    @ApiOperation("查询带条件")
    @PostMapping("query/{current}/{size}")
    public ResultMap queryCartype(
            @PathVariable Long current,
            @PathVariable Long size,
            @RequestBody BasePartsTypeQuery basePartsTypeQuery) {
        Page<BasePartsType> basePartsTypePage = new Page<>(current, size);

        iBasePartsTypeService.queryQuery(basePartsTypePage, basePartsTypeQuery);

        return ResultMap.getOKInstance()
                .putData("rows",basePartsTypePage.getRecords())
                .putData("pages",basePartsTypePage.getPages())
                .putData("total",basePartsTypePage.getTotal())
                .putData("current",basePartsTypePage.getCurrent())
                .putData("size",basePartsTypePage.getSize());

    }


    @PostMapping("/add")
    @ApiOperation("添加配件目录")
    public ResultMap add(
            @ApiParam(name = "basePartsType",value = "配件目录对象",required = true)
            @RequestBody BasePartsType basePartsType){
        boolean flag = iBasePartsTypeService.save(basePartsType);

        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }

    // 根据id查询
    @GetMapping("queryById/{partTypeUuid}")
    @ApiOperation("根据Id查询配件目录信息")
    public ResultMap queryById(
            @ApiParam(name = "partTypeUuid",value = "配件id",required = true)
            @PathVariable String partTypeUuid){

        BasePartsType basePartsType = iBasePartsTypeService.getById(partTypeUuid);

        return ResultMap.getOKInstance()
                .putData("basePartsType",basePartsType);
    }

    //修改
    @PostMapping("update")
    @ApiOperation("修改配件目录")
    public ResultMap update(
            @ApiParam(name = "basePartsType",value = "配件目录",required = true)
            @RequestBody BasePartsType basePartsType){

        boolean flag = iBasePartsTypeService.updateById(basePartsType);

        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }

    //删除
    @DeleteMapping("/delete/{id}")
    public Result<Object> delete(@PathVariable String id){
        if (iBasePartsTypeService.removeById(id))
            return Result.getOKInstance("删除成功");
        return Result.getErrorInstance("删除失败");
    }

}


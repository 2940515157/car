package com.xmz.service.basedata.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.common.utils.Result;
import com.xmz.common.utils.ResultMap;
import com.xmz.service.basedata.entity.pojo.BaseCarType;
import com.xmz.service.basedata.entity.vo.CarTypeQueryParam;
import com.xmz.service.basedata.service.IBaseCarTypeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 车型 前端控制器
 * </p>
 *
 * @author weikang
 * @since 2021-06-02
 */
@RestController
    @RequestMapping("/serviceBasedata/cartype")
public class BaseCarTypeController {
    @Autowired
    IBaseCarTypeService service;

    //分页查询
    @PostMapping("/queryPage/{current}/{size}")
    public ResultMap queryPage(@RequestBody CarTypeQueryParam queryParam,
                               @PathVariable Integer current,
                               @PathVariable Integer size){
        Page<BaseCarType> page=new Page(current,size);
        service.queryPage(page,queryParam);
        return ResultMap.getOKInstance()
                .putData("rows",page.getRecords())
                .putData("pages",page.getPages())
                .putData("total",page.getTotal())
                .putData("current",page.getCurrent())
                .putData("size",page.getSize())
                ;
    }
//删除
    @DeleteMapping("{brandUuid}")
    @ApiOperation("删除汽车")
    public ResultMap delete(
            @ApiParam(name = "brandUuid",value = "id",required = true)
            @PathVariable String brandUuid){

        boolean flag = service.removeById(brandUuid);

        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }

    //根据id查
    @GetMapping("/queryById/{id}")
    public Result<BaseCarType> queryById(@PathVariable String id){
        return Result.getOKInstance(service.getById(id));
    }


    //添加
    @PostMapping("addBaseCarType")
    @ApiOperation("添加")
    public ResultMap add(
            @ApiParam(name = "baseCarType",value = "车系",required = true)
            @RequestBody BaseCarType baseCarType){
        baseCarType.setVersion(new BigDecimal("1"));
        baseCarType.setCreatedDate(new Date());
        baseCarType.setCreatedBy("admin");
        boolean flag = service.save(baseCarType);
        if(flag)

            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }
    //修改
    @PostMapping("updateBaseCarType")
    @ApiOperation("修改车系")
    public ResultMap update(
            @ApiParam(name = "baseCarType",value = "车系",required = true)
            @RequestBody BaseCarType baseCarType){
        baseCarType.setUpdatedDate(new Date());
        baseCarType.setCreatedBy("admin");
        boolean flag = service.updateById(baseCarType);
        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }


    //数据回显
    // 根据id查询
    @GetMapping("queryById1/{carTypeUuid}")
    @ApiOperation("根据Id查询信息")
    public ResultMap queryById2(
            @ApiParam(name = "carTypeUuid",value = "车系id",required = true)
            @PathVariable String carTypeUuid){

        BaseCarType baseCarType = service.getById(carTypeUuid);

        return ResultMap.getOKInstance()
                .putData("baseCarType",baseCarType);
    }

    //根据条件查所有
    @PostMapping("/qeuryList")
    public ResultMap queryList(@RequestBody CarTypeQueryParam queryParam){
        return ResultMap.getOKInstance().putData("rows",service.queryList(queryParam));
    }

    //品牌下拉框
    @GetMapping("queryBname")
    @ApiOperation("查询品牌下拉框")
    public ResultMap bName(){
        List<BaseCarType> list = service.list2();
        return ResultMap.getOKInstance()
                .putData("list",list);
    }

    //车系下拉框
    @GetMapping("queryCategory")
    @ApiOperation("查询车系下拉框")
    public ResultMap QuerycarCategory(){
        List<BaseCarType> list=service.list3();
        return ResultMap.getOKInstance()
                .putData("list",list);
    }

    //年款下拉框
    @GetMapping("queryCarYear")
    @ApiOperation("查询年款下拉框")
    public ResultMap QuerycarYear(){
        List<BaseCarType> list=service.list4();
        return ResultMap.getOKInstance()
                .putData("list",list);
    }

    //排量下拉框
    @GetMapping("queryDisplacement")
    @ApiOperation("查询排量下拉框")
    public ResultMap QueryDisplacement(){
        List<BaseCarType> list=service.list5();
        return ResultMap.getOKInstance()
                .putData("list",list);
    }

    //厂家下拉框
    @GetMapping("queryFactory")
    @ApiOperation("查询厂家下拉框")
    public ResultMap QueryActory(){
        List<BaseCarType> list=service.list6();
        return ResultMap.getOKInstance()
                .putData("list",list);
    }


}


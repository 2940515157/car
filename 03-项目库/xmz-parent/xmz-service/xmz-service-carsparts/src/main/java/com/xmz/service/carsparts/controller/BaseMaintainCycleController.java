package com.xmz.service.carsparts.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.common.utils.Result;
import com.xmz.common.utils.ResultMap;
import com.xmz.service.carsparts.entity.pojo.BaseMaintainCycle;
import com.xmz.service.carsparts.entity.vo.CarMaintainCycle;
import com.xmz.service.carsparts.entity.vo.CarTypeQueryParam;
import com.xmz.service.carsparts.entity.vo.QueryParams;
import com.xmz.service.carsparts.service.BaseMaintainCycleService;
import com.xmz.service.carsparts.service.CarTypeFeignService;
import com.xmz.service.carsparts.service.DictFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 保养周期 前端控制器
 * </p>
 *
 * @author weikang
 * @since 2021-05-27
 */
@RestController //
@RequestMapping("/serviceCarsparts/base-maintain-cycle")
@CrossOrigin
public class BaseMaintainCycleController {

    @Autowired
    BaseMaintainCycleService service;

    @Autowired
    DictFeignService dictFeignService;

    @Autowired
    CarTypeFeignService carTypeFeignService;

    @PostMapping("/queryList/{current}/{size}")
    public ResultMap queryList(@RequestBody QueryParams queryParams,
                               @PathVariable Integer current,
                               @PathVariable Integer size) {
        Page<CarMaintainCycle> page = new Page<>(current, size);
        service.queryPage(queryParams,page);
        return ResultMap.getOKInstance().putData("rows", page.getRecords())
                .putData("current", page.getCurrent())
                .putData("pages", page.getPages())
                .putData("size", page.getSize())
                .putData("total", page.getTotal());
    }
    @GetMapping("/queryById/{mcUuid}")
    public Result<BaseMaintainCycle> queryById(@PathVariable String mcUuid){
        return Result.getOKInstance(service.getById(mcUuid));
    }
    //增加
    @PostMapping("/add")
    public Result add(@RequestBody BaseMaintainCycle baseMaintainCycle){
        baseMaintainCycle.setVersion(new BigDecimal(1.0));
        if (service.save(baseMaintainCycle))
            return Result.getOKInstance("添加成功");
        return Result.getErrorInstance("添加失败");
    }

    //修改
    @PostMapping("/update")
    public Result<Object> update(@RequestBody BaseMaintainCycle baseMaintainCycle){
        if (service.updateById(baseMaintainCycle))
            return Result.getOKInstance("添加成功");
        return Result.getErrorInstance("添加失败");
    }

    //删除
    @DeleteMapping("/delete/{mcid}")
    public Result<Object> delete(@PathVariable String mcid){
        if (service.removeById(mcid))
            return Result.getOKInstance("添加成功");
        return Result.getErrorInstance("添加失败");
    }

    //搜索下拉框的数据
    @PostMapping("/select")
    public ResultMap select(@RequestBody QueryParams queryParams){
        return ResultMap.getOKInstance().putData("rows", service.querySelect(queryParams));
    }
    //查所有保养类型
    @GetMapping("/queryDict/{dicttypeid}")
    public Result<List> queryTypeByIdList(@PathVariable String dicttypeid){
        return dictFeignService.queryTypeByIdList(dicttypeid);
    }

    //查所有车辆信息
    @PostMapping("carList/{current}/{size}")
    public ResultMap carList(@RequestBody CarTypeQueryParam queryParam,
                             @PathVariable Integer current,
                             @PathVariable Integer size){
        return carTypeFeignService.queryPage(queryParam,current,size);
    }

    //根据条件查所有
    @PostMapping("/qeuryList")
    public ResultMap queryList(@RequestBody CarTypeQueryParam queryParam){
        return carTypeFeignService.queryList(queryParam);
    }


}


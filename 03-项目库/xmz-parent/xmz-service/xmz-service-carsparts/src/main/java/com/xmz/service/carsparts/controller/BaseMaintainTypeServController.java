package com.xmz.service.carsparts.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.common.utils.Result;
import com.xmz.common.utils.ResultMap;
import com.xmz.service.carsparts.entity.pojo.BaseMaintainTypeServ;
import com.xmz.service.carsparts.entity.vo.MaintainTypeServCatalogBack;
import com.xmz.service.carsparts.entity.vo.QueryBMTS;
import com.xmz.service.carsparts.service.DictFeignService;
import com.xmz.service.carsparts.service.IBaseMaintainTypeServService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 立洋保养类型与服务项目的对照表 前端控制器
 * </p>
 *
 * @author leiyaping
 * @since 2021-06-10
 */
@CrossOrigin
@RestController
@RequestMapping("/serviceCarsparts/base-maintain-type-serv")
public class BaseMaintainTypeServController {
    @Autowired
    private IBaseMaintainTypeServService iBaseMaintainTypeServService;
    @Autowired
    private DictFeignService dictFeignService;
    //分页查询
    @PostMapping("/queryList/{current}/{size}")
    public String queryList(
            @RequestBody QueryBMTS queryBMTS,
            @PathVariable Integer current,
            @PathVariable Integer size){
        Page<MaintainTypeServCatalogBack> page=new Page<>(current,size);
        iBaseMaintainTypeServService.queryPage(page,queryBMTS);
        return JSONObject.toJSONString(ResultMap.getOKInstance().putData("rows",page.getRecords())
                .putData("current", page.getCurrent())
                .putData("pages", page.getPages())
                .putData("size", page.getSize())
                .putData("total", page.getTotal()));
    }
    //根据id查询
    @GetMapping("/queryById/{mtsUuid}")
    public Result<BaseMaintainTypeServ> queryById(@PathVariable String mtsUuid){
        return Result.getOKInstance(iBaseMaintainTypeServService.getById(mtsUuid));
    }
    //添加
    @PostMapping("/add")
    public Result add(@RequestBody MaintainTypeServCatalogBack maintainTypeServCatalogBack) {
        if (iBaseMaintainTypeServService.add(maintainTypeServCatalogBack)>0)
            return Result.getOKInstance("添加成功");
        return Result.getErrorInstance("添加失败");
    }
    //修改
    @PostMapping("/update")
    public Result update(@RequestBody MaintainTypeServCatalogBack maintainTypeServCatalogBack){
        if (iBaseMaintainTypeServService.update(maintainTypeServCatalogBack)>0)
            return Result.getOKInstance("修改成功");
        return Result.getErrorInstance("修改失败");
    }
    //删除
    @DeleteMapping("/delete/{mtsUuid}")
    public Result delete(@PathVariable String mtsUuid){
        if(iBaseMaintainTypeServService.removeById(mtsUuid))
            return Result.getOKInstance("删除成功");
        return Result.getErrorInstance("删除失败");
    }
    //搜索下拉框的数据
    @PostMapping("/select")
    public ResultMap select(@RequestBody QueryBMTS queryBMTS){
        return ResultMap.getOKInstance().putData("rows", iBaseMaintainTypeServService.queryselect(queryBMTS));
    }
    //查所有保养类型
    @GetMapping("/queryDict/{dicttypeid}")
    public Result<List> queryTypeByIdList(@PathVariable String dicttypeid){
        return dictFeignService.queryTypeByIdList(dicttypeid);
    }
}


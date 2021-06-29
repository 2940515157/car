package com.xmz.br.storages.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.br.storages.entity.pojo.WmWarehouse;
import com.xmz.br.storages.entity.vo.WmWarehouseQuery;
import com.xmz.br.storages.service.IWmWarehouseService;
import com.xmz.common.utils.ResultMap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 仓库 前端控制器
 * </p>
 *
 * @author hwb
 * @since 2021-06-16
 */
@RestController
@RequestMapping("/brStorages/wm-warehouse")
@CrossOrigin
@Api(description = "仓库管理")
public class WmWarehouseController {

    @Autowired
    private IWmWarehouseService iWmWarehouseService;

    //分页查询仓库信息
    @PostMapping("page/{current}/{size}")
    @ApiOperation("分页查询")
    public ResultMap myPage(
            @PathVariable Long current,
            @PathVariable Long size,
            @RequestBody WmWarehouseQuery wmWarehouseQuery) {
        System.out.println(current+"---"+size);
        Page<WmWarehouse> wmWarehousePage = new Page<>(current, size);
        iWmWarehouseService.pageQuery(wmWarehousePage,wmWarehouseQuery);
        return ResultMap.getOKInstance()
                .putData("rows",wmWarehousePage.getRecords())
                .putData("pages",wmWarehousePage.getPages())
                .putData("total",wmWarehousePage.getTotal())
                .putData("current",wmWarehousePage.getCurrent())
                .putData("size",wmWarehousePage.getSize());
    }

    //添加仓库

    @PostMapping("add")
    @ApiOperation("新增仓库信息")
    public ResultMap add(
            @ApiParam(name = "wmWarehouse",value = "仓库对象",required = true)
            @RequestBody WmWarehouse wmWarehouse){
        boolean flag = iWmWarehouseService.save(wmWarehouse);

        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }

    //删除仓库
    @DeleteMapping("delete/{wmWarehouseUuid}")
    @ApiOperation("删除仓库信息")
    public ResultMap delete(
            @ApiParam(name = "wmWarehouseUuid",value = "仓库id",required = true)
            @PathVariable String wmWarehouseUuid){
        boolean flag = iWmWarehouseService.removeById(wmWarehouseUuid);

        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }

    //修改仓库
    @PostMapping("update")
    @ApiOperation("修改仓库信息")
    public ResultMap update(
            @ApiParam(name = "wmWarehouse",value = "仓库对象",required = true)
            @RequestBody WmWarehouse wmWarehouse){
        boolean flag = iWmWarehouseService.updateById(wmWarehouse);

        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }

    //根据ID查询
    @GetMapping("queryById/{wmWarehouseUuid}")
    @ApiOperation("根据Id查询仓库信息")
    public ResultMap queryById(
            @ApiParam(name = "wmWarehouseUuid",value = "仓库id",required = true)
            @PathVariable String wmWarehouseUuid){

        WmWarehouse warehouse = iWmWarehouseService.getById(wmWarehouseUuid);

        return ResultMap.getOKInstance()
                .putData("warehouse",warehouse);

    }
}


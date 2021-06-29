package com.xmz.br.storages.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.br.storages.entity.pojo.EosDictEntry;
import com.xmz.br.storages.entity.pojo.WmWarehouse;
import com.xmz.br.storages.entity.pojo.WmWarehouseEnter;
import com.xmz.br.storages.entity.vo.WWhouseEnterQuery;
import com.xmz.br.storages.entity.vo.WmWarehouseQuery;
import com.xmz.br.storages.service.IWmWarehouseEnterService;
import com.xmz.common.utils.ResultMap;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 入库单 前端控制器
 * </p>
 *
 * @author hwb
 * @since 2021-06-16
 */
@RestController
@RequestMapping("/brStorages/wm-warehouse-enter")
public class WmWarehouseEnterController {
    @Autowired
    private IWmWarehouseEnterService iWmWarehouseEnterService;
    //分页查询入库单信息
    @PostMapping("page/{current}/{size}")
    public ResultMap myPage(
            @PathVariable Long current,
            @PathVariable Long size,
            @RequestBody WWhouseEnterQuery wWhouseEnterQuery) {
        System.out.println(current+"---"+size+"---"+ wWhouseEnterQuery);
        Page<WmWarehouseEnter> wmWarehouseEnterPage = new Page<>(current, size);
        iWmWarehouseEnterService.myPage(wmWarehouseEnterPage, wWhouseEnterQuery);
        return ResultMap.getOKInstance()
                .putData("rows",wmWarehouseEnterPage.getRecords())
                .putData("pages",wmWarehouseEnterPage.getPages())
                .putData("total",wmWarehouseEnterPage.getTotal())
                .putData("current",wmWarehouseEnterPage.getCurrent())
                .putData("size",wmWarehouseEnterPage.getSize());
    }
    //下拉框拿仓库
    @ApiOperation(value = "查询全部仓库")
    @GetMapping("queryall")
    public ResultMap queryall(){
        List<WmWarehouse> list=iWmWarehouseEnterService.listAll();
        return ResultMap.getOKInstance().putData("list",list);
    }

    //下拉框拿入库类型
    @ApiOperation(value = "查询全部入库类型")
    @GetMapping("listAllwwetype")
    public ResultMap listAllwwetype(){
        List<EosDictEntry> listAllwwetype=iWmWarehouseEnterService.listAllwwetype();
        return ResultMap.getOKInstance().putData("listAllwwetype",listAllwwetype);
    }

    //下拉框拿来源类型
    @ApiOperation(value = "查询全部来源类型")
    @GetMapping("listAllfromtype")
    public ResultMap listAllfromtype(){
        List<EosDictEntry> listAllfromtype=iWmWarehouseEnterService.listAllfromtype();
        return ResultMap.getOKInstance().putData("listAllfromtype",listAllfromtype);
    }
}


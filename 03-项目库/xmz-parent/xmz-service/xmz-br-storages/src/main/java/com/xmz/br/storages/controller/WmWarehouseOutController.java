package com.xmz.br.storages.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.br.storages.entity.pojo.EosDictEntry;
import com.xmz.br.storages.entity.pojo.WmWarehouseOut;
import com.xmz.br.storages.entity.vo.WWhouseOutQuery;
import com.xmz.br.storages.service.IWmWarehouseOutService;
import com.xmz.common.utils.ResultMap;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 出库单 前端控制器
 * </p>
 *
 * @author hwb
 * @since 2021-06-19
 */
@RestController
@RequestMapping("/brStorages/wm-warehouse-Out")
public class WmWarehouseOutController {
    @Autowired
        private IWmWarehouseOutService iWmWarehouseOutService;
    //分页查询出库单信息
    @PostMapping("page/{current}/{size}")
    public ResultMap myPage(
            @PathVariable Long current,
            @PathVariable Long size,
            @RequestBody WWhouseOutQuery wWhouseOutQuery) {
        System.out.println(current+"---"+size+"---"+ wWhouseOutQuery);
        Page<WmWarehouseOut> wmWarehouseOutPage = new Page<>(current, size);
        iWmWarehouseOutService.myPage(wmWarehouseOutPage, wWhouseOutQuery);
        return ResultMap.getOKInstance()
                .putData("rows",wmWarehouseOutPage.getRecords())
                .putData("pages",wmWarehouseOutPage.getPages())
                .putData("total",wmWarehouseOutPage.getTotal())
                .putData("current",wmWarehouseOutPage.getCurrent())
                .putData("size",wmWarehouseOutPage.getSize());
    }
    //下拉框拿入库类型
    @ApiOperation(value = "查询全部入库类型")
    @GetMapping("listAllwwotype")
    public ResultMap listAllwwetype(){
        List<EosDictEntry> listAllwwotype=iWmWarehouseOutService.listAllwwetype();
        return ResultMap.getOKInstance().putData("listAllwwotype",listAllwwotype);
    }

    //下拉框拿来源类型
    @ApiOperation(value = "查询全部来源类型")
    @GetMapping("listAllfromtype")
    public ResultMap listAllfromtype(){
        List<EosDictEntry> listAllfromtype=iWmWarehouseOutService.listAllfromtype();
        return ResultMap.getOKInstance().putData("listAllfromtype",listAllfromtype);
    }
}


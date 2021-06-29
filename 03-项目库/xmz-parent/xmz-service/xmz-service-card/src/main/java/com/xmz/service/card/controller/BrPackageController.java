package com.xmz.service.card.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.common.utils.ResultMap;
import com.xmz.service.card.entity.pojo.BrPackage;
import com.xmz.service.card.entity.vo.BrPackageQuery;
import com.xmz.service.card.entity.vo.MyBrPackage;
import com.xmz.service.card.service.IBrPackageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * <p>
 * 门店套餐 前端控制器
 * </p>
 *
 * @author liujiaxi,
 * @since 2021-05-28
 */
@Api("门店套餐情况")
@RestController
@CrossOrigin
@RequestMapping("/serviceCard/brpackage")
public class BrPackageController {

    @Autowired
    private IBrPackageService iBrPackageService;

    /**
     * 门店套餐情况 分页多表查询方法
     * @param current
     * @param size
     * @param brPackageQuery
     * @return
     */
    @ApiOperation("查询全部")
    @PostMapping("queryAll/{current}/{size}")
    public ResultMap queryAllBrPack(
            @PathVariable Long current,
            @PathVariable Long size,
            @RequestBody BrPackageQuery brPackageQuery) {
        Page<MyBrPackage> brPackagePage = new Page<>(current, size);

        iBrPackageService.queryAllBrPack(brPackagePage, brPackageQuery);

        return ResultMap.getOKInstance()
                .putData("rows",brPackagePage.getRecords())
                .putData("pages",brPackagePage.getPages())
                .putData("total",brPackagePage.getTotal())
                .putData("current",brPackagePage.getCurrent())
                .putData("size",brPackagePage.getSize());
    }

    @ApiOperation("通过编号查询门店套餐")
    @GetMapping("/getByBrPackageUuid/{brPackageUuid}")
    public ResultMap getByBrPackageUuid(
            @ApiParam(name = "brPackageUuid",value = "编号")
            @PathVariable String brPackageUuid) {
        BrPackage brPackage = iBrPackageService.getByBrPackageUuid(brPackageUuid);

        return ResultMap.getOKInstance().putData("brPackage",brPackage);
    }

    @ApiOperation("修改上架")
    @PostMapping("/update/up")
    public ResultMap updateUp(
            @ApiParam(name = "brPackage",value = "修改条件")
            @RequestBody BrPackage brPackage) {
        BrPackage brPackage1=new BrPackage();
        brPackage1.setBrPackageUuid(brPackage.getBrPackageUuid());
        brPackage1.setStatus("up");
        brPackage1.setPutOnDate(new Date());
        boolean flag = iBrPackageService.updateById(brPackage1);
        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }

    @ApiOperation("修改下架")
    @PostMapping("/updates/under")
    public ResultMap updateUnder(
            @ApiParam(name = "brPackage",value = "修改条件")
            @RequestBody BrPackage brPackage) {
        BrPackage brPackage1=new BrPackage();

        brPackage1.setBrPackageUuid(brPackage.getBrPackageUuid());
        brPackage1.setStatus("under");
        brPackage1.setPullOffDate(new Date());
        boolean flag = iBrPackageService.updateById(brPackage1);
        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }

}


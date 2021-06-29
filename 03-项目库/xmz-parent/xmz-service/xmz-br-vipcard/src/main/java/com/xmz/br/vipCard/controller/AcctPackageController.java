package com.xmz.br.vipCard.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.br.vipCard.entity.pojo.*;
import com.xmz.br.vipCard.entity.vo.AcctBrCardQuery;
import com.xmz.br.vipCard.entity.vo.AcctCardSetupQuery;
import com.xmz.br.vipCard.entity.vo.AcctPackageQuery;
import com.xmz.br.vipCard.service.IAcctPackageService;
import com.xmz.br.vipCard.service.IBrPackageService;
import com.xmz.common.utils.ResultMap;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 套餐 前端控制器
 * </p>
 *
 * @author zt
 * @since 2021-06-11
 */
@RestController
@RequestMapping("/brVipcard/acct-package")
public class AcctPackageController {
    @Autowired
    IAcctPackageService iAcctPackageService;

    @Autowired
    IBrPackageService iBrPackageService;

    @ApiOperation("获取发行范围内的套餐")
    @PostMapping("/queryPage/{current}/{size}")
    public ResultMap queryPage( @RequestBody AcctPackageQuery acctPackageQuery,
                                @PathVariable Integer current,
                                @PathVariable Integer size){
        Page<AcctPackage> page=new Page(current,size);
        iAcctPackageService.myPage(page,acctPackageQuery);
        return ResultMap.getOKInstance()
                .putData("rows",page.getRecords())
                .putData("pages",page.getPages())
                .putData("total",page.getTotal())
                .putData("current",page.getCurrent())
                .putData("size",page.getSize());
    }


    @ApiOperation("发起类型 门店：套餐上架")
    @PostMapping("/apShangjia/{apUuid}/{br_package_uuid}")
    public ResultMap apShangjia(@PathVariable String apUuid,@PathVariable String br_package_uuid) {
        AcctPackage acctPackage=new AcctPackage();
        acctPackage.setApUuid(apUuid);
        acctPackage.setStatus("up");
        iAcctPackageService.updateById(acctPackage);

        BrPackage brPackage=new BrPackage();
        brPackage.setBrPackageUuid(br_package_uuid);
        brPackage.setStatus("up");
        brPackage.setPutOnDate(new Date());
        brPackage.setPullOffDate(null);
        iBrPackageService.updateById(brPackage);

        return ResultMap.getOKInstance();
    }

    @ApiOperation("发起类型 门店：套餐下架")
    @PostMapping("/apxiajia/{apUuid}/{br_package_uuid}")
    public ResultMap apxiajia(@PathVariable String apUuid,@PathVariable String br_package_uuid) {
        AcctPackage acctPackage=new AcctPackage();
        acctPackage.setApUuid(apUuid);
        acctPackage.setStatus("under");
        iAcctPackageService.updateById(acctPackage);

        BrPackage brPackage=new BrPackage();
        brPackage.setBrPackageUuid(br_package_uuid);
        brPackage.setStatus("under");
        brPackage.setPutOnDate(null);
        brPackage.setPullOffDate(new Date());
        iBrPackageService.updateById(brPackage);

        return ResultMap.getOKInstance();
    }

    @ApiOperation("发起类型 总部：套餐下架")
    @PostMapping("/zbapxiajia/{br_package_uuid}")
    public ResultMap zbapxiajia(@PathVariable String br_package_uuid) {

        BrPackage brPackage=new BrPackage();
        brPackage.setBrPackageUuid(br_package_uuid);
        brPackage.setStatus("under");
        brPackage.setPutOnDate(null);
        brPackage.setPullOffDate(new Date());
        iBrPackageService.updateById(brPackage);

        return ResultMap.getOKInstance();
    }

    @ApiOperation("发起类型 总部：套餐上架")
    @PostMapping("/zbapshangjia/{br_package_uuid}")
    public ResultMap zbapshangjia(@PathVariable String br_package_uuid) {

        BrPackage brPackage=new BrPackage();
        brPackage.setBrPackageUuid(br_package_uuid);
        brPackage.setStatus("up");
        brPackage.setPutOnDate(new Date());
        brPackage.setPullOffDate(null);
        iBrPackageService.updateById(brPackage);

        return ResultMap.getOKInstance();
    }

}


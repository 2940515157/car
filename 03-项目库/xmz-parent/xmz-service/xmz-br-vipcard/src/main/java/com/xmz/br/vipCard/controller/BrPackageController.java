package com.xmz.br.vipCard.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.br.vipCard.entity.pojo.MyAcctBrCard;
import com.xmz.br.vipCard.entity.pojo.MyAcctCardSetup;
import com.xmz.br.vipCard.entity.pojo.MyBrPackage;
import com.xmz.br.vipCard.entity.vo.AcctBrCardQuery;
import com.xmz.br.vipCard.entity.vo.AcctCardSetupQuery;
import com.xmz.br.vipCard.entity.vo.BrPackageQuery;
import com.xmz.br.vipCard.service.IBrPackageService;
import com.xmz.common.utils.ResultMap;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 门店套餐 前端控制器
 * </p>
 *
 * @author zt
 * @since 2021-06-11
 */
@RestController
@RequestMapping("/brVipcard/br-package")
public class BrPackageController {
    @Autowired
    IBrPackageService iBrPackageService;

    @ApiOperation("获取门店套餐列表(条件分页)")
    @PostMapping("/queryPage/{current}/{size}")
    public ResultMap queryPage(@RequestBody BrPackageQuery brPackageQuery,
                               @PathVariable Integer current,
                               @PathVariable Integer size){
        Page<MyBrPackage> page=new Page(current,size);
        iBrPackageService.myPage(page,brPackageQuery);
        return ResultMap.getOKInstance()
                .putData("rows",page.getRecords())
                .putData("pages",page.getPages())
                .putData("total",page.getTotal())
                .putData("current",page.getCurrent())
                .putData("size",page.getSize());
    }


    @ApiOperation("获取所有门店已上架且在有效期内的套餐")
    @PostMapping("/listAp")
    public ResultMap listAp(@RequestBody BrPackageQuery brPackageQuery){
        List<MyBrPackage> list= iBrPackageService.listQuery(brPackageQuery);
        return ResultMap.getOKInstance()
                .putData("list",list);

    }

    @ApiOperation("增加套餐")
    @PostMapping("/add")
    public ResultMap add(@RequestBody Map<String,String> data) {
        boolean flag  =iBrPackageService.add(data);
        if(flag) return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }

}


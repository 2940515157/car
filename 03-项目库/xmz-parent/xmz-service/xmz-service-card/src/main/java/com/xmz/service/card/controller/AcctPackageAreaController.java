package com.xmz.service.card.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.common.utils.ResultMap;
import com.xmz.service.card.entity.pojo.AcctCardArea;
import com.xmz.service.card.entity.pojo.AcctPackageArea;
import com.xmz.service.card.entity.vo.MyAcctCardArea;
import com.xmz.service.card.entity.vo.MyAcctPackageArea;
import com.xmz.service.card.service.IAcctPackageAreaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 套餐使用范围 前端控制器
 * </p>
 *
 * @author liujiaxi
 * @since 2021-06-21
 */
@Api("套餐产品使用范围")
@RestController
@CrossOrigin
@RequestMapping("/serviceCard/acct-package-area")
public class AcctPackageAreaController {
    @Autowired
    IAcctPackageAreaService iAcctPackageAreaService;

    @ApiOperation("分页条件查询卡与区域的关系")
    @PostMapping("queryAllPage/{current}/{size}/{apUuid}")
    public ResultMap queryAllPage(@PathVariable Long current,
                                  @PathVariable Long size,
                                  @PathVariable String apUuid){
        System.out.println("---------");
        System.out.println(apUuid);
        Page<MyAcctPackageArea> brPackagePage = new Page<>(current, size);

        iAcctPackageAreaService.queryAllPage(brPackagePage,apUuid);
        return ResultMap.getOKInstance()
                .putData("rows",brPackagePage.getRecords())
                .putData("pages",brPackagePage.getPages())
                .putData("total",brPackagePage.getTotal())
                .putData("current",brPackagePage.getCurrent())
                .putData("size",brPackagePage.getSize());
    }

    //添加
    @ApiOperation("增加选择范围")
    @PostMapping("/add")
    public ResultMap add(@RequestBody AcctPackageArea acctPackageArea){

        boolean flag=iAcctPackageAreaService.save(acctPackageArea);
        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }

    //删除
    @ApiOperation("删除卡与区域的关系")
    @DeleteMapping("/delete/{id}")
    public ResultMap delete(@PathVariable String id){
        boolean flag=iAcctPackageAreaService.removeById(id);
        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }



}


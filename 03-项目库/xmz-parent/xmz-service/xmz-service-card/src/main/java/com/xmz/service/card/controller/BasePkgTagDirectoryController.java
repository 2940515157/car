package com.xmz.service.card.controller;



import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.common.utils.ResultMap;
import com.xmz.service.card.config.PinYinUtil;
import com.xmz.service.card.entity.pojo.BasePkgTagDirectory;
import com.xmz.service.card.entity.vo.BasePkgTagDirectoryQuery;

import com.xmz.service.card.service.IBasePkgTagDirectoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 * 套餐标签目录 前端控制器
 * </p>
 *
 * @author liujiaxi
 * @since 2021-05-28
 */
@Api("套餐标签情况")
@RestController
@CrossOrigin
@RequestMapping("/serviceCard/base-pkg-tag-directory")
public class BasePkgTagDirectoryController {
    @Autowired
    IBasePkgTagDirectoryService iBasePkgTagDirectoryService;

    /**
     * 查询左边菜单
     * @return
     */
    @GetMapping
    @ApiOperation("查询左边菜单套餐标签情况")
    public ResultMap findAllTaoCanBiaoQian(){
        List<BasePkgTagDirectory> list=iBasePkgTagDirectoryService.findAllTaoCanBiaoQian();
        return ResultMap.getOKInstance()
                .putData("rows",list);
    }


    @ApiOperation("查询右边菜单套餐标签情况")
    @PostMapping("menuRight/{current}/{size}")
    public ResultMap getMenuRight( @PathVariable Long current,
                                   @PathVariable Long size,
                                   @RequestBody BasePkgTagDirectoryQuery basePkgTagDirectoryQuery){
        Page<BasePkgTagDirectory> brPackagePage = new Page<>(current, size);
        System.out.println("这是uuid"+basePkgTagDirectoryQuery.getBptdUuid());
        iBasePkgTagDirectoryService.getMenuRight(brPackagePage,basePkgTagDirectoryQuery);
        return ResultMap.getOKInstance()
                .putData("rows",brPackagePage.getRecords())
                .putData("pages",brPackagePage.getPages())
                .putData("total",brPackagePage.getTotal())
                .putData("current",brPackagePage.getCurrent())
                .putData("size",brPackagePage.getSize());
    }


    @ApiOperation("删除套餐标签")
    @DeleteMapping("/delete/{id}")
    public ResultMap delete(@PathVariable String id){
        boolean flag=iBasePkgTagDirectoryService.removeById(id);
        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }


    @ApiOperation("增加套餐标签")
    @PostMapping("/add")
    public ResultMap add(@RequestBody BasePkgTagDirectory basePkgTagDirectory){

        //拼音
        basePkgTagDirectory.setPinyin(PinYinUtil.toPinyin(basePkgTagDirectory.getLabel()));
        //是否有效
        basePkgTagDirectory.setIsActive("Y");

        boolean flag=iBasePkgTagDirectoryService.save(basePkgTagDirectory);
        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }

    @ApiOperation("增加套餐分类")
    @PostMapping("/addf")
    public ResultMap addf(@RequestBody BasePkgTagDirectory basePkgTagDirectory){

        //拼音
        basePkgTagDirectory.setPinyin(PinYinUtil.toPinyin(basePkgTagDirectory.getLabel()));
        //是否有效
        basePkgTagDirectory.setIsActive("Y");

        boolean flag=iBasePkgTagDirectoryService.save(basePkgTagDirectory);
        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }


    @ApiOperation("修改套餐标签")
    @PostMapping("/update")
    public ResultMap update(@RequestBody BasePkgTagDirectory basePkgTagDirectory){
        //拼音
        basePkgTagDirectory.setPinyin(PinYinUtil.toPinyin(basePkgTagDirectory.getLabel()));
        //是否有效
        basePkgTagDirectory.setIsActive("Y");
        boolean flag=iBasePkgTagDirectoryService.updateById(basePkgTagDirectory);

        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }


    @ApiOperation("根据id查询套餐")
    @PostMapping("/queryById/{acsUuid}")
    public ResultMap queryById(@PathVariable String acsUuid) {

        BasePkgTagDirectory basePkgTagDirectory=iBasePkgTagDirectoryService.getById(acsUuid);
        return ResultMap.getOKInstance()
                .putData("basePkgTagDirectory",basePkgTagDirectory);
    }

}


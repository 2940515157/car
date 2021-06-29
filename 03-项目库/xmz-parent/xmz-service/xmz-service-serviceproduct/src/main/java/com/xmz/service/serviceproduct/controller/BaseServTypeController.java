package com.xmz.service.serviceproduct.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.common.utils.ResultMap;
import com.xmz.service.serviceproduct.entity.pojo.BaseServType;
import com.xmz.service.serviceproduct.entity.pojo.ServCheckupDirectory;
import com.xmz.service.serviceproduct.entity.vo.ServCheckQuery;
import com.xmz.service.serviceproduct.entity.vo.ServTypeQuery;
import com.xmz.service.serviceproduct.service.IBaseServTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 服务类型 前端控制器
 * </p>
 *
 * @author hwb
 * @since 2021-06-03
 */
@RestController
@CrossOrigin
@RequestMapping("/serviceServiceProduct/base-serv-type")
@Api("服务目录")
public class BaseServTypeController {
    @Autowired
    private IBaseServTypeService iBaseServTypeService;

    //添加
    @PostMapping("add")
    @ApiOperation("添加服务目录")
    public ResultMap add(
            @ApiParam(name = "BaseServType",value = "服务目录",required = true)
            @RequestBody BaseServType baseServType){

        boolean flag = iBaseServTypeService.save(baseServType);

        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }

    //删除
    @DeleteMapping("{stUuid}")
    @ApiOperation("删除服务目录")
    public ResultMap delete(
            @ApiParam(name = "stUuid",value = "服务目录id",required = true)
            @PathVariable String stUuid){

        boolean flag = iBaseServTypeService.removeById(stUuid);

        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }


    //修改
    @PostMapping("update")
    @ApiOperation("修改服务目录")
    public ResultMap update(
            @ApiParam(name = "baseServType",value = "服务目录",required = true)
            @RequestBody BaseServType baseServType){

        boolean flag = iBaseServTypeService.updateById(baseServType);

        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }

    //分页查询服务信息
    @PostMapping("page/{current}/{size}")
    public ResultMap myPage(
            @PathVariable Long current,
            @PathVariable Long size,
            @RequestBody ServTypeQuery servTypeQuery) {
        System.out.println(current+"---"+size+"---"+ servTypeQuery);
        Page<BaseServType> baseServTypePage = new Page<>(current, size);
        iBaseServTypeService.myPage(baseServTypePage, servTypeQuery);
        return ResultMap.getOKInstance()
                .putData("rows",baseServTypePage.getRecords())
                .putData("pages",baseServTypePage.getPages())
                .putData("total",baseServTypePage.getTotal())
                .putData("current",baseServTypePage.getCurrent())
                .putData("size",baseServTypePage.getSize());
    }

    // 根据id查询
    @GetMapping("queryById/{stUuid}")
    @ApiOperation("根据Id查询服务信息")
    public ResultMap queryById(
            @ApiParam(name = "scdUuid",value = "服务id",required = true)
            @PathVariable String stUuid){

        BaseServType baseServType = iBaseServTypeService.getById(stUuid);

        return ResultMap.getOKInstance()
                .putData("baseServType",baseServType);
    }


    /**
     * 查询左边菜单
     * @return
     */
    @GetMapping
    @ApiOperation("查询左边菜单套餐标签情况")
    public ResultMap findAllTaoCanBiaoQian(){
        List<BaseServType> list=iBaseServTypeService.findAllBase();
        return ResultMap.getOKInstance()
                .putData("rows",list);
    }







}


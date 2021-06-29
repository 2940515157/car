package com.xmz.service.serviceproduct.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xmz.common.utils.ResultMap;
import com.xmz.service.serviceproduct.entity.pojo.*;
import com.xmz.service.serviceproduct.entity.vo.ServCheckupCatalogQuery;
import com.xmz.service.serviceproduct.entity.vo.ServCheckupPkgQuery;
import com.xmz.service.serviceproduct.service.IServCheckupPkgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 点检包 前端控制器
 * </p>
 *
 * @author zt
 * @since 2021-05-31
 */
@RestController
@RequestMapping("/serviceServiceProduct/serv-checkup-pkg")
@CrossOrigin
public class ServCheckupPkgController {
    @Autowired
    IServCheckupPkgService iServCheckupPkgService;


    @ApiOperation("获取点检包列表(条件分页)")
    @PostMapping("/queryPage/{current}/{size}")
    public ResultMap queryPage(@RequestBody ServCheckupPkgQuery servCheckupPkgQuery,
                               @PathVariable Integer current,
                               @PathVariable Integer size){
        Page<ServCheckupPkg> page=new Page(current,size);
        iServCheckupPkgService.myPage(page,servCheckupPkgQuery);
        return ResultMap.getOKInstance()
                .putData("rows",page.getRecords())
                .putData("pages",page.getPages())
                .putData("total",page.getTotal())
                .putData("current",page.getCurrent())
                .putData("size",page.getSize());
    }


    @ApiOperation("根据点检包名称查询信息")
    @PostMapping("/query/{pkgName}")
    public ResultMap getById(@PathVariable String pkgName) {

        List<MyServCheckupPkg> myServCheckupPkgList = iServCheckupPkgService.queryPkgName(pkgName);

        System.out.println(myServCheckupPkgList);
        return ResultMap.getOKInstance()
                .putData("myServCheckupPkgList", myServCheckupPkgList);
    }


    @ApiOperation("添加点检包")
    @PostMapping("/add")
    public ResultMap add(@RequestBody Map<String,String> data) {

      boolean flag=  iServCheckupPkgService.add(data);
        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }

    @ApiOperation("根据id查询点检包")
    @GetMapping("/queryById/{id}")
    public ResultMap queryById(@PathVariable String id) {

       ServCheckupPkg servCheckupPkg=  iServCheckupPkgService.queryById(id);

            return ResultMap.getOKInstance()
                    .putData("servCheckupPkg",servCheckupPkg);
    }


    @ApiOperation("编辑点检包")
    @PostMapping("/update")
    public ResultMap update(@RequestBody Map<String,String> data) {

        boolean flag=  iServCheckupPkgService.update(data);
        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }


    @ApiOperation("上架/下架")
    @PostMapping("/updateStatus")
    public ResultMap updateStatus(@RequestBody ServCheckupPkg servCheckupPkg) {
       iServCheckupPkgService.updateStatus(servCheckupPkg);
            return ResultMap.getOKInstance();
    }


}


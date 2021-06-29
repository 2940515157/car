package com.xmz.service.serviceproduct.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.common.utils.ResultMap;
import com.xmz.service.serviceproduct.config.PinYinUtil;
import com.xmz.service.serviceproduct.entity.pojo.MyServCheckupCatalog;
import com.xmz.service.serviceproduct.entity.pojo.ServCheckupCatalog;
import com.xmz.service.serviceproduct.entity.pojo.ServCheckupCatalogItem;
import com.xmz.service.serviceproduct.entity.pojo.ServCheckupPkg;
import com.xmz.service.serviceproduct.entity.vo.ServCheckupCatalogQuery;
import com.xmz.service.serviceproduct.service.IServCheckupCatalogItemService;
import com.xmz.service.serviceproduct.service.IServCheckupCatalogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 点检项目 前端控制器
 * </p>
 *
 * @author zt
 * @since 2021-05-28
 */
@Api("点检项目")
@RestController
@RequestMapping("/serviceServiceProduct/serv-checkup-catalog")
@CrossOrigin
public class ServCheckupCatalogController {
    @Autowired
    IServCheckupCatalogService iServCheckupCatalogService;

    @Autowired
    IServCheckupCatalogItemService iServCheckupCatalogItemService;

    //分页
    @ApiOperation("获取点检项目列表(条件分页)")
    @PostMapping("/queryPage/{current}/{size}")
    public ResultMap queryPage(@RequestBody ServCheckupCatalogQuery servCheckupCatalogQuery,
                               @PathVariable Integer current,
                               @PathVariable Integer size){
        System.out.println(current+"---"+size);
        Page<MyServCheckupCatalog> page=new Page(current,size);
        iServCheckupCatalogService.myPage(page,servCheckupCatalogQuery);
        return ResultMap.getOKInstance()
                .putData("rows",page.getRecords())
                .putData("pages",page.getPages())
                .putData("total",page.getTotal())
                .putData("current",page.getCurrent())
                .putData("size",page.getSize());
    }


    //添加点检项目
    @ApiOperation("添加点检项目")
    @PostMapping("/add")
    public ResultMap add(@RequestBody Map<String,Object> myServCheckupCatalog) {
        boolean flag=iServCheckupCatalogService.add(myServCheckupCatalog);
        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();

   }


    @ApiOperation("删除点检项目")
    @DeleteMapping("/delete/{id}")
    public ResultMap delete(@PathVariable String id){
        boolean flag=iServCheckupCatalogService.removeById(id);
        iServCheckupCatalogItemService.deleteBySccUuid(id);
        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }


    // 根据编号查询
    @ApiOperation("根据点检项目id查询信息")
    @GetMapping("{id}")
    public ResultMap getById(@PathVariable String id) {
       List<MyServCheckupCatalog>  myServCheckupCatalogList = iServCheckupCatalogService.querySccUuid(id);
        return ResultMap.getOKInstance()
                .putData("myServCheckupCatalogList", myServCheckupCatalogList);
    }


    @ApiOperation("编辑点检项目")
    @PostMapping("/update")
    public ResultMap update(@RequestBody Map<String,Object> map) {
        boolean flag=iServCheckupCatalogService.update(map);
        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();

    }

}


package com.xmz.service.serviceproduct.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.common.utils.ResultMap;
import com.xmz.service.serviceproduct.entity.pojo.BaseServCatalog;
import com.xmz.service.serviceproduct.entity.vo.BaseServCatalogQuery;
import com.xmz.service.serviceproduct.entity.vo.MyBaseServCatalog;
import com.xmz.service.serviceproduct.service.IBaseServCatalogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 总部统一服务目录 前端控制器
 * </p>
 *
 * @author liujiaxi
 * @since 2021-06-08
 */
@Api("服务项目")
@RestController
@CrossOrigin
@RequestMapping("/serviceServiceProduct/base-serv-catalog")
public class BaseServCatalogController {
    @Autowired
    IBaseServCatalogService iBaseServCatalogService;

    @ApiOperation("分页条件查询服务项目")
    @PostMapping("queryAllPage/{current}/{size}")
    public ResultMap queryAllPage(@PathVariable Long current,
                                     @PathVariable Long size,
                                     @RequestBody BaseServCatalogQuery baseServCatalogQuery){
        Page<MyBaseServCatalog> brPackagePage = new Page<>(current, size);

        iBaseServCatalogService.queryAllPage(brPackagePage,baseServCatalogQuery);
        return ResultMap.getOKInstance()
                .putData("rows",brPackagePage.getRecords())
                .putData("pages",brPackagePage.getPages())
                .putData("total",brPackagePage.getTotal())
                .putData("current",brPackagePage.getCurrent())
                .putData("size",brPackagePage.getSize());
    }

    //添加
    @ApiOperation("增加服务项目")
    @PostMapping("/add")
    public ResultMap add(@RequestBody BaseServCatalog baseServCatalog){

        boolean flag=iBaseServCatalogService.save(baseServCatalog);
        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }

    //修改
    @ApiOperation("修改服务项目")
    @PostMapping("/update")
    public ResultMap update(@RequestBody BaseServCatalog baseServCatalog){
        boolean flag=iBaseServCatalogService.updateById(baseServCatalog);
        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }

    @ApiOperation("修改SA提成")
    @PostMapping("/update1")
    public ResultMap update1(@RequestBody BaseServCatalog baseServCatalog){
        boolean flag=iBaseServCatalogService.updateCommRateSa(baseServCatalog);
        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }

    @ApiOperation("修改技师提成")
    @PostMapping("/update2")
    public ResultMap update2(@RequestBody BaseServCatalog baseServCatalog){
        boolean flag=iBaseServCatalogService.updateCommRateWorker(baseServCatalog);
        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }

    @ApiOperation("根据id查询服务项目")
    @PostMapping("/queryById/{id}")
    public ResultMap queryById(@PathVariable String id) {

        BaseServCatalog baseServCatalog=iBaseServCatalogService.getById(id);
        return ResultMap.getOKInstance()
                .putData("baseServCatalog",baseServCatalog);

    }

    //删除
    @ApiOperation("根据id删除服务项目")
    @DeleteMapping("/delete/{id}")
    public ResultMap delete(@PathVariable String id){
        boolean flag=iBaseServCatalogService.removeById(id);
        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }

}


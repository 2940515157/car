package com.xmz.service.serviceproduct.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.common.utils.ResultMap;
import com.xmz.service.serviceproduct.entity.pojo.BaseServCatalog;
import com.xmz.service.serviceproduct.entity.pojo.BaseServCatalogPart;
import com.xmz.service.serviceproduct.entity.vo.BaseServCatalogQuery;
import com.xmz.service.serviceproduct.entity.vo.MyBaseServCatalog;
import com.xmz.service.serviceproduct.entity.vo.MyBaseServCatalogPart;
import com.xmz.service.serviceproduct.service.IBaseServCatalogPartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 总部服务目录与配件目录的对应关系，描述某一服务需要哪些零配件 前端控制器
 * </p>
 *
 * @author liujiaxi
 * @since 2021-06-09
 */
@Api("服务项目和配件目录对应关系")
@RestController
@CrossOrigin
@RequestMapping("/serviceServiceProduct/base-serv-catalog-part")
public class BaseServCatalogPartController {
    @Autowired
    IBaseServCatalogPartService iBaseServCatalogPartService;

    @ApiOperation("分页条件查询服务项目和配件目录对应关系")
    @PostMapping("queryByPart/{current}/{size}/{catalogUuid}")
    public ResultMap queryByPart(@PathVariable Long current,
                                 @PathVariable Long size,
                                 @PathVariable String catalogUuid){
        Page<MyBaseServCatalogPart> brPackagePage = new Page<>(current, size);

        iBaseServCatalogPartService.queryByPart(brPackagePage,catalogUuid);
        return ResultMap.getOKInstance()
                .putData("rows",brPackagePage.getRecords())
                .putData("pages",brPackagePage.getPages())
                .putData("total",brPackagePage.getTotal())
                .putData("current",brPackagePage.getCurrent())
                .putData("size",brPackagePage.getSize());

    }

    //添加
    @ApiOperation("增加服务项目和配件对应关系")
    @PostMapping("/add")
    public ResultMap add(@RequestBody BaseServCatalogPart baseServCatalogPart){

        boolean flag=iBaseServCatalogPartService.save(baseServCatalogPart);
        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }

    //删除
    @ApiOperation("根据id删除服务项目和配件对应关系")
    @DeleteMapping("/delete/{id}")
    public ResultMap delete(@PathVariable String id){
        boolean flag=iBaseServCatalogPartService.removeById(id);
        if(flag)
            return ResultMap.getOKInstance();

        return ResultMap.getErrorInstance();
    }

}


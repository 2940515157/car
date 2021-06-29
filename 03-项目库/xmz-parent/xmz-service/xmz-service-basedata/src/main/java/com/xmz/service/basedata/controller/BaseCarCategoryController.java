package com.xmz.service.basedata.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.common.utils.ResultMap;
import com.xmz.service.basedata.entity.pojo.BaseCarBrand;
import com.xmz.service.basedata.entity.pojo.BaseCarCategory;
import com.xmz.service.basedata.entity.pojo.BaseCarCategoryAll;
import com.xmz.service.basedata.entity.pojo.BaseCarFactory;
import com.xmz.service.basedata.entity.vo.BaseCarCategoryQuery;
import com.xmz.service.basedata.service.IBaseCarCategoryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 车系 前端控制器
 * </p>
 *
 * @author hyc
 * @since 2021-06-08
 */
@RestController
@RequestMapping("/serviceBasedata/base-car-category")
public class BaseCarCategoryController {
    @Autowired
    public IBaseCarCategoryService iBaseCarCategoryService;

    @PostMapping("pageQuery/{current}/{size}")
    public ResultMap myPage(
            @PathVariable Long current,
            @PathVariable Long size,
            @RequestBody BaseCarCategoryQuery baseCarCategoryQuery) {
        System.out.println(current+"---"+size);
        System.out.println(baseCarCategoryQuery.getBrandUuid());
        Page<BaseCarCategoryAll> BaseCarCategoryBrandPage = new Page(current, size);
        iBaseCarCategoryService.myPage(BaseCarCategoryBrandPage, baseCarCategoryQuery);
        return ResultMap.getOKInstance()
                .putData("rows",BaseCarCategoryBrandPage.getRecords())
                .putData("pages",BaseCarCategoryBrandPage.getPages())
                .putData("total",BaseCarCategoryBrandPage.getTotal())
                .putData("current",BaseCarCategoryBrandPage.getCurrent())
                .putData("size",BaseCarCategoryBrandPage.getSize());
    }
    //添加
    @PostMapping("addBaseCarCategory")
    @ApiOperation("添加车系")
    public ResultMap add(
            @ApiParam(name = "baseCarCategory",value = "车系",required = true)
            @RequestBody BaseCarCategory baseCarCategory){
        baseCarCategory.setVersion(new BigDecimal("1"));
        baseCarCategory.setCreatedDate(new Date());
        baseCarCategory.setCreatedBy("admin");

        boolean flag = iBaseCarCategoryService.save(baseCarCategory);

        if(flag)

            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }

    //修改
    @PostMapping("updateBaseCarCategory")
    @ApiOperation("修改车系")
    public ResultMap update(
            @ApiParam(name = "baseCarCategory",value = "车系",required = true)
            @RequestBody BaseCarCategory baseCarCategory){
        baseCarCategory.setUpdatedDate(new Date());
        baseCarCategory.setCreatedBy("admin");

        boolean flag = iBaseCarCategoryService.updateById(baseCarCategory);

        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }
    //删除
    @DeleteMapping("delete/{categoryUuid}")
    @ApiOperation("删除")
    public ResultMap delete(
            @ApiParam(name = "categoryUuid",value = "品牌id",required = true)
            @PathVariable String categoryUuid){

        boolean flag = iBaseCarCategoryService.removeById(categoryUuid);

        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }
      //数据回显
      // 根据id查询
      @GetMapping("queryById/{categoryUuid}")
       @ApiOperation("根据Id查询信息")
       public ResultMap queryById(
             @ApiParam(name = "categoryUuid",value = "车系id",required = true)
             @PathVariable String categoryUuid){

           BaseCarCategory baseCarCategory = iBaseCarCategoryService.getById(categoryUuid);

           return ResultMap.getOKInstance()
               .putData("baseCarCategory",baseCarCategory);
   }
}


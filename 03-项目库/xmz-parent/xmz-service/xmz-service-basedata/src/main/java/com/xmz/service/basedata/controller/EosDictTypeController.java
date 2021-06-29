package com.xmz.service.basedata.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.common.utils.Result;
import com.xmz.common.utils.ResultMap;
import com.xmz.service.basedata.entity.pojo.EosDictType;
import com.xmz.service.basedata.entity.pojo.QueryParam;
import com.xmz.service.basedata.service.EosDictTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author weikang
 * @since 2021-05-24
 */
@Api("字典类型信息")
@RestController
@RequestMapping("/serviceBasedata/dicttype")
@CrossOrigin
public class EosDictTypeController {
    @Autowired
    private EosDictTypeService service;

    //根据ID查
    @GetMapping("/queryById/{dicttypeid}")
    public Result<Object> queryById(@PathVariable String dicttypeid){
        return Result.getOKInstance(service.getById(dicttypeid));
    }

    //分页
    @ApiOperation("获取列表")
    @PostMapping("/queryPage/{current}/{size}")
    public ResultMap queryPage(@RequestBody QueryParam queryParam,
                       @PathVariable Integer current,
                       @PathVariable Integer size){
        Page<EosDictType> page=new Page(current,size);
        service.queryPage(queryParam,page);
        return ResultMap.getOKInstance()
                .putData("rows",page.getRecords())
                .putData("pages",page.getPages())
                .putData("total",page.getTotal())
                .putData("current",page.getCurrent())
                .putData("size",page.getSize())
                ;
    }

    //增加
    @PostMapping("/add")
    public Result<Object> add(@RequestBody EosDictType eosDictType){
        if (service.save(eosDictType))
            return Result.getOKInstance("添加成功");
        return Result.getErrorInstance("添加失败");
    }

    //修改
    @PostMapping("/update")
    public Result<Object> update(@RequestBody EosDictType eosDictType){
        if (service.updateById(eosDictType))
            return Result.getOKInstance("添加成功");
        return Result.getErrorInstance("添加失败");
    }

    //删除
    @DeleteMapping("/delete/{dicttypeid}")
    public Result<Object> delete(@PathVariable String dicttypeid){
        if (service.removeById(dicttypeid))
            return Result.getOKInstance("添加成功");
        return Result.getErrorInstance("添加失败");
    }

}


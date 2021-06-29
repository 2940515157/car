package com.xmz.service.basedata.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.common.serviceBase.MyQueryWrapper;
import com.xmz.common.utils.Result;
import com.xmz.common.utils.ResultMap;
import com.xmz.service.basedata.entity.pojo.EosDictEntry;
import com.xmz.service.basedata.service.EosDictEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author weikang
 * @since 2021-05-24
 */
@CrossOrigin
@RestController
@RequestMapping("/serviceBasedata/dictentry")
public class EosDictEntryController {

    @Autowired
    private EosDictEntryService service;

    //根据dicttypeid查
    @GetMapping("/queryByTypeId/{dicttypeid}/{current}/{size}")
    public ResultMap queryById(@PathVariable String dicttypeid,
                       @PathVariable Integer current,
                       @PathVariable Integer size){
        Page<EosDictEntry> page=new Page(current,size);
        service.queryPage(page,dicttypeid);
        return ResultMap.getOKInstance()
                .putData("rows",page.getRecords())
                .putData("pages",page.getPages())
                .putData("total",page.getTotal())
                .putData("current",page.getCurrent())
                .putData("size",page.getSize())
                ;
    }

    //根据dicttypeid和dictid查
    @GetMapping("/queryByDictId/{dicttypeid}/{dictid}")
    public Result queryByDictId(@PathVariable String dictid,
                           @PathVariable String dicttypeid){
        return Result.getOKInstance(service.queryById(dicttypeid,dictid));
    }

    //根据dicttypeid查
    @GetMapping("/queryTypeByIdList/{dicttypeid}")
    public Result<List> queryTypeByIdList(@PathVariable String dicttypeid){
        return Result.getOKInstance(service.list(new MyQueryWrapper<EosDictEntry>().eq("dicttypeid",dicttypeid)));
    }

    //增加
    @PostMapping("/add")
    public Result add(@RequestBody EosDictEntry eosDictEntry){
        if (service.save(eosDictEntry))
            return Result.getOKInstance("添加成功");
        return Result.getErrorInstance("添加失败");
    }

    //修改
    @PostMapping("/update")
    public Result<Object> update(@RequestBody EosDictEntry eosDictEntry){
        if (service.updateById(eosDictEntry))
            return Result.getOKInstance("添加成功");
        return Result.getErrorInstance("添加失败");
    }

    //删除
    @DeleteMapping("/delete/{dictid}")
    public Result<Object> delete(@PathVariable String dictid){
        if (service.removeById(dictid))
            return Result.getOKInstance("添加成功");
        return Result.getErrorInstance("添加失败");
    }


}


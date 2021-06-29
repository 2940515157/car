package com.xmz.service.serviceproduct.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.common.utils.Result;
import com.xmz.common.utils.ResultMap;
import com.xmz.service.serviceproduct.entity.pojo.ServCheckupDirectory;
import com.xmz.service.serviceproduct.entity.vo.ServCheckQuery;
import com.xmz.service.serviceproduct.service.IServCheckupDirectoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 点检目录 前端控制器
 * </p>
 *
 * @author hwb
 * @since 2021-05-26
 */
@RestController
@RequestMapping("/serviceServiceProduct/serv-checkup-directory")
@CrossOrigin
@Api("点检目录")
public class ServCheckupDirectoryController {
    @Autowired
    private IServCheckupDirectoryService iServCheckupDirectoryService;

    @GetMapping("/listall")
    @ApiOperation("查询所有子目录")
    public ResultMap list(){
       List<ServCheckupDirectory> list2= iServCheckupDirectoryService.listAll();

        return ResultMap.getOKInstance().putData("list",list2);
    }

    @GetMapping("/findAllTree")
    @ApiOperation("查询点检目录(树状)")
    public ResultMap findAllTree(){
        List<ServCheckupDirectory> list=iServCheckupDirectoryService.findAllTree();
        return ResultMap.getOKInstance()
                .putData("rows",list);
    }

    //添加
    @PostMapping("add")
    @ApiOperation("添加点检目录")
    public ResultMap add(
            @ApiParam(name = "ServCheckupDirectory",value = "点检目录",required = true)
            @RequestBody ServCheckupDirectory servCheckupDirectory){

        boolean flag = iServCheckupDirectoryService.save(servCheckupDirectory);

        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }

    //删除
    @DeleteMapping("{scdUuid}")
    @ApiOperation("删除点检目录")
    public ResultMap delete(
            @ApiParam(name = "scdUuid",value = "检点目录id",required = true)
            @PathVariable String scdUuid){

        boolean flag = iServCheckupDirectoryService.removeById(scdUuid);

        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }


    //修改
    @PostMapping("update")
    @ApiOperation("修改检点目录")
    public ResultMap update(
            @ApiParam(name = "servCheckupDirectory",value = "检点目录",required = true)
            @RequestBody ServCheckupDirectory servCheckupDirectory){

        boolean flag = iServCheckupDirectoryService.updateById(servCheckupDirectory);

        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }

    //分页查询检点信息
    @PostMapping("page/{current}/{size}")
    public ResultMap myPage(
            @PathVariable Long current,
            @PathVariable Long size,
            @RequestBody ServCheckQuery servCheckQuery) {
        System.out.println(current+"---"+size+"---"+ servCheckQuery);
        Page<ServCheckupDirectory> servCheckupDirectoryPage = new Page<>(current, size);
        iServCheckupDirectoryService.myPage(servCheckupDirectoryPage, servCheckQuery);
        return ResultMap.getOKInstance()
                .putData("rows",servCheckupDirectoryPage.getRecords())
                .putData("pages",servCheckupDirectoryPage.getPages())
                .putData("total",servCheckupDirectoryPage.getTotal())
                .putData("current",servCheckupDirectoryPage.getCurrent())
                .putData("size",servCheckupDirectoryPage.getSize());
    }

    // 根据id查询
    @GetMapping("queryById/{scdUuid}")
    @ApiOperation("根据Id查询点检信息")
    public ResultMap queryById(
            @ApiParam(name = "scdUuid",value = "点检id",required = true)
            @PathVariable String scdUuid){

        ServCheckupDirectory servCheckupDirectory = iServCheckupDirectoryService.getById(scdUuid);

        return ResultMap.getOKInstance()
                .putData("servCheckupDirectory",servCheckupDirectory);
    }

}


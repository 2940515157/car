package com.xmz.service.hr.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.common.utils.Result;
import com.xmz.common.utils.ResultMap;
import com.xmz.service.hr.entity.pojo.HrOrg;
import com.xmz.service.hr.entity.pojo.SysAccount;
import com.xmz.service.hr.entity.vo.HrOrgQuery;
import com.xmz.service.hr.service.IHrOrgService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 部门信息 前端控制器
 * </p>
 *
 * @author leiyaping
 * @since 2021-05-28
 */
@RestController
@RequestMapping("/serviceHr/HrOrg")
@CrossOrigin
public class HrOrgController {
    @Autowired
    private IHrOrgService iHrOrgService;

    @GetMapping("queryAll")
    @ApiOperation("查询全部")
    public ResultMap list(){
        List<HrOrg> hrOrgList=iHrOrgService.list();
        return ResultMap.getOKInstance().putData("hrOrg",hrOrgList);
    }
    //组合查询带分页的请求
    @PostMapping("page/{current}/{size}")
    @ApiOperation("分页查询")
    public ResultMap page(
            @ApiParam(name= "page" ,value = "当前页号",required = true)
            @PathVariable Integer current,
            @ApiParam(name= "size",value = "页大小",required = true)
            @PathVariable Integer size,
            @RequestBody HrOrgQuery hrOrgQuery){
        // 分页参数
        Page<HrOrg> hrOrgPage = new Page<>(current, size);
        //业务调用
        iHrOrgService.pageQuery(hrOrgPage,hrOrgQuery);

        return ResultMap.getOKInstance()
                .putData("rows",hrOrgPage.getRecords())
                .putData("total",hrOrgPage.getTotal())
                .putData("pages",hrOrgPage.getPages())
                .putData("current",hrOrgPage.getCurrent())
                .putData("size",hrOrgPage.getSize());
    }
    //根据id查询用户信息
    @GetMapping("queryById/{orgUuid}")
    @ApiOperation("根据id查询用户信息")
    public ResultMap queryByid(
            @ApiParam(name= "orgUuid",value = "部门id",required = true)
            @PathVariable String orgUuid){
        HrOrg hrOrg=iHrOrgService.getById(orgUuid);
        return ResultMap.getOKInstance().putData("hrOrg",hrOrg);
    }
    //添加部门
    @PostMapping("add")
    @ApiOperation("添加部门信息")
    public Result add(
            @ApiParam(name="hrOrg",value="添加部门信息",required = true)
            @RequestBody HrOrg hrOrg){

        boolean flag=iHrOrgService.save(hrOrg);

        if(flag)
            return Result.getOKInstance();
        return Result.getErrorInstance();
    }
    //修改部门
    @PostMapping("update")
    @ApiOperation("修改部门信息")
    public Result update(
            @ApiParam(name="hrOrg",value = "部门对象",required = true)
            @RequestBody HrOrg hrOrg){

        boolean flag=iHrOrgService.updateById(hrOrg);

        if(flag)
            return Result.getOKInstance();
        return  Result.getErrorInstance();
    }
    //删除部门

    @DeleteMapping("delete/{orgUuid}")
    @ApiOperation(value = "根据用户编号逻辑删除用户信息")
    public Result delete(
            @ApiParam(name = "orgUuid", value = "部门编号", required = true)
            @PathVariable String orgUuid) {
        boolean flag = iHrOrgService.removeById(orgUuid);
        if(flag)
            return Result.getOKInstance();
        return Result.getErrorInstance();
    }






    @ApiOperation("分页查询部门里大区")
    @PostMapping("queryAllPage/{current}/{size}")
    public ResultMap queryAllPage(@PathVariable Long current,
                                  @PathVariable Long size){
        Page<HrOrg> AllPage = new Page<>(current, size);

        iHrOrgService.queryAllPage(AllPage);
        System.out.println(AllPage.getRecords());
        return ResultMap.getOKInstance()
                .putData("rows",AllPage.getRecords())
                .putData("pages",AllPage.getPages())
                .putData("total",AllPage.getTotal())
                .putData("current",AllPage.getCurrent())
                .putData("size",AllPage.getSize());
    }

    /**
     * 查询所有营运中心的部门
     * @return
     */
    @ApiOperation("查询所有营运中心的部门")
    @PostMapping("queryAllYingYun")
    public ResultMap queryAllYingYun(@RequestBody HrOrg hrOrg){
        String orgUuid=hrOrg.getOrgUuid();
        List<HrOrg> list=iHrOrgService.queryAllYingYun(orgUuid);
        return ResultMap.getOKInstance()
                .putData("list",list);
    }

    /**
     * 获取大区，以选项封装
     * @author 罗俊
     * @return <code>[{id: "foo", label: "FOO"},{...}]</code>
     */
    @GetMapping("/getOrgOptions")
    public Result<Object> getOrgOptions(){
        return Result.getOKInstance(iHrOrgService.listMaps(
                new QueryWrapper<HrOrg>()
                .select("ORG_UUID id","ORG_NAME label")
        ));
    }

}


package com.xmz.br.basedata.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.br.basedata.pojo.entity.HrOrg;
import com.xmz.br.basedata.pojo.vo.HrOrgPage;
import com.xmz.br.basedata.pojo.vo.HrOrgQuery;
import com.xmz.br.basedata.pojo.vo.R;
import com.xmz.br.basedata.service.HrOrgService;
import com.xmz.common.utils.Result;
import com.xmz.common.utils.ResultMap;
import io.swagger.annotations.Api;
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
 * @author 驰骋
 * @since 2021-05-26
 */
@Api(description = "总店-人力资源-部门/大区管理")
@RestController
@RequestMapping("/hrmanagementservice/hr-org")
@CrossOrigin
public class HrOrgController {

    @Autowired
    HrOrgService hrOrgService;

    // 条件查询Tree结构部门列表
    @ApiOperation(value = "条件查询Tree结构部门列表")
    @PostMapping("queryTreeTable")
    public Result<Object> queryTreeTable(
            @ApiParam(name = "hrOrgQuery", value = "查询条件对象", required = false)
            @RequestBody(required = false) HrOrgQuery hrOrgQuery) {
        List<HrOrgPage> hrOrgPageList = hrOrgService.findList(hrOrgQuery);
        return Result.getOKInstance("rows", hrOrgPageList);
    }

    // 条件+分页查询大区列表
    @ApiOperation(value = "按条件分页查询大区列表")
    @PostMapping("queryAllBigAreaPage/{page}/{size}")
    public ResultMap queryAllBigAreaPage(
            @ApiParam(value = "当前页号", name = "page", required = true)
            @PathVariable Integer page,
            @ApiParam(value = "页大小", name = "size", required = true)
            @PathVariable Integer size) {

        Page<HrOrg> hrOrgPagePage = new Page<>(page, size);
        hrOrgService.queryAllBigAreaPage(hrOrgPagePage);
       /* return R.ok().data("rows", hrOrgPagePage.getRecords())
                .data("total", hrOrgPagePage.getTotal())
                .data("pages", hrOrgPagePage.getPages())
                .data("current", hrOrgPagePage.getCurrent())
                .data("size", hrOrgPagePage.getSize());*/
        return ResultMap.getOKInstance().putData("rows", hrOrgPagePage.getRecords())
                .putData("total", hrOrgPagePage.getTotal())
                .putData("pages", hrOrgPagePage.getPages())
                .putData("current", hrOrgPagePage.getCurrent())
                .putData("size", hrOrgPagePage.getSize());
    }

    @ApiOperation(value = "分页查询不包含大区的数据")
    @PostMapping("noPageQuery/{page}/{size}")
    public ResultMap noPageQuery(
            @ApiParam(name="page", value = "当前页码", required = true)
            @PathVariable Integer page,
            @ApiParam(name = "size", value = "页大小", required = true)
            @PathVariable Integer size,
            @ApiParam(name = "orgNo",value = "查询条件")
            @RequestBody String[] orgNo) {
        Page<HrOrg> pageParam = new Page<>(page, size);

        if(orgNo.length==0){
            hrOrgService.queryAllBigAreaPage(pageParam);
        }else{
            hrOrgService.noPageQuery(pageParam, orgNo);
        }

//        return R.ok().data("total", pageParam.getTotal()).data("rows", pageParam.getRecords());
        return ResultMap.getOKInstance().putData("total", pageParam.getTotal())
                .putData("rows", pageParam.getRecords());
    }

    // 根据部门id进行查询
    @ApiOperation(value = "根据部门编号进行查询")
    @GetMapping("queryById/{id}")
    public ResultMap queryById(
            @ApiParam(name="id", value = "部门id", required = true)
            @PathVariable String id) {
        HrOrg hrOrg = hrOrgService.getById(id);
//        return R.ok().data("hrOrg", hrOrg);
        return ResultMap.getOKInstance().putData("hrOrg", hrOrg);
    }

    //查询出全部的大区数据
    @ApiOperation(value = "查询全部大区数据")
    @GetMapping("queryAllBigArea")
    public ResultMap queryAllBigArea() {
        QueryWrapper<HrOrg> queryWrapper = new QueryWrapper<>();
        //只查询出 是大区的 列
        queryWrapper.eq("IS_BIG_AREA","Y");
        //只查询出 不停用的 列
        queryWrapper.eq("IS_DISABLED",'N');
        //只查询出 有效的 列
        queryWrapper.eq("IS_ACTIVE",'Y');
        List<HrOrg> list = hrOrgService.list(queryWrapper);

        //return R.ok().data("bigAreaList", list);
        return  ResultMap.getOKInstance().putData("bigAreaList", list);
    }


    //查询出全部的部门数据
    @ApiOperation(value = "查询全部部门数据")
    @GetMapping("queryAllOrg")
    public ResultMap queryAllOrg() {
        QueryWrapper<HrOrg> queryWrapper = new QueryWrapper<>();
        //只查询出 不是大区的 列
        queryWrapper.eq("IS_BIG_AREA","N");
        //只查询出 不停用的 列
        queryWrapper.eq("IS_DISABLED",'N');
        //只查询出 有效的 列
        queryWrapper.eq("IS_ACTIVE",'Y');
        List<HrOrg> list = hrOrgService.list(queryWrapper);

        //return R.ok().data("hrOrgList", list);
        return ResultMap.getOKInstance().putData("hrOrgList", list);
    }

    // 添加部门
    @ApiOperation(value = "添加部门/大区")
    @PostMapping("insert")
    public Result<Object> insert(
            @ApiParam(name = "hrOrg", value = "讲师对象", required = true)
            @RequestBody HrOrg hrOrg) {
        boolean flag = hrOrgService.save(hrOrg);
        if(flag)
            return Result.getOKInstance();
        return Result.getErrorInstance();
    }

    // 修改部门
    @ApiOperation(value = "修改部门")
    @PostMapping("update")
    public Result<Object> update(
            @ApiParam(name = "hrOrg", value = "部对象", required = true)
            @RequestBody HrOrg hrOrg) {
        boolean flag = hrOrgService.updateById(hrOrg);
        if(flag)
            return Result.getOKInstance();
        return Result.getErrorInstance();
    }

    // 删除部门的方法
    @ApiOperation("根据ID删除部门")
    @DeleteMapping("{id}")
    public Result<Object> removeTeacher(
            @ApiParam(name = "id", value = "部门编号", required = true)
            @PathVariable String id) {
        boolean flag = hrOrgService.removeById(id);
        if(flag)
            return Result.getOKInstance();
        return Result.getErrorInstance();
    }


}


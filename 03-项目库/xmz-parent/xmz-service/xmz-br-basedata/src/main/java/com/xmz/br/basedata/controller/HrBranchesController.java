package com.xmz.br.basedata.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.br.basedata.pojo.entity.HrBranches;
import com.xmz.br.basedata.pojo.vo.*;
import com.xmz.br.basedata.service.HrBranchesService;
import com.xmz.common.utils.Result;
import com.xmz.common.utils.ResultMap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 门店信息 前端控制器
 * </p>
 *
 * @author 驰骋
 * @since 2021-05-28
 */

@Api(description = "总店-人力资源-门店管理")
@RestController
@RequestMapping("/hrmanagementservice/hr-branches")
@CrossOrigin
public class HrBranchesController {


    @Autowired
    HrBranchesService hrBranchesService;

    @ApiOperation("查询门店详细信息")
    @GetMapping("/{brCode}")
    public Result<HrBranches> queryMyHrBranches(
            @ApiParam(name = "brCode",value = "门店表的brCode",required = true)
            @PathVariable String brCode){

        HrBranches hrBranches=hrBranchesService.queryMyHrBranches(brCode);
        return Result.getOKInstance("hrBranches",hrBranches);
    }

    @ApiOperation("条件分页查询门店信息")
    @PostMapping("/page/{current}/{size}")
    public ResultMap query(
            @ApiParam(name = "current",value = "页码")
            @PathVariable Long current,
            @ApiParam(name = "size",value = "页大小")
            @PathVariable Long size,
            @ApiParam(name = "hrBranchesQuery",value = "查询条件")
            @RequestBody HrBranchesQuery hrBranchesQuery) {
        Page<HrBranchesPage> hrBranchesPagePage = new Page<>(current, size);
        hrBranchesService.query(hrBranchesPagePage,hrBranchesQuery);
        /*return R.ok().data("rows", hrBranchesPagePage.getRecords())
                .data("total", hrBranchesPagePage.getTotal())
                .data("pages", hrBranchesPagePage.getPages())
                .data("current", hrBranchesPagePage.getCurrent())
                .data("size", hrBranchesPagePage.getSize());*/
        return ResultMap.getOKInstance().putData("rows", hrBranchesPagePage.getRecords())
                .putData("total", hrBranchesPagePage.getTotal())
                .putData("pages", hrBranchesPagePage.getPages())
                .putData("current", hrBranchesPagePage.getCurrent())
                .putData("size", hrBranchesPagePage.getSize());
    }

    // 根据主键id进行查询
    @ApiOperation(value = "根据主键id查询")
    @GetMapping("queryById/{id}")
    public Result<HrBranches> queryById(
            @ApiParam(name="id", value = "门店id", required = true)
            @PathVariable String id) {
        HrBranches hrBranches = hrBranchesService.getById(id);
//        return R.ok().data("hrBranches", hrBranches);
        return Result.getOKInstance("hrBranches", hrBranches);
    }

    //查询出全部的门店信息
    @ApiOperation(value = "查询全部门店数据")
    @GetMapping("queryAll")
    public Result<List<HrBranches>> queryAll() {
        QueryWrapper<HrBranches> queryWrapper = new QueryWrapper<>();
        //只查询出 有效的 列
        queryWrapper.eq("IS_ACTIVE",'Y');
        List<HrBranches> hrBranchesList=hrBranchesService.list(queryWrapper);

//        return R.ok().data("hrBranchesList", hrBranchesList);
        return Result.getOKInstance("hrBranchesList", hrBranchesList);
    }

    // 添加门店
    @ApiOperation(value = "添加门店")
    @PostMapping("insert")
    public Result<Object> insert(
            @ApiParam(name = "hrBranches", value = "门店对象", required = true)
            @RequestBody HrBranches hrBranches) {
        //自动生成uuid
        String uuid = UUIDUtil.getUUID();
        //设置主键id
        hrBranches.setBrUuid(uuid);
        //设置是否有效
        hrBranches.setIsActive("Y");
        //设置创建时间
        hrBranches.setCreatedDate(new Date());
        //设置版本
        hrBranches.setVersion(BigDecimal.valueOf(0));
        //设置拼音
        hrBranches.setPinyin(PinYin.ToPinyin(hrBranches.getBrName()));

        boolean flag = hrBranchesService.save(hrBranches);
        if(flag)
            return Result.getOKInstance();
        return Result.getErrorInstance();
    }

    // 修改门店
    @ApiOperation(value = "修改门店")
    @PostMapping("update")
    public Result<Object> update(
            @ApiParam(name = "hrBranches", value = "门店对象", required = true)
            @RequestBody HrBranches hrBranches) {

        //设置修改日期
        hrBranches.setUpdatedDate(new Date());
        //设置版本号+1
        BigDecimal bigDecimal = new BigDecimal(1);
        hrBranches.setVersion(hrBranches.getVersion().add(bigDecimal));

        boolean flag = hrBranchesService.updateById(hrBranches);
        if(flag)
            return Result.getOKInstance();
        return Result.getErrorInstance();
    }

    // 删除区域
    @ApiOperation("根据ID删除门店")
    @DeleteMapping("{id}")
    public Result<Object> delete(
            @ApiParam(name = "id", value = "门店编号", required = true)
            @PathVariable String id) {
        boolean flag = hrBranchesService.removeById(id);
        if(flag)
            return Result.getOKInstance();
        return Result.getErrorInstance();
    }

    @ApiOperation(value = "分页查询不包含门店的数据")
    @PostMapping("noPageQuery/{page}/{size}")
    public ResultMap noPageQuery(
            @ApiParam(name="page", value = "当前页码", required = true)
            @PathVariable Integer page,
            @ApiParam(name = "size", value = "页大小", required = true)
            @PathVariable Integer size,
            @RequestBody Map<String,Object> datas) {
        Page<HrBranchesPage> pageParam = new Page<>(page, size);

        String[] brCodes=datas.get("brCode").toString().split(",");

        String brName=datas.get("brName").toString();

        if(brCodes.length==0){
            hrBranchesService.pageQuery(pageParam,brName);
        }else{
            hrBranchesService.noPageQuery(pageParam,brCodes,brName);
        }

        //return R.ok().data("total", pageParam.getTotal()).data("rows", pageParam.getRecords());
        return ResultMap.getOKInstance().putData("total", pageParam.getTotal())
                .putData("rows", pageParam.getRecords());
    }

    //分页查询出 未授权 门店信息
    @ApiOperation("分页查询出 未授权 门店信息")
    @PostMapping("/queryUnAuthorizationBranches/{current}/{size}")
    public ResultMap queryUnAuthorizationBranches(
            @ApiParam(name = "current",value = "页码")
            @PathVariable Long current,
            @ApiParam(name = "size",value = "页大小")
            @PathVariable Long size,
            @ApiParam(name = "hrBranchesQuery",value = "查询条件")
            @RequestBody HrBranchesQuery hrBranchesQuery) {
        Page<HrBranchesPage> hrBranchesPagePage = new Page<>(current, size);
        hrBranchesService.queryUnAuthorizationBranches(hrBranchesPagePage,hrBranchesQuery);
        /*return R.ok().data("rows", hrBranchesPagePage.getRecords())
                .data("total", hrBranchesPagePage.getTotal())
                .data("pages", hrBranchesPagePage.getPages())
                .data("current", hrBranchesPagePage.getCurrent())
                .data("size", hrBranchesPagePage.getSize());*/
        return ResultMap.getOKInstance().putData("rows", hrBranchesPagePage.getRecords())
                .putData("total", hrBranchesPagePage.getTotal())
                .putData("pages", hrBranchesPagePage.getPages())
                .putData("current", hrBranchesPagePage.getCurrent())
                .putData("size", hrBranchesPagePage.getSize());
    }
    //分页查询出 已授权 门店信息
    @ApiOperation("分页查询出 已授权 门店信息")
    @PostMapping("/queryAuthorizationBranches/{current}/{size}")
    public ResultMap queryAuthorizationBranches(
            @ApiParam(name = "current",value = "页码")
            @PathVariable Long current,
            @ApiParam(name = "size",value = "页大小")
            @PathVariable Long size,
            @ApiParam(name = "hrBranchesQuery",value = "查询条件")
            @RequestBody HrBranchesQuery hrBranchesQuery) {
        Page<HrBranchesPage> hrBranchesPagePage = new Page<>(current, size);
        hrBranchesService.queryAuthorizationBranches(hrBranchesPagePage,hrBranchesQuery);
        /*return R.ok().data("rows", hrBranchesPagePage.getRecords())
                .data("total", hrBranchesPagePage.getTotal())
                .data("pages", hrBranchesPagePage.getPages())
                .data("current", hrBranchesPagePage.getCurrent())
                .data("size", hrBranchesPagePage.getSize());*/
        return ResultMap.getOKInstance().putData("rows", hrBranchesPagePage.getRecords())
                .putData("total", hrBranchesPagePage.getTotal())
                .putData("pages", hrBranchesPagePage.getPages())
                .putData("current", hrBranchesPagePage.getCurrent())
                .putData("size", hrBranchesPagePage.getSize());
    }

    /**
     * 根据大区和区域查询出门店
     * @return
     */
    @ApiOperation("根据区域查询出门店")
    @GetMapping("/queryAllMenDian/{areaUuid}")
    public Result<Object> queryAllMenDian(
            @ApiParam(name = "areaUuid", value = "区域编号")
            @PathVariable String areaUuid){
        List<HrBranches> list=hrBranchesService.queryAllMenDian(areaUuid);
//        return R.ok().data("list",list);
        return Result.getOKInstance("list",list);
    }
}


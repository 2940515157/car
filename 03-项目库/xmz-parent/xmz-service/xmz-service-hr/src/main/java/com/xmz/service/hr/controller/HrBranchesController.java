package com.xmz.service.hr.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.common.utils.Result;
import com.xmz.common.utils.ResultMap;
import com.xmz.service.hr.entity.pojo.HrBranches;
import com.xmz.service.hr.entity.pojo.HrOrg;
import com.xmz.service.hr.entity.vo.HrBranchesQuery;
import com.xmz.service.hr.entity.vo.MyHrBranches;
import com.xmz.service.hr.service.IHrBranchesService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 门店信息 前端控制器
 * </p>
 *
 * @author liujiaxi
 * @since 2021-06-04
 */
@CrossOrigin
@RestController
@RequestMapping("/serviceHr/hr-branches")
public class HrBranchesController {
    @Autowired
    IHrBranchesService iHrBranchesService;


    @ApiOperation("分页查询门店")
    @PostMapping("queryAllHrBPage/{current}/{size}")
    public ResultMap queryAllPage(@PathVariable Long current,
                                  @PathVariable Long size,
                                  @RequestBody HrBranches hrBranches){
        Page<MyHrBranches> AllPage = new Page<>(current, size);

        iHrBranchesService.queryAllPage(AllPage,hrBranches.getBrName());
        System.out.println(AllPage.getRecords());
        return ResultMap.getOKInstance()
                .putData("rows",AllPage.getRecords())
                .putData("pages",AllPage.getPages())
                .putData("total",AllPage.getTotal())
                .putData("current",AllPage.getCurrent())
                .putData("size",AllPage.getSize());
    }

    /**
     * 根据大区和区域查询出门店
     * @return
     */
    @ApiOperation("根据大区和区域查询出门店")
    @PostMapping("queryAllMenDian")
    public ResultMap queryAllMenDian(@RequestBody HrBranches hrBranches){
        List<HrBranches> list=iHrBranchesService.queryAllMenDian(hrBranches);
        return ResultMap.getOKInstance()
                .putData("list",list);
    }

    /**
     * 获取根据区域ID获取门店，以选项封装
     * @author 罗俊
     * @return <code>[{id: "foo", label: "FOO"},{...}]</code>
     */
    @GetMapping("/getBrOptions/{areaId}")
    public Result<Object> getBrOptions(@PathVariable String areaId){
        return Result.getOKInstance(iHrBranchesService.listMaps(
                new QueryWrapper<HrBranches>()
                .select("BR_UUID id","BR_NAME label")
                        .eq(!"-1".equals(areaId),"AREA_UUID",areaId)
        ));
    }


    /**
     * 查询出所有门店
     * @return
     */
    @ApiOperation("查询出所有门店")
    @GetMapping("queryAll")
    public ResultMap queryAll(){
        List<HrBranches> list=iHrBranchesService.queryAll();
        return ResultMap.getOKInstance()
                .putData("list",list);
    }


    @ApiOperation("查询带条件")
    @PostMapping("query/{current}/{size}")
    public ResultMap queryBrancher(
            @PathVariable Long current,
            @PathVariable Long size,
            @RequestBody HrBranchesQuery hrBrancherQuery) {
        Page<MyHrBranches> hrBranchesPage = new Page<>(current, size);

        iHrBranchesService.query(hrBranchesPage,hrBrancherQuery);

        return ResultMap.getOKInstance()
                .putData("rows",hrBranchesPage.getRecords())
                .putData("pages",hrBranchesPage.getPages())
                .putData("total",hrBranchesPage.getTotal())
                .putData("current",hrBranchesPage.getCurrent())
                .putData("size",hrBranchesPage.getSize());
    }

    @PostMapping("/add")
    @ApiOperation("添加门店信息")
    public ResultMap add(
            @ApiParam(name = "hrBranches",value = "门店信息",required = true)
            @RequestBody HrBranches hrBranches){
        boolean flag = iHrBranchesService.save(hrBranches);

        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }

    // 根据id查询
    @GetMapping("queryById/{brUuid}")
    @ApiOperation("根据Id查询门店信息")
    public ResultMap queryById(
            @ApiParam(name = "brUuid",value = "门店信息id",required = true)
            @PathVariable String brUuid){
        HrBranches hrBranches = iHrBranchesService.getById(brUuid);

        return ResultMap.getOKInstance()
                .putData("hrBranches",hrBranches);
    }

    //修改
    @PostMapping("update")
    @ApiOperation("修改门店信息")
    public ResultMap update(
            @ApiParam(name = "basePartsType",value = "门店信息",required = true)
            @RequestBody HrBranches hrBranches){

        boolean flag = iHrBranchesService.updateById(hrBranches);

        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }

    //删除
    @DeleteMapping("/delete/{id}")
    public Result<Object> delete(@PathVariable String id){
        if (iHrBranchesService.removeById(id))
            return Result.getOKInstance("删除成功");
        return Result.getErrorInstance("删除失败");
    }



}


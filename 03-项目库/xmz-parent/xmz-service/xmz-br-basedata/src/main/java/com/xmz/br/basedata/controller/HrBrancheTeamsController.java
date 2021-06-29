package com.xmz.br.basedata.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.br.basedata.pojo.entity.HrBrancheTeams;
import com.xmz.br.basedata.pojo.entity.HrBranches;
import com.xmz.br.basedata.pojo.entity.HrTeamsEmp;
import com.xmz.br.basedata.pojo.vo.MyHrBrancheTeams;
import com.xmz.br.basedata.pojo.vo.QueryHrBrancheTeams;
import com.xmz.br.basedata.pojo.vo.R;
import com.xmz.br.basedata.pojo.vo.UUIDUtil;
import com.xmz.br.basedata.service.HrBrancheTeamsService;
import com.xmz.br.basedata.service.HrEmployeesService;
import com.xmz.br.basedata.service.HrTeamsEmpService;
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
 * 班组信息 前端控制器
 * </p>
 *
 * @author 驰骋
 * @since 2021-06-03
 */
@Api(description="分店-基础数据-班组信息管理")
@RestController
@RequestMapping("/hrmanagementservice/hr-branche-teams")
@CrossOrigin
public class HrBrancheTeamsController {

    @Autowired
    HrBrancheTeamsService hrBrancheTeamsService;

    @Autowired
    HrEmployeesService hrEmployeesService;

    @Autowired
    HrTeamsEmpService hrTeamsEmpService;

    @ApiOperation("查询班组信息")
    @PostMapping("/{page}/{rows}")
    public ResultMap queryAllBrancheTeams(
            @ApiParam(value = "当前页号", name = "page", required = true)
            @PathVariable("page")int page,
            @ApiParam(value = "页大小", name = "rows", required = true)
            @PathVariable("rows")int rows ,
            @ApiParam( value = "查询条件对象",name = "queryHrBrancheTeams", required = false)
            @RequestBody QueryHrBrancheTeams queryHrBrancheTeams
    ){

        Page<MyHrBrancheTeams> hrBrancheTeamsPage=new Page<>(page,rows);
        //设置员工门店
        queryHrBrancheTeams.setBrUuid(hrEmployeesService.queryBrUuidByShop(queryHrBrancheTeams.getBrUuid()));
        hrBrancheTeamsService.queryBrancheTeams(hrBrancheTeamsPage,queryHrBrancheTeams);

        //System.out.println(JSONObject.toJSONString(hrBrancheTeamsPage.getRecords()));
//        return R.ok()
//                .data("page",hrBrancheTeamsPage.getCurrent())
//                .data("rows",hrBrancheTeamsPage.getSize())
//                .data("total",hrBrancheTeamsPage.getTotal())
//                .data("hrBrancheTeams", JSONObject.toJSONString(hrBrancheTeamsPage.getRecords()));
        return ResultMap.getOKInstance().putData("page",hrBrancheTeamsPage.getCurrent())
                .putData("rows",hrBrancheTeamsPage.getSize())
                .putData("total",hrBrancheTeamsPage.getTotal())
                .putData("hrBrancheTeams", JSONObject.toJSONString(hrBrancheTeamsPage.getRecords()));
    }

    @ApiOperation("通过id 查询 班组信息")
    @GetMapping("/queryById/{id}")
    public Result<HrBrancheTeams> queryById(
            @ApiParam(value = "页面传来的Id",name = "id",required = true)
            @PathVariable("id")String id){
        //return R.ok().data("hrBrancheTeam",hrBrancheTeamsService.getById(id));
        return Result.getOKInstance("hrBrancheTeam",hrBrancheTeamsService.getById(id));
    }



    @ApiOperation("添加班组信息")
    @PostMapping("/add")
    public Result<Object> add(
            @ApiParam(value = "班组信息",name = "hrBrancheTeams",required = true)
            @RequestBody HrBrancheTeams hrBrancheTeams){

        hrBrancheTeams.setTeamUuid(UUIDUtil.getUUID());
        hrBrancheTeams.setIsActive("Y");
        hrBrancheTeamsService.save(hrBrancheTeams);
        return Result.getOKInstance();
    }

    @ApiOperation("修改班组信息")
    @PutMapping("/update")
    public Result<Object> update(
            @ApiParam(value = "班组信息",name = "hrBrancheTeams",required = true)
            @RequestBody HrBrancheTeams hrBrancheTeams){
        hrBrancheTeamsService.updateById(hrBrancheTeams);
        return  Result.getOKInstance();
    }

    @ApiOperation("删除班组")
    @DeleteMapping("/delete/{id}")
    public Result<Object> delete(
            @ApiParam(value = "班组tem_uuid",name = "id",required = true)
            @PathVariable("id") String id){

        QueryWrapper<HrTeamsEmp> queryWrapper=new QueryWrapper();

        queryWrapper.eq("team_uuid",id);
        if(hrTeamsEmpService.list(queryWrapper).size()!=0)
        return Result.getErrorInstance("班组下已存在员工,无法删除");

        hrBrancheTeamsService.removeById(id);
        return  Result.getOKInstance();
    }

}


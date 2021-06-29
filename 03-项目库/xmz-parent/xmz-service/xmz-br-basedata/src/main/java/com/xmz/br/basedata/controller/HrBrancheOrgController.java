package com.xmz.br.basedata.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.br.basedata.pojo.entity.HrBrancheOrg;
import com.xmz.br.basedata.pojo.entity.HrBrancheTeams;
import com.xmz.br.basedata.pojo.vo.MyHrBrancheOrg;
import com.xmz.br.basedata.pojo.vo.QueryHrBrancheOrg;
import com.xmz.br.basedata.pojo.vo.UUIDUtil;
import com.xmz.br.basedata.service.HrBrancheOrgService;
import com.xmz.br.basedata.service.HrBrancheTeamsService;
import com.xmz.br.basedata.service.HrEmployeesService;
import com.xmz.common.utils.Result;
import com.xmz.common.utils.ResultMap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 车间信息 前端控制器
 * </p>
 *
 * @author 驰骋
 * @since 2021-05-31
 */
@Api(description = "分店-基础数据-车间信息管理")
@RestController
@RequestMapping("/hrmanagementservice/hr-branche-org")
@CrossOrigin
public class HrBrancheOrgController {

    @Autowired
    HrEmployeesService hrEmployeesService;

    @Autowired
    HrBrancheOrgService hrBrancheOrgService;

    @Autowired
    HrBrancheTeamsService hrBrancheTeamsService;

    @ApiOperation(value = "按条件分页查询 车间信息 列表")
    @PostMapping("{page}/{rows}")
    public ResultMap queryBrancheOrg(
            @ApiParam(value = "当前页号", name = "page", required = true)
            @PathVariable("page") int page,
            @ApiParam(value = "页大小", name = "rows", required = true)
            @PathVariable("rows") int rows,
            @ApiParam(value = "查询条件对象", name = "queryHrBrancheOrg", required = false)
            @RequestBody QueryHrBrancheOrg queryHrBrancheOrg
    ) {


        Page<MyHrBrancheOrg> hrBrancheOrgPage = new Page<>(page, rows);

        hrBrancheOrgService.queryBrancheOrg(hrBrancheOrgPage, queryHrBrancheOrg);


      /*  return R.ok()
                .data("page", hrBrancheOrgPage.getCurrent())
                .data("rows", hrBrancheOrgPage.getSize())
                .data("total", hrBrancheOrgPage.getTotal())
                .data("hrBrancheOrg", hrBrancheOrgPage.getRecords());*/
        return ResultMap.getOKInstance().putData("page", hrBrancheOrgPage.getCurrent())
                .putData("rows", hrBrancheOrgPage.getSize())
                .putData("total", hrBrancheOrgPage.getTotal())
                .putData("hrBrancheOrg", hrBrancheOrgPage.getRecords());
    }

    @ApiOperation(value = "根据车间Id 查询 数据 返回")
    @GetMapping("{id}")
    public Result<HrBrancheOrg> queryById(
            @ApiParam(value = "页面传来的Id", name = "id", required = true)
            @PathVariable("id") String id) {


        //return R.ok().data("hrBrancheOrg", hrBrancheOrgService.getById(id));
        return Result.getOKInstance("hrBrancheOrg", hrBrancheOrgService.getById(id));
    }


    @ApiOperation("根据门店 br_uuid 查询所有车间信息")
    @GetMapping("/queryAll/{id}")
    public Result<List<HrBrancheOrg>> queryAll(
            @ApiParam(value = "门店br_uuid", name = "id", required = true)
            @PathVariable("id") String id
    ) {

        QueryWrapper<HrBrancheOrg> queryWrapper = new QueryWrapper();

        queryWrapper.eq("br_uuid", hrEmployeesService.queryBrUuidByShop(id));

        //return R.ok().data("hrBrancheOrgs", hrBrancheOrgService.list(queryWrapper));
        return Result.getOKInstance("hrBrancheOrgs", hrBrancheOrgService.list(queryWrapper));
    }

    @ApiOperation(value = "根据Id删除")
    @DeleteMapping("{id}")
    public Result<Object> deleteId(
            @ApiParam(value = "页面传的 选中的id 字符串 , 号隔开", name = "id", required = true)
            @PathVariable("id") String id) {


        String[] ids = id.split(",");


        QueryWrapper<HrBrancheTeams> queryWrapper = new QueryWrapper();

        queryWrapper.in("bd_uuid", Arrays.asList(ids));

        if (hrBrancheTeamsService.list(queryWrapper).size() != 0)
            return Result.getOKInstance("车间被使用，不能删除");


        if (hrBrancheOrgService.removeByIds(Arrays.asList(ids)))
            return Result.getOKInstance();

        return Result.getErrorInstance();
    }


    @ApiOperation(value = "修改 或 添加")
    @PostMapping
    public Result<Object> addOrUpdate(
            @ApiParam(value = "车间信息参数", name = "hrBrancheOrg", required = true)
            @RequestBody HrBrancheOrg hrBrancheOrg) {


        //2.判断是否有bd_uuid 没有 就是添加  操作
        if (StringUtils.isEmpty(hrBrancheOrg.getBdUuid())) {
            //设置bd_uuid
            hrBrancheOrg.setBdUuid(UUIDUtil.getUUID());
            hrBrancheOrg.setIsActive("Y");
        }

        //3.设置修改人
        //hrBrancheOrg.setUpdatedBy("");

        hrBrancheOrgService.saveOrUpdate(hrBrancheOrg);
        return Result.getOKInstance();
//        if(hrBrancheOrgService.saveOrUpdate(hrBrancheOrg))
//        return R.ok();
//        return R.error();
    }


}


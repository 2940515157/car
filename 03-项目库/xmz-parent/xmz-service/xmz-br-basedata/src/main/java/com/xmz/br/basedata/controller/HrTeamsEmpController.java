package com.xmz.br.basedata.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.br.basedata.pojo.entity.HrTeamsEmp;
import com.xmz.br.basedata.pojo.vo.MyHrTeamsEmp;
import com.xmz.br.basedata.pojo.vo.R;
import com.xmz.br.basedata.pojo.vo.UUIDUtil;
import com.xmz.br.basedata.service.HrTeamsEmpService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 班组成员信息 前端控制器
 * </p>
 *
 * @author 驰骋
 * @since 2021-06-08
 */
@RestController
@RequestMapping("/hrmanagementservice/hr-teams-emp")
@CrossOrigin
public class HrTeamsEmpController {


    @Autowired
    HrTeamsEmpService hrTeamsEmpService;


    @GetMapping("queryTeamUuId/{page}/{rows}/{teamUuid}")
    public R queryTeamUuId(
            @ApiParam(value = "当前页号", name = "page", required = true)
            @PathVariable("page")int page,
            @ApiParam(value = "页大小", name = "rows", required = true)
            @PathVariable("rows")int rows,
            @PathVariable("teamUuid")String teamUuid){

        Page<MyHrTeamsEmp> teamsEmpPage=new Page<>(page,rows);
        hrTeamsEmpService.queryTeamUuId(teamsEmpPage,teamUuid);
        return R.ok().
                data("page",teamsEmpPage.getCurrent())
                .data("rows",teamsEmpPage.getSize())
                .data("total",teamsEmpPage.getTotal())
                .data("hrTeamsEmps", teamsEmpPage.getRecords());
    }


    @PostMapping("add")
    public R add(@RequestBody List<HrTeamsEmp> hrTeamsEmps){

        for (HrTeamsEmp hrTeamsEmp:hrTeamsEmps) {
            hrTeamsEmp.setTeamEmpUuid(UUIDUtil.getUUID());
            hrTeamsEmp.setIsActive("Y");
        }
        hrTeamsEmpService.saveBatch(hrTeamsEmps);

        return R.ok();
    }


    @DeleteMapping("/delete/{id}")
    public R delete(
            @ApiParam(value = "班组-成员 teamEmp_uuid",name = "id",required = true)
            @PathVariable("id") String id){

        String[] ids=id.split(",");

        hrTeamsEmpService.removeByIds(Arrays.asList(ids));
        return  R.ok();
    }


    @PutMapping("update")
    public R update(@RequestBody List<HrTeamsEmp> hrTeamsEmps){

        hrTeamsEmpService.updateBatchById(hrTeamsEmps);

        return R.ok();
    }
}


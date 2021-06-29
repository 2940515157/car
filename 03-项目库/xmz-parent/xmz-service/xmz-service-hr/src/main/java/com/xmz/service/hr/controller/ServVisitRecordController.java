package com.xmz.service.hr.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.common.utils.ResultMap;
import com.xmz.service.hr.entity.pojo.*;
import com.xmz.service.hr.entity.vo.HrBranchesQuery;
import com.xmz.service.hr.entity.vo.ServVisitRecordQuery;
import com.xmz.service.hr.service.IServVisitRecordService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 回访记录 前端控制器
 * </p>
 *
 * @author hwb
 * @since 2021-06-04
 */
@RestController
@RequestMapping("/serviceHr/serv-visit-record")
public class ServVisitRecordController {
    @Autowired
    private IServVisitRecordService iServVisitRecordService;
    //分页查询检点信息
    @PostMapping("page/{current}/{size}")
    public ResultMap myPage(
            @PathVariable Long current,
            @PathVariable Long size,
            @RequestBody ServVisitRecordQuery servVisitRecordQuery) {
        System.out.println(current+"---"+size+"---"+ servVisitRecordQuery);
        Page<HuiFang> huiFangPage = new Page<>(current, size);
        iServVisitRecordService.myPage(huiFangPage, servVisitRecordQuery);
        return ResultMap.getOKInstance()
                .putData("rows",huiFangPage.getRecords())
                .putData("pages",huiFangPage.getPages())
                .putData("total",huiFangPage.getTotal())
                .putData("current",huiFangPage.getCurrent())
                .putData("size",huiFangPage.getSize());
    }

    //下拉框拿大区值
    @ApiOperation(value = "查询全部大区")
    @GetMapping("queryall")
    public ResultMap queryall(){
        List<HrOrg> list=iServVisitRecordService.listAll();
        return ResultMap.getOKInstance().putData("list",list);
    }

    //下拉框拿区域值
    @ApiOperation(value = "查询全部区域")
    @PostMapping("/queryallarea")
    public ResultMap queryallarea(
            @ApiParam(name = "hrOrg",value = "条件对象",required = false)
            @RequestBody HrOrg hrOrg){
        List<HrArea> listarea=iServVisitRecordService.arealistAll(hrOrg);
        return ResultMap.getOKInstance().putData("listarea",listarea);
    }
    //下拉框拿区域值
    @ApiOperation(value = "查询全部门店")
    @PostMapping("/queryallbranches")
    public ResultMap queryallbranches(
            @ApiParam(name = "hrbranchesQuery",value = "条件对象",required = false)
            @RequestBody HrBranchesQuery hrBranchesQuery){
        System.out.println("-----"+hrBranchesQuery);
        List<HrBranches> listbranches=iServVisitRecordService.brancheslistAll(hrBranchesQuery);
        return ResultMap.getOKInstance().putData("listbranches",listbranches);
    }
}


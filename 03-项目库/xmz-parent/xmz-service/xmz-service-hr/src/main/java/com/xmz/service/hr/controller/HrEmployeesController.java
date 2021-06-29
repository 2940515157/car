package com.xmz.service.hr.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.common.utils.Result;
import com.xmz.common.utils.ResultMap;
import com.xmz.service.hr.entity.pojo.HrEmployees;
import com.xmz.service.hr.entity.pojo.HrEmployeesAll;
import com.xmz.service.hr.entity.pojo.SysAccount;
import com.xmz.service.hr.entity.vo.HrEmployeesQuery;
import com.xmz.service.hr.service.IHrEmployeesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.http.nio.protocol.NHttpRequestHandlerRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 员工信息 前端控制器
 * </p>
 *
 * @author hyc
 * @since 2021-05-24
 */
@RestController
@RequestMapping("/serviceHr/employees")
@CrossOrigin
public class HrEmployeesController {
    @Autowired
    private IHrEmployeesService iHrEmployeesService;



    //分页查询员工信息
    @PostMapping("pageQuery/{current}/{size}")
    public ResultMap myPage(
            @PathVariable Long current,
            @PathVariable Long size,
            @RequestBody HrEmployeesQuery hrEmployeesQuery) {
        System.out.println(current+"---"+size);
        Page<HrEmployeesAll> hrEmployeesBrandPage = new Page<>(current, size);
        iHrEmployeesService.myPage(hrEmployeesBrandPage, hrEmployeesQuery);
        return ResultMap.getOKInstance()
                .putData("rows",hrEmployeesBrandPage.getRecords())
                .putData("pages",hrEmployeesBrandPage.getPages())
                .putData("total",hrEmployeesBrandPage.getTotal())
                .putData("current",hrEmployeesBrandPage.getCurrent())
                .putData("size",hrEmployeesBrandPage.getSize());
    }
    //添加
    @PostMapping("addHrEmp")
    @ApiOperation("添加")
    public ResultMap add(
            @ApiParam(name = "hrEmployees",value = "车系",required = true)
            @RequestBody HrEmployees hrEmployees){
        hrEmployees.setVersion(new BigDecimal("1"));
        hrEmployees.setCreatedDate(new Date());
        hrEmployees.setCreatedBy("admin");
        hrEmployees.setStatus("jobing");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        hrEmployees.setEmpCode("EMPN"+sdf.format(new Date()));
        boolean flag = iHrEmployeesService.save(hrEmployees);
        if(flag)

            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }

    //修改
    @PostMapping("updateHrEmp")
    @ApiOperation("修改")
    public ResultMap update(
            @ApiParam(name = "hrEmployees",value = "人员信息",required = true)
            @RequestBody HrEmployees hrEmployees){
        hrEmployees.setUpdatedDate(new Date());
        System.out.println(hrEmployees.getEmpUuid()+"---------------------");
        hrEmployees.setCreatedBy("admin");
        boolean flag = iHrEmployeesService.updateById(hrEmployees);
        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }


    //数据回显
    // 根据id查询
    @GetMapping("queryById/{empUuid}")
    @ApiOperation("根据Id查询信息")
    public ResultMap queryById2(
            @ApiParam(name = "empUuid",value = "车系id",required = true)
            @PathVariable String empUuid){
        HrEmployees hrEmployees = iHrEmployeesService.getById(empUuid);
        return ResultMap.getOKInstance()
                .putData("hrEmployees",hrEmployees);
    }



    //设置离职或取消
    @PostMapping("setStatus")
    @ApiOperation("设置离职状态")
    public ResultMap setStatus(@RequestBody HrEmployees hrEmployees){
        int row = iHrEmployeesService.setStatus(hrEmployees);
        if (row > 0)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }

    @ApiOperation("查询所有领卡人")
    @PostMapping("/queryAllSa")
    public ResultMap queryAllSa(@RequestBody HrEmployees hrEmployees) {
        List<HrEmployees> list = iHrEmployeesService.queryName(hrEmployees);
        return ResultMap.getOKInstance()
                .putData("list", list);
    }



}


package com.xmz.br.basedata.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.br.basedata.pojo.entity.HrEmployees;
import com.xmz.br.basedata.pojo.vo.*;
import com.xmz.br.basedata.service.HrEmployeesService;
import com.xmz.common.utils.Result;
import com.xmz.common.utils.ResultMap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 * 员工信息 前端控制器
 * </p>
 *
 * @author 驰骋
 * @since 2021-06-02
 */
@Api(description = "总店-人力资源-员工管理")
@RestController
@RequestMapping("/hrmanagementservice/hr-employees")
public class HrEmployeesController {

    @Autowired
    HrEmployeesService hrEmployeesService;

    @ApiOperation("条件分页查询员工信息")
    @PostMapping("/page/{current}/{size}")
    public ResultMap query(
            @ApiParam(name = "current",value = "页码")
            @PathVariable Long current,
            @ApiParam(name = "size",value = "页大小")
            @PathVariable Long size,
            @ApiParam(name = "hrEmployeesQuery",value = "查询条件")
            @RequestBody HrEmployeesQuery hrEmployeesQuery) {
        Page<HrEmployeesPage> hrEmployeesPagePage = new Page<>(current, size);
        hrEmployeesQuery.setBranchesId(hrEmployeesService.queryBrUuidByShop(hrEmployeesQuery.getBranchesId()));
        hrEmployeesService.query(hrEmployeesPagePage, hrEmployeesQuery);
        /*return R.ok().data("rows", hrEmployeesPagePage.getRecords())
                .data("total", hrEmployeesPagePage.getTotal())
                .data("pages", hrEmployeesPagePage.getPages())
                .data("current", hrEmployeesPagePage.getCurrent())
                .data("size", hrEmployeesPagePage.getSize());*/
        return ResultMap.getOKInstance().putData("rows", hrEmployeesPagePage.getRecords())
                .putData("total", hrEmployeesPagePage.getTotal())
                .putData("pages", hrEmployeesPagePage.getPages())
                .putData("current", hrEmployeesPagePage.getCurrent())
                .putData("size", hrEmployeesPagePage.getSize());
    }

    // 根据主键id进行查询
    @ApiOperation(value = "根据主键id查询")
    @GetMapping("queryById/{id}")
    public Result<HrEmployees> queryById(
            @ApiParam(name="id", value = "员工id", required = true)
            @PathVariable String id) {
        HrEmployees hrEmployees = hrEmployeesService.getById(id);
       // return R.ok().data("hrEmployees", hrEmployees);
        return Result.getOKInstance("hrEmployees", hrEmployees);
    }

    // 查询全部员工信息
    @ApiOperation(value = "查询全部员工信息")
    @GetMapping("queryAll")
    public Result<Object> queryAll() {
        int count = hrEmployeesService.queryTodayCount();
        System.out.println(count);

        QueryWrapper<HrEmployees> hrEmployeesQueryWrapper = new QueryWrapper<>();
        hrEmployeesQueryWrapper.eq("IS_ACTIVE","Y");
        List<HrEmployees> hrEmployeesList = hrEmployeesService.list(hrEmployeesQueryWrapper);
        //return R.ok().data("hrEmployeesList", hrEmployeesList);
        return Result.getOKInstance("hrEmployeesList", hrEmployeesList);
    }

    // 添加员工
    @ApiOperation(value = "添加员工")
    @PostMapping("insert")
    public Result<Object> insert(
            @ApiParam(name = "hrEmployees", value = "区域对象", required = true)
            @RequestBody HrEmployees hrEmployees) {
        //自动生成uuid
        String uuid = UUIDUtil.getUUID();
        //设置主键id
        hrEmployees.setEmpUuid(uuid);
        //设置是否有效
        hrEmployees.setIsActive("Y");
        //设置创建时间
        hrEmployees.setCreatedDate(new Date());
        //设置版本
        hrEmployees.setVersion(BigDecimal.valueOf(0));
        //设置拼音
        hrEmployees.setPinyin(PinYin.ToPinyin(hrEmployees.getEmpName()));
        //设置员工状态 默认为在职
        hrEmployees.setStatus("jobing");

        //设置员工门店
        hrEmployees.setBrUuid(hrEmployeesService.queryBrUuidByShop(hrEmployees.getBrUuid()));

        //查询出当天有几条添加的数据
        int count = hrEmployeesService.queryTodayCount();
        AtomicInteger val = new AtomicInteger(count);//上一个数字
        int nextVal = val.incrementAndGet();
        String format = String.format("%04d", nextVal);
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
        String newsNo = "EMPN"+df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳

        //设置员工编号
        hrEmployees.setEmpCode(newsNo+format);

        boolean flag = hrEmployeesService.save(hrEmployees);
        if(flag)
            return Result.getOKInstance();
        return Result.getErrorInstance();
    }

    // 修改员工
    @ApiOperation(value = "修改员工")
    @PostMapping("update")
    public Result<Object> update(
            @ApiParam(name = "HrEmployees", value = "员工对象", required = true)
            @RequestBody HrEmployees HrEmployees) {

        //设置修改日期
        HrEmployees.setUpdatedDate(new Date());
        //设置版本号+1
        BigDecimal bigDecimal = new BigDecimal(1);
        HrEmployees.setVersion(HrEmployees.getVersion().add(bigDecimal));
        //设置员工门店
        HrEmployees.setBrUuid(hrEmployeesService.queryBrUuidByShop(HrEmployees.getBrUuid()));

        boolean flag = hrEmployeesService.updateById(HrEmployees);
        if(flag)
            return Result.getOKInstance();
        return Result.getErrorInstance();
    }

    // 删除员工
    @ApiOperation("根据ID删除员工")
    @DeleteMapping("{id}")
    public Result<Object> delete(
            @ApiParam(name = "id", value = "员工id", required = true)
            @PathVariable String id) {
        boolean flag = hrEmployeesService.removeById(id);
        if(flag)
            return Result.getOKInstance();
        return Result.getErrorInstance();
    }

    // 设置员工离职
    @ApiOperation(value = "设置员工离职")
    @PostMapping("leaveJob")
    public Result<Object> leaveJob(@ApiParam(name = "HrEmployees", value = "员工对象", required = true)
                      @RequestBody HrEmployees hrEmployees) {
        //设置状态为离职
        hrEmployees.setStatus("leaveJob");
        //离职时间、离职原因 由前端操作

        boolean flag = hrEmployeesService.updateById(hrEmployees);
        if(flag)
            return Result.getOKInstance();
        return Result.getErrorInstance();
    }

    // 撤销员工离职
    @ApiOperation(value = "撤销员工离职")
    @PostMapping("unLeaveJob/{id}")
    public Result<Object> unLeaveJob(@ApiParam(name = "id", value = "员工id", required = true)
                        @PathVariable String id) {

        HrEmployees hrEmployees = new HrEmployees();
        //设置员工id
        hrEmployees.setEmpUuid(id);
        //设置状态为在职
        hrEmployees.setStatus("jobing");

        boolean flag = hrEmployeesService.updateById(hrEmployees);
        if(flag)
            return Result.getOKInstance();
        return Result.getErrorInstance();
    }


    //查询 车间 员工
    @PostMapping("queryBrandEmp/{current}/{size}/{tid}/{bid}")
    public ResultMap queryBrandEmp( @ApiParam(name = "current",value = "页码")
                                @PathVariable Long current,
                            @ApiParam(name = "size",value = "页大小")
                                @PathVariable Long size,
                            @ApiParam(name = "hrEmployeesQuery",value = "查询条件")
                                @PathVariable("tid") String tid ,
                                @PathVariable("bid")String bid){

        Page<HrEmployeesPage> hrEmployeesPage=new Page<>(current,size);
        hrEmployeesService.queryBrandEmp(hrEmployeesPage,tid,bid);

        /*return R.ok().data("rows", hrEmployeesPage.getRecords())
                .data("total", hrEmployeesPage.getTotal())
                .data("pages", hrEmployeesPage.getPages())
                .data("current", hrEmployeesPage.getCurrent())
                .data("size", hrEmployeesPage.getSize());*/
        return ResultMap.getOKInstance().putData("rows", hrEmployeesPage.getRecords())
                .putData("total", hrEmployeesPage.getTotal())
                .putData("pages", hrEmployeesPage.getPages())
                .putData("current", hrEmployeesPage.getCurrent())
                .putData("size", hrEmployeesPage.getSize());
    }


    // 查询出还没有账号的员工
    @ApiOperation(value = "查询出还没有账号的员工")
    @PostMapping("queryNoAccEmps/{current}/{size}")
    public ResultMap queryNoAccEmps(@ApiParam(name = "current",value = "页码")
                            @PathVariable Long current,
                            @ApiParam(name = "size",value = "页大小")
                            @PathVariable Long size,
                            @RequestBody HrEmployeesQuery hrEmployeesQuery) {

        Page<HrEmployeesPage> hrEmployeesPagePage = new Page<>(current,size);
        hrEmployeesService.queryNoAccEmps(hrEmployeesPagePage,hrEmployeesQuery);
        /*return R.ok().data("rows", hrEmployeesPagePage.getRecords())
                .data("total", hrEmployeesPagePage.getTotal())
                .data("pages", hrEmployeesPagePage.getPages())
                .data("current", hrEmployeesPagePage.getCurrent())
                .data("size", hrEmployeesPagePage.getSize());*/
        return ResultMap.getOKInstance().putData("rows", hrEmployeesPagePage.getRecords())
                .putData("total", hrEmployeesPagePage.getTotal())
                .putData("pages", hrEmployeesPagePage.getPages())
                .putData("current", hrEmployeesPagePage.getCurrent())
                .putData("size", hrEmployeesPagePage.getSize());
    }
}


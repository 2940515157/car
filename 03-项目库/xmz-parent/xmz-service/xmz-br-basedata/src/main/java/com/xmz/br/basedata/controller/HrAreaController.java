package com.xmz.br.basedata.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.br.basedata.pojo.entity.HrArea;
import com.xmz.br.basedata.pojo.vo.*;
import com.xmz.br.basedata.service.HrAreaService;
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

/**
 * <p>
 * 区域表 前端控制器
 * </p>
 *
 * @author 驰骋
 * @since 2021-05-28
 */
@Api(description = "总店-人力资源-区域管理")
@RestController
@RequestMapping("/hrmanagementservice/hr-area")
@CrossOrigin
public class HrAreaController {

    @Autowired
    HrAreaService hrAreaService;

    @ApiOperation("条件分页查询区域信息")
    @PostMapping("/page/{current}/{size}")
    public ResultMap query(
            @ApiParam(name = "current",value = "页码")
            @PathVariable Long current,
            @ApiParam(name = "size",value = "页大小")
            @PathVariable Long size,
            @ApiParam(name = "hrAreaQuery",value = "查询条件")
            @RequestBody HrAreaQuery hrAreaQuery) {
        Page<HrAreaPage> hrAreaPagePage = new Page<>(current, size);
        hrAreaService.query(hrAreaPagePage, hrAreaQuery);
        /*return R.ok().data("rows", hrAreaPagePage.getRecords())
                .data("total", hrAreaPagePage.getTotal())
                .data("pages", hrAreaPagePage.getPages())
                .data("current", hrAreaPagePage.getCurrent())
                .data("size", hrAreaPagePage.getSize());*/
        return ResultMap.getOKInstance().putData("rows", hrAreaPagePage.getRecords())
                .putData("total", hrAreaPagePage.getTotal())
                .putData("pages", hrAreaPagePage.getPages())
                .putData("current", hrAreaPagePage.getCurrent())
                .putData("size", hrAreaPagePage.getSize());

    }

    // 根据主键id进行查询
    @ApiOperation(value = "根据主键id查询")
    @GetMapping("queryById/{id}")
    public ResultMap queryById(
            @ApiParam(name="id", value = "区域id", required = true)
            @PathVariable String id) {
        HrArea hrArea = hrAreaService.getById(id);
        //return R.ok().data("hrArea", hrArea);
        return ResultMap.getOKInstance().putData("hrArea", hrArea);
    }

    // 查询全部区域信息
    @ApiOperation(value = "查询全部区域信息")
    @GetMapping("queryAll")
    public ResultMap queryAll() {
        QueryWrapper<HrArea> hrAreaQueryWrapper = new QueryWrapper<>();
        hrAreaQueryWrapper.eq("IS_ACTIVE","Y");
        List<HrArea> hrAreaList = hrAreaService.list(hrAreaQueryWrapper);
        //return R.ok().data("hrAreaList", hrAreaList);
        return ResultMap.getOKInstance().putData("hrAreaList", hrAreaList);
    }

    // 添加区域
    @ApiOperation(value = "添加区域")
    @PostMapping("insert")
    public Result<Object> insert(
            @ApiParam(name = "hrArea", value = "区域对象", required = true)
            @RequestBody HrArea hrArea) {
        //自动生成uuid
        String uuid = UUIDUtil.getUUID();
        //设置主键id
        hrArea.setAreaUuid(uuid);
        //设置是否有效
        hrArea.setIsActive("Y");
        //设置创建时间
        hrArea.setCreatedDate(new Date());
        //设置版本
        hrArea.setVersion(BigDecimal.valueOf(0));
        //设置拼音
        hrArea.setPinyin(PinYin.ToPinyin(hrArea.getAreaName()));

        boolean flag = hrAreaService.save(hrArea);
        if(flag)
            return Result.getOKInstance();
        return Result.getErrorInstance();
    }

    // 修改区域
    @ApiOperation(value = "修改区域")
    @PostMapping("update")
    public Result<Object> update(
            @ApiParam(name = "hrArea", value = "讲师对象", required = true)
            @RequestBody HrArea hrArea) {

        //设置修改日期
        hrArea.setUpdatedDate(new Date());
        //设置版本号+1
        BigDecimal bigDecimal = new BigDecimal(1);
        hrArea.setVersion(hrArea.getVersion().add(bigDecimal));

        boolean flag = hrAreaService.updateById(hrArea);
        if(flag)
            return Result.getOKInstance();
        return Result.getErrorInstance();
    }

    // 删除区域
    @ApiOperation("根据ID删除区域")
    @DeleteMapping("{id}")
    public Result delete(
            @ApiParam(name = "id", value = "区域编号", required = true)
            @PathVariable String id) {
        boolean flag = hrAreaService.removeById(id);
        if(flag)
            return Result.getOKInstance();
        return Result.getErrorInstance();
    }

    /**
     * 根据大区查出所有的区域
     * @return
     */
    @ApiOperation("根据大区查出所有的区域")
    @GetMapping("/queryAllHrAreas/{orgUuid}")
    public ResultMap queryAllHrAreas(
            @ApiParam(name = "orgUuid", value = "大区编号")
            @PathVariable String orgUuid){
        List<HrArea> list=hrAreaService.queryAllHrAreas(orgUuid);
        return ResultMap.getOKInstance().putData("list",list);
    }


    /**
     * 根据区域查出大区
     * @return
     */
    @ApiOperation("根据区域查出大区")
    @PostMapping("queryAllOrgUuid")
    public ResultMap queryAllOrgUuid(@RequestBody HrArea hrArea){
        String areaUuid=hrArea.getAreaUuid();
        HrArea hrArea1 =hrAreaService.queryAllOrgUuid(areaUuid);
        return ResultMap.getOKInstance().putData("list",hrArea1);
    }
}


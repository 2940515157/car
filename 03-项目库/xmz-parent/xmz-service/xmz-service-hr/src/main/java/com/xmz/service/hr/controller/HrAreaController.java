package com.xmz.service.hr.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mysql.cj.util.StringUtils;
import com.xmz.common.utils.Result;
import com.xmz.common.utils.ResultMap;
import com.xmz.service.hr.entity.pojo.HrArea;
import com.xmz.service.hr.entity.pojo.HrAreaPage;
import com.xmz.service.hr.entity.pojo.HrOrg;
import com.xmz.service.hr.entity.vo.HrAreaQuery;
import com.xmz.service.hr.service.IHrAreaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 区域表 前端控制器
 * </p>
 *
 * @author lm
 * @since 2021-06-01
 */
@RestController
@RequestMapping("/serviceHr/hrArea")
@CrossOrigin
public class HrAreaController {

    @Autowired
    public IHrAreaService iHrAreaService;

    //根据大区查询区域
    @PostMapping("queryallQuYuByOrgUuid/{id}")
    @ApiOperation("根据大区查询区域")
    public ResultMap queryallQuYuByOrgUuid(
                @PathVariable String id) {
        List<HrArea> list = iHrAreaService.queryallQuYu(id);
        return ResultMap.getOKInstance().putData("hrArea",list);
    }

    // 查询全部区域信息
    @ApiOperation(value = "查询全部区域信息")
    @GetMapping("queryallQuYu")
    public ResultMap queryallQuYu(){
        List<HrArea> list = iHrAreaService.list();
        return ResultMap.getOKInstance().putData("list",list);

    }

    //分页查询区域信息
    @PostMapping("page/{current}/{size}")
    @ApiOperation("分页查询区域信息")
    public ResultMap myPage(
            @PathVariable Long current,
            @PathVariable Long size,
            @RequestBody HrAreaQuery hrAreaQuery) {
        System.out.println(current+"---"+size);
        Page<HrAreaPage> hrAreaPage = new Page<>(current, size);
        iHrAreaService.myPage(hrAreaPage, hrAreaQuery);
        return ResultMap.getOKInstance()
                .putData("rows",hrAreaPage.getRecords())
                .putData("pages",hrAreaPage.getPages())
                .putData("total",hrAreaPage.getTotal())
                .putData("current",hrAreaPage.getCurrent())
                .putData("size",hrAreaPage.getSize());
    }

    // 查询全部区域信息
    @ApiOperation(value = "查询全部区域信息")
    @GetMapping("queryAll")
    public ResultMap queryall(){
        //List<HrOrg> hrAreaList = iHrAreaService.lis;
        //List<HrArea> list = iHrAreaService.list();
        List<HrOrg> list = iHrAreaService.listAll();
        return ResultMap.getOKInstance().putData("list",list);

    }

    //添加区域信息
    @PostMapping("add")
    @ApiOperation("添加区域")
    public ResultMap add(
            @ApiParam(name = "hrArea",value = "区域对象",required = true)
            @RequestBody HrArea hrArea){

        boolean flag = iHrAreaService.save(hrArea);

        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }

    //删除区域信息
    //AREA_UUID
    @DeleteMapping("{areaUuid}")
    @ApiOperation("删除区域")
    public ResultMap delete(
            @ApiParam(name = "areaUuid",value = "区域id",required = true)
            @PathVariable String areaUuid){

        boolean flag = iHrAreaService.removeById(areaUuid);

        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }

    //修改区域信息
    @PostMapping("update")
    @ApiOperation("修改区域")
    public ResultMap update(
            @ApiParam(name = "hrArea",value = "区域对象",required = true)
            @RequestBody HrArea hrArea){

        boolean flag = iHrAreaService.updateById(hrArea);

        if(flag)
            return ResultMap.getOKInstance();
        return ResultMap.getErrorInstance();
    }

    // 根据id查询区域信息
    @GetMapping("queryById/{areaUuid}")
    @ApiOperation("根据Id查询区域信息")
    public ResultMap queryById(
            @ApiParam(name = "areaUuid",value = "区域id",required = true)
            @PathVariable String areaUuid){

        HrArea hrArea = iHrAreaService.getById(areaUuid);

        return ResultMap.getOKInstance()
                .putData("hrArea",hrArea);
    }



    /**
     * 根据大区查出所有的区域
     * @return
     */
    @ApiOperation("根据大区查出所有的区域")
    @PostMapping("queryAllHrAreas")
    public ResultMap queryAllHrAreas(@RequestBody HrArea hrArea){
        String orgUuid=hrArea.getOrgUuid();
        List<HrArea> list=iHrAreaService.queryAllHrAreas(orgUuid);
        return ResultMap.getOKInstance()
                .putData("list",list);
    }


    /**
     * 获取根据大区ID获取区域，以选项封装
     * @author 罗俊
     * @return <code>[{id: "foo", label: "FOO"},{...}]</code>
     */
    @PostMapping("/getAreaOptions/{orgId}")
    public Result<Object> getAreaOptions(@PathVariable String orgId){
        return Result.getOKInstance(iHrAreaService.listMaps(
                new QueryWrapper<HrArea>()
                .select("AREA_UUID id","AREA_NAME label")
                .eq(!"-1".equals(orgId),"ORG_UUID",orgId)
        ));
    }

    /**
     * 根据大区查出所有的区域
     * @return
     */
    @ApiOperation("根据大区查出所有的区域")
    @PostMapping("queryAllOrgUuid")
    public ResultMap queryAllOrgUuid(@RequestBody HrArea hrArea){
        String areaUuid=hrArea.getAreaUuid();
        HrArea hrArea1 =iHrAreaService.queryAllOrgUuid(areaUuid);
        return ResultMap.getOKInstance()
                .putData("list",hrArea1);
    }

}


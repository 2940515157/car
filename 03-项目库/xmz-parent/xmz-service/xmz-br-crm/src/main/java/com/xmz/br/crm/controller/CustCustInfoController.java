package com.xmz.br.crm.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.br.crm.entity.pojo.CustCustInfo;
import com.xmz.br.crm.entity.vo.CustInfoQueryParam;
import com.xmz.br.crm.entity.vo.SaInfo;
import com.xmz.br.crm.service.DictFeignService;
import com.xmz.br.crm.service.ICustCustInfoService;
import com.xmz.common.utils.Result;
import com.xmz.common.utils.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 客户信息 前端控制器
 * </p>
 *
 * @author weikang
 * @since 2021-06-08
 */
@RestController
@RequestMapping("/brCrm/cust-cust-info")
public class CustCustInfoController {

    @Autowired
    private ICustCustInfoService service;

    @Autowired
    DictFeignService dictFeignService;


    @PostMapping("/queryPage/{current}/{size}/{shop}")
    public ResultMap queryPage(@RequestBody CustInfoQueryParam queryParams,
                               @PathVariable Integer current,
                               @PathVariable Integer size,
                               @PathVariable String shop) {
        Page<CustCustInfo> page = new Page<>(current, size);
        service.queryPage(page,queryParams,shop);
        return ResultMap.getOKInstance().putData("rows", page.getRecords())
                .putData("current", page.getCurrent())
                .putData("pages", page.getPages())
                .putData("size", page.getSize())
                .putData("total", page.getTotal());
    }

    @GetMapping("queryById/{custUuid}")
    public Result<CustCustInfo> queryById(@PathVariable String custUuid){
        return Result.getOKInstance(service.getById(custUuid));
    }

    @PostMapping("/queryList/{shop}")
    public Result<List<CustCustInfo>> queryList(@RequestBody CustInfoQueryParam queryParam,
                                                @PathVariable String shop){
        return Result.getOKInstance(service.queryList(queryParam,shop));
    }

    //根据门店查sa
    @GetMapping("/querySaInfoList/{shop}")
    public Result<List<SaInfo>> querySaInfoList(@PathVariable String shop){
        return Result.getOKInstance(service.querySaInfoList(shop));
    }

    //增加
    @PostMapping("/add")
    public Result<Object> add(@RequestBody CustCustInfo custCustInfo){
        custCustInfo.setVersion(new BigDecimal(1.0));
        if (service.add(custCustInfo)>0)
            return Result.getOKInstance("添加成功");
        return Result.getErrorInstance("添加失败");
    }

    //修改
    @PostMapping("/update")
    public Result<Object> update(@RequestBody CustCustInfo custCustInfo){
        if (service.updateById(custCustInfo))
            return Result.getOKInstance("添加成功");
        return Result.getErrorInstance("添加失败");
    }

    //删除
    @DeleteMapping("/delete/{custUuid}")
    public Result<Object> delete(@PathVariable String custUuid){
        if (service.removeById(custUuid))
            return Result.getOKInstance("添加成功");
        return Result.getErrorInstance("添加失败");
    }

    //查所有保养类型
    @GetMapping("/queryDict/{dicttypeid}")
    public Result<List> queryTypeByIdList(@PathVariable String dicttypeid){
        return dictFeignService.queryTypeByIdList(dicttypeid);
    }

}


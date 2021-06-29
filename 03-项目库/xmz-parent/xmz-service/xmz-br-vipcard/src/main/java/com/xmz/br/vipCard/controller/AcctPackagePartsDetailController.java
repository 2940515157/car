package com.xmz.br.vipCard.controller;


import com.xmz.br.vipCard.entity.pojo.HrEmployees;
import com.xmz.br.vipCard.entity.pojo.MyAcctPackagePartsDetail;
import com.xmz.br.vipCard.service.IAcctPackagePartsDetailService;
import com.xmz.common.utils.ResultMap;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 套餐配件明细 前端控制器
 * </p>
 *
 * @author zt
 * @since 2021-06-21
 */
@RestController
@RequestMapping("/brVipcard/acct-package-parts-detail")
public class AcctPackagePartsDetailController {
    @Autowired
    IAcctPackagePartsDetailService iAcctPackagePartsDetailService;

    @ApiOperation("根据套餐id查询配件信息")
    @PostMapping("/list/{apUuid}")
    public ResultMap list(@PathVariable String apUuid){
        List<MyAcctPackagePartsDetail> list= iAcctPackagePartsDetailService.list(apUuid);
        return ResultMap.getOKInstance()
                .putData("list",list);
    }
}


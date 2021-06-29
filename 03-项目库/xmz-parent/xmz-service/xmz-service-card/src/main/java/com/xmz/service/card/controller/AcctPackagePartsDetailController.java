package com.xmz.service.card.controller;



import com.xmz.common.utils.ResultMap;
import com.xmz.service.card.entity.vo.MyAcctPackagePartsDetail;
import com.xmz.service.card.service.IAcctPackagePartsDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 套餐配件明细 前端控制器
 * </p>
 *
 * @author liujiaxi
 * @since 2021-06-18
 */
@Api("套餐配件")
@RestController
@CrossOrigin
@RequestMapping("/serviceCard/acct-package-parts-detail")
public class AcctPackagePartsDetailController {
    @Autowired
    IAcctPackagePartsDetailService  iAcctPackagePartsDetailService;


    @ApiOperation("通过套餐编号查询套餐配件")
    @GetMapping("/getApUuid/{apUuid}")
    public ResultMap getApUuid(
            @ApiParam(name = "apUuid",value = "套餐编号")
            @PathVariable String apUuid) {
        List<MyAcctPackagePartsDetail> acctPackagePartsDetails = iAcctPackagePartsDetailService.getApUuid(apUuid);

        return ResultMap.getOKInstance().putData("acctPackagePartsDetails",acctPackagePartsDetails);
    }

}


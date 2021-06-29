package com.xmz.br.vipCard.controller;


import com.xmz.br.vipCard.entity.pojo.MyAcctCustCard;
import com.xmz.br.vipCard.entity.pojo.MyAcctPackageDetail;
import com.xmz.br.vipCard.entity.vo.AcctCustCardQuery;
import com.xmz.br.vipCard.service.IAcctPackageDetailService;
import com.xmz.common.utils.ResultMap;
import io.swagger.annotations.ApiOperation;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 套餐服务明细 前端控制器
 * </p>
 *
 * @author zt
 * @since 2021-06-16
 */
@RestController
@RequestMapping("/brVipcard/acct-package-detail")
public class AcctPackageDetailController {
    @Autowired
    IAcctPackageDetailService iAcctPackageDetailService;

    @ApiOperation("根据id查询")
    @PostMapping("/queryApUuid/{apUuid}")
    public ResultMap queryApUuid(@PathVariable String apUuid){
        List<MyAcctPackageDetail> list= iAcctPackageDetailService.queryApUuid(apUuid);
        return ResultMap.getOKInstance()
                .putData("list",list);

    }

}


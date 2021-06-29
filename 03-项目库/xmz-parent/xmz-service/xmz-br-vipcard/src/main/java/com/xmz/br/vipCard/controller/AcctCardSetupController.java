package com.xmz.br.vipCard.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.br.vipCard.entity.pojo.AcctCardSetup;
import com.xmz.br.vipCard.entity.pojo.MyAcctBrCard;
import com.xmz.br.vipCard.entity.pojo.MyAcctCardSetup;
import com.xmz.br.vipCard.entity.pojo.MyCustCustInfo;
import com.xmz.br.vipCard.entity.vo.AcctBrCardQuery;
import com.xmz.br.vipCard.entity.vo.AcctCardSetupQuery;
import com.xmz.br.vipCard.entity.vo.CustCustInfoQuery;
import com.xmz.br.vipCard.service.IAcctCardSetupService;
import com.xmz.common.utils.ResultMap;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 储值卡产品定义 前端控制器
 * </p>
 *
 * @author zt
 * @since 2021-06-09
 */
@RestController
@RequestMapping("/brVipcard/acct-card-setup")
public class AcctCardSetupController {
    @Autowired
    IAcctCardSetupService iAcctCardSetupService;

    @ApiOperation("获取所有上架的储值卡")
    @PostMapping("/listAcs")
    public ResultMap listAcs(@RequestBody AcctCardSetupQuery acctCardSetupQuery){
        List<MyAcctCardSetup> list= iAcctCardSetupService.listAcs(acctCardSetupQuery);
        return ResultMap.getOKInstance()
                .putData("list",list);
    }

    @ApiOperation("获取储值卡产品记录列表(条件分页)")
    @PostMapping("/queryPage/{current}/{size}")
    public ResultMap queryPage(@RequestBody AcctCardSetupQuery acctCardSetupQuery,
                               @PathVariable Integer current,
                               @PathVariable Integer size){
        Page<MyAcctCardSetup> page=new Page(current,size);
        iAcctCardSetupService.myPage(page,acctCardSetupQuery);
        return ResultMap.getOKInstance()
                .putData("rows",page.getRecords())
                .putData("pages",page.getPages())
                .putData("total",page.getTotal())
                .putData("current",page.getCurrent())
                .putData("size",page.getSize());
    }

}


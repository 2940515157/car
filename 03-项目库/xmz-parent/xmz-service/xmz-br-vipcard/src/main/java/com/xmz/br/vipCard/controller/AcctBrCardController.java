package com.xmz.br.vipCard.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.br.vipCard.entity.pojo.*;
import com.xmz.br.vipCard.entity.vo.AcctBrCardQuery;
import com.xmz.br.vipCard.entity.vo.AcctCardSetupQuery;
import com.xmz.br.vipCard.entity.vo.CustCustInfoQuery;
import com.xmz.br.vipCard.service.IAcctBrCardService;
import com.xmz.br.vipCard.service.IHrEmployeesService;
import com.xmz.br.vipCard.service.impl.AcctBrCardServiceImpl;
import com.xmz.common.utils.ResultMap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 门店领卡 前端控制器
 * </p>
 *
 * @author zt
 * @since 2021-06-06
 */
@RestController
@RequestMapping("/brVipcard/acct-br-card")
public class AcctBrCardController {
    @Autowired
    IAcctBrCardService iAcctBrCardService;

    @ApiOperation("获取领卡记录列表(条件分页)")
    @PostMapping("/queryPage/{current}/{size}")
    public ResultMap queryPage(@RequestBody AcctBrCardQuery acctBrCardQuery,
                               @PathVariable Integer current,
                               @PathVariable Integer size){
        Page<MyAcctBrCard> page=new Page(current,size);
        iAcctBrCardService.myPage(page,acctBrCardQuery);
        return ResultMap.getOKInstance()
                .putData("rows",page.getRecords())
                .putData("pages",page.getPages())
                .putData("total",page.getTotal())
                .putData("current",page.getCurrent())
                .putData("size",page.getSize());
    }

    @ApiOperation("获取没有发放给客户的卡")
    @PostMapping("/queryCardNo/{current}/{size}")
    public ResultMap queryCardNo(@RequestBody AcctBrCardQuery acctBrCardQuery,
                                 @PathVariable Integer current,
                                 @PathVariable Integer size){
        Page<MyAcctBrCard> page=new Page(current,size);
        iAcctBrCardService.queryByCardNo(page,acctBrCardQuery);
        return ResultMap.getOKInstance()
                .putData("rows",page.getRecords())
                .putData("pages",page.getPages())
                .putData("total",page.getTotal())
                .putData("current",page.getCurrent())
                .putData("size",page.getSize());

    }

    @ApiOperation("获取所有领卡人")
    @PostMapping("/listHe")
    public ResultMap listHe(){
        List<HrEmployees> list= iAcctBrCardService.listHe();
        return ResultMap.getOKInstance()
                .putData("list",list);
    }

}


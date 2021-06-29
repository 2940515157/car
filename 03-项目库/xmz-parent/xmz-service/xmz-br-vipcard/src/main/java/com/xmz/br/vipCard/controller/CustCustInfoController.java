package com.xmz.br.vipCard.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.br.vipCard.entity.pojo.CustVo;
import com.xmz.br.vipCard.entity.pojo.MyAcctCustCard;
import com.xmz.br.vipCard.entity.pojo.MyCustCustInfo;
import com.xmz.br.vipCard.entity.vo.AcctCustCardQuery;
import com.xmz.br.vipCard.entity.vo.CustCustInfoQuery;
import com.xmz.br.vipCard.service.ICustCustInfoService;
import com.xmz.common.utils.ResultMap;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 客户信息 前端控制器
 * </p>
 *
 * @author zt
 * @since 2021-06-04
 */
@RestController
@RequestMapping("/brVipcard/cust-cust-info")
public class CustCustInfoController {
    @Autowired
    ICustCustInfoService iCustCustInfoService;

    @ApiOperation("获取客户与车辆的信息")
    @PostMapping("/queryPhone")
    public ResultMap queryPhone(@RequestBody CustCustInfoQuery custCustInfoQuery){
      List<MyCustCustInfo> list= iCustCustInfoService.queryByPhone(custCustInfoQuery);
        return ResultMap.getOKInstance()
                .putData("list",list);

    }

    @ApiOperation("分页条件查询门店会员")
    @PostMapping("/pageList/{page}/{size}")
    public ResultMap pageList(@ApiParam("页数") @PathVariable long page,
                               @ApiParam("页大小") @PathVariable long size,
                               @ApiParam("对象") @RequestBody CustVo custVo) {
        Page<CustVo> pojoPage = new Page<>(page, size);
        List<CustVo> list = iCustCustInfoService.pageList(pojoPage, custVo);
        return ResultMap.getOKInstance()
                .putData("list", list)
                .putData("total", pojoPage.getTotal());
    }

}


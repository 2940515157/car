package com.xmz.service.card.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.common.utils.ResultMap;
import com.xmz.service.card.entity.pojo.AcctCompositeProduct;
import com.xmz.service.card.entity.vo.MyAcctCompositeProduct;
import com.xmz.service.card.service.IAcctCompositeProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 营销组合产品 前端控制器
 * </p>
 *
 * @author liujiaxi
 * @since 2021-06-07
 */
@Api("营销组合产品")
@RestController
@CrossOrigin
@RequestMapping("/serviceCard/acct-composite-product")
public class AcctCompositeProductController {
    @Autowired
    IAcctCompositeProductService iAcctCompositeProductService;


    @ApiOperation("分页条件查询营销组合产品")
    @PostMapping("queryAllPage/{current}/{size}")
    public ResultMap queryAllPage(@PathVariable Long current,
                                  @PathVariable Long size,
                                  @RequestBody AcctCompositeProduct acctCompositeProduct){
        Page<MyAcctCompositeProduct> brPackagePage = new Page<>(current, size);

        iAcctCompositeProductService.queryAllPage(brPackagePage,acctCompositeProduct);
        return ResultMap.getOKInstance()
                .putData("rows",brPackagePage.getRecords())
                .putData("pages",brPackagePage.getPages())
                .putData("total",brPackagePage.getTotal())
                .putData("current",brPackagePage.getCurrent())
                .putData("size",brPackagePage.getSize());
    }

}


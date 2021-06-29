package com.xmz.service.card.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.card.entity.pojo.AcctCompositeProduct;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmz.service.card.entity.vo.MyAcctCompositeProduct;

/**
 * <p>
 * 营销组合产品 服务类
 * </p>
 *
 * @author liujiaxi
 * @since 2021-06-07
 */
public interface IAcctCompositeProductService extends IService<AcctCompositeProduct> {

    /**
     * 分页条件查询营销组合产品
     * @param pageParam
     * @param acctCompositeProduct
     * @return
     */
    Page<MyAcctCompositeProduct> queryAllPage(Page<MyAcctCompositeProduct> pageParam, AcctCompositeProduct acctCompositeProduct);
}

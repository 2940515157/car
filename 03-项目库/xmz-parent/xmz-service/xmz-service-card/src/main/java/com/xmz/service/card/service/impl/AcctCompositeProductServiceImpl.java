package com.xmz.service.card.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.card.entity.pojo.AcctCompositeProduct;
import com.xmz.service.card.entity.vo.MyAcctCompositeProduct;
import com.xmz.service.card.mapper.AcctCompositeProductMapper;
import com.xmz.service.card.service.IAcctCompositeProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 营销组合产品 服务实现类
 * </p>
 *
 * @author liujiaxi
 * @since 2021-06-07
 */
@Service
public class AcctCompositeProductServiceImpl extends ServiceImpl<AcctCompositeProductMapper, AcctCompositeProduct> implements IAcctCompositeProductService {

    @Autowired(required = false)
    AcctCompositeProductMapper acctCompositeProductMapper;

    /**
     * 套餐产品分页条件查询
     * @param pageParam
     * @param acctCompositeProduct
     * @return
     */
    @Override
    public Page<MyAcctCompositeProduct> queryAllPage(Page<MyAcctCompositeProduct> pageParam, AcctCompositeProduct acctCompositeProduct) {
        return acctCompositeProductMapper.queryAllPage(pageParam,acctCompositeProduct);
    }
}

package com.xmz.service.card.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.card.entity.pojo.AcctCompositeProduct;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmz.service.card.entity.vo.MyAcctCompositeProduct;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 营销组合产品 Mapper 接口
 * </p>
 *
 * @author liujiaxi
 * @since 2021-06-07
 */
public interface AcctCompositeProductMapper extends BaseMapper<AcctCompositeProduct> {

    /**
     * 分页条件查询营销组合产品
     * @param pageParam
     * @param acctCompositeProduct
     * @return
     */
    Page<MyAcctCompositeProduct> queryAllPage(Page<MyAcctCompositeProduct> pageParam,@Param("accp") AcctCompositeProduct acctCompositeProduct);
}

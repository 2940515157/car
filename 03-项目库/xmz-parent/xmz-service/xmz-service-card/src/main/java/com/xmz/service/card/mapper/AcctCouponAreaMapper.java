package com.xmz.service.card.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.card.entity.pojo.AcctCouponArea;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmz.service.card.entity.vo.MyAcctCouponArea;

/**
 * <p>
 * 优惠券使用范围 Mapper 接口
 * </p>
 *
 * @author liujiaxi
 * @since 2021-06-16
 */
public interface AcctCouponAreaMapper extends BaseMapper<AcctCouponArea> {

    //根据优惠券UUID 查询出优惠券使用范围
    Page<MyAcctCouponArea> queryAllPage(Page<MyAcctCouponArea> pageParam, String acUuid);

}

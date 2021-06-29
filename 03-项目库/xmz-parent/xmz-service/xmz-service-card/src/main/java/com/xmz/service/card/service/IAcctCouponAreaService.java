package com.xmz.service.card.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.card.entity.pojo.AcctCouponArea;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmz.service.card.entity.vo.MyAcctCouponArea;

/**
 * <p>
 * 优惠券使用范围 服务类
 * </p>
 *
 * @author liujiaxi
 * @since 2021-06-16
 */
public interface IAcctCouponAreaService extends IService<AcctCouponArea> {

    //根据优惠券UUID 查询出优惠券使用范围
    Page<MyAcctCouponArea> queryAllPage(Page<MyAcctCouponArea> pageParam, String acUuid);

    /**
     *
     * @param acctCouponArea
     * @return
     */
    boolean add(AcctCouponArea acctCouponArea);

}

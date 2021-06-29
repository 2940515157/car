package com.xmz.service.card.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.card.entity.pojo.AcctCoupon;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmz.service.card.entity.vo.AcctCouponQuery;
import com.xmz.service.card.entity.vo.MyAcctCoupon;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 优惠券 Mapper 接口
 * </p>
 *
 * @author ;liujiaxi
 * @since 2021-06-07
 */
public interface AcctCouponMapper extends BaseMapper<AcctCoupon> {
    /**
     * 优惠券分页条件查询
     * @param pageParam
     * @param acctCouponQuery
     * @return
     */
    Page<MyAcctCoupon> queryAllACoup(Page<MyAcctCoupon> pageParam,@Param("accoup")AcctCouponQuery acctCouponQuery);

    //查询门店的可用优惠券
    Page<MyAcctCoupon> queryBrACoup(Page<MyAcctCoupon> pageParam,@Param("accoup")AcctCouponQuery acctCouponQuery);
}

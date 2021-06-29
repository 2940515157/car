package com.xmz.service.card.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.card.entity.pojo.AcctCoupon;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmz.service.card.entity.vo.AcctCouponQuery;
import com.xmz.service.card.entity.vo.MyAcctCoupon;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * <p>
 * 优惠券 服务类
 * </p>
 *
 * @author ;liujiaxi
 * @since 2021-06-07
 */
public interface IAcctCouponService extends IService<AcctCoupon> {

    /**
     * 优惠券分页条件查询
     * @param pageParam
     * @param acctCouponQuery
     * @return
     */
    Page<MyAcctCoupon> queryAllACoup(Page<MyAcctCoupon> pageParam,  AcctCouponQuery acctCouponQuery);

    //查询门店的可用优惠券
    Page<MyAcctCoupon> queryBrACoup(Page<MyAcctCoupon> pageParam,AcctCouponQuery acctCouponQuery);

    /**
     * 添加优惠券
     * @return
     */
    boolean add(Map<String,String> data);


}

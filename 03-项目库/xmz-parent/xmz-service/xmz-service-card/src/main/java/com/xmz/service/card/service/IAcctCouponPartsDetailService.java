package com.xmz.service.card.service;

import com.xmz.service.card.entity.pojo.AcctCouponPartsDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmz.service.card.entity.vo.MyAcctCouponPartsDetail;

import java.util.List;

/**
 * <p>
 * 优惠券配件明细 服务类
 * </p>
 *
 * @author liujiaxi
 * @since 2021-06-16
 */
public interface IAcctCouponPartsDetailService extends IService<AcctCouponPartsDetail> {
    /**
     * 根据卡券id 查询出优惠券服务明细
     * @param acctCouponPartsDetail
     * @return
     */
    List<MyAcctCouponPartsDetail> acdList(AcctCouponPartsDetail acctCouponPartsDetail);

}

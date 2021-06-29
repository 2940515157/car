package com.xmz.br.vipCard.service;



import com.baomidou.mybatisplus.extension.service.IService;
import com.xmz.br.vipCard.entity.pojo.AcctCouponDetailPojo;
import com.xmz.br.vipCard.entity.pojo.CouponServerVo;

import java.util.List;

/**
 * <p>
 * 优惠券服务明细 服务类
 * </p>
 *
 * @author LLY
 * @since 2021-06-02
 */
public interface AcctCouponDetailService extends IService<CouponServerVo> {

    List<CouponServerVo> serverSelectAll(AcctCouponDetailPojo acctCouponDetailPojo);

}

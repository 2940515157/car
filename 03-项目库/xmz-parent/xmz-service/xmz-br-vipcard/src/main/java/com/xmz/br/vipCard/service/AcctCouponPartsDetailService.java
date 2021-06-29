package com.xmz.br.vipCard.service;



import com.baomidou.mybatisplus.extension.service.IService;
import com.xmz.br.vipCard.entity.pojo.AcctCouponPartsDetailPojo;
import com.xmz.br.vipCard.entity.pojo.CouponPartsVo;

import java.util.List;

/**
 * <p>
 * 优惠券配件明细 服务类
 * </p>
 *
 * @author LLY
 * @since 2021-06-03
 */
public interface AcctCouponPartsDetailService extends IService<AcctCouponPartsDetailPojo> {

    List<CouponPartsVo> partsSelectAll(AcctCouponPartsDetailPojo acctCouponPartsDetailPojo);
}

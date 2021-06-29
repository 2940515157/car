package com.xmz.br.vipCard.service;



import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmz.br.vipCard.entity.pojo.BrCouponPojo;
import com.xmz.br.vipCard.entity.pojo.BrCouponVo;

import java.util.List;

/**
 * <p>
 * 门店与优惠券对应关系 服务类
 * </p>
 *
 * @author LLY
 * @since 2021-06-10
 */
public interface BrCouponService extends IService<BrCouponPojo> {

    List<BrCouponVo> pageList(Page<BrCouponVo> pojoPage, BrCouponVo brCouponVo);
}

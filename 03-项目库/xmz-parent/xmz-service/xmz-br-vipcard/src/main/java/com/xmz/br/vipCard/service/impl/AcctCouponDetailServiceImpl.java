package com.xmz.br.vipCard.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xmz.br.vipCard.entity.pojo.AcctCouponDetailPojo;
import com.xmz.br.vipCard.entity.pojo.CouponServerVo;
import com.xmz.br.vipCard.mapper.AcctCouponDetailMapper;
import com.xmz.br.vipCard.service.AcctCouponDetailService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 优惠券服务明细 服务实现类
 * </p>
 *
 * @author LLY
 * @since 2021-06-02
 */
@Service
public class AcctCouponDetailServiceImpl extends ServiceImpl<AcctCouponDetailMapper, CouponServerVo> implements AcctCouponDetailService {

    @Override
    public List<CouponServerVo> serverSelectAll(AcctCouponDetailPojo acctCouponDetailPojo) {
        return baseMapper.serverSelectAll(acctCouponDetailPojo);
    }

}

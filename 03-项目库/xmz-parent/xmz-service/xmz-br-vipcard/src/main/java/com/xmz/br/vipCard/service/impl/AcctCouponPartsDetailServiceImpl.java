package com.xmz.br.vipCard.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xmz.br.vipCard.entity.pojo.AcctCouponPartsDetailPojo;
import com.xmz.br.vipCard.entity.pojo.CouponPartsVo;
import com.xmz.br.vipCard.mapper.AcctCouponPartsDetailMapper;
import com.xmz.br.vipCard.service.AcctCouponPartsDetailService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 优惠券配件明细 服务实现类
 * </p>
 *
 * @author LLY
 * @since 2021-06-03
 */
@Service
public class AcctCouponPartsDetailServiceImpl extends ServiceImpl<AcctCouponPartsDetailMapper, AcctCouponPartsDetailPojo> implements AcctCouponPartsDetailService {

    @Override
    public List<CouponPartsVo> partsSelectAll(AcctCouponPartsDetailPojo acctCouponPartsDetailPojo) {
        return baseMapper.partsSelectAll(acctCouponPartsDetailPojo);
    }
}

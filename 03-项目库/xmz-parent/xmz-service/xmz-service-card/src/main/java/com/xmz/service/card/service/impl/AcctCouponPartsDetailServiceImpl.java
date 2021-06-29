package com.xmz.service.card.service.impl;

import com.xmz.service.card.entity.pojo.AcctCouponPartsDetail;
import com.xmz.service.card.entity.vo.MyAcctCouponPartsDetail;
import com.xmz.service.card.mapper.AcctCouponPartsDetailMapper;
import com.xmz.service.card.service.IAcctCouponPartsDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 优惠券配件明细 服务实现类
 * </p>
 *
 * @author liujiaxi
 * @since 2021-06-16
 */
@Service
public class AcctCouponPartsDetailServiceImpl extends ServiceImpl<AcctCouponPartsDetailMapper, AcctCouponPartsDetail> implements IAcctCouponPartsDetailService {

    @Override
    public List<MyAcctCouponPartsDetail> acdList(AcctCouponPartsDetail acctCouponPartsDetail) {
        return baseMapper.acdList(acctCouponPartsDetail);
    }
}

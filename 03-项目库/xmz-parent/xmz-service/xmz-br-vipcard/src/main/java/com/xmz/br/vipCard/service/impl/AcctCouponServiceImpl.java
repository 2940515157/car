package com.xmz.br.vipCard.service.impl;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xmz.br.vipCard.entity.pojo.AcctCouponPojo;
import com.xmz.br.vipCard.entity.pojo.CouponVo;
import com.xmz.br.vipCard.entity.pojo.MyAcbrStatus;
import com.xmz.br.vipCard.entity.pojo.MyAcctCouponBuyRecordPojo;
import com.xmz.br.vipCard.entity.vo.AcctCouponBuyRecordPojoQuery;
import com.xmz.br.vipCard.mapper.AcctCouponMapper;
import com.xmz.br.vipCard.service.AcctCouponService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 优惠券 服务实现类
 * </p>
 *
 * @author LLY
 * @since 2021-05-27
 */
@Service
public class AcctCouponServiceImpl extends ServiceImpl<AcctCouponMapper, AcctCouponPojo> implements AcctCouponService {

    @Override
    public List<CouponVo> pageList(Page<CouponVo> pojoPage, CouponVo couponVo) {
        return baseMapper.pageList(pojoPage,couponVo);
    }

    @Override
    public Page<MyAcctCouponBuyRecordPojo> myPage(Page<MyAcctCouponBuyRecordPojo> pageParam, AcctCouponBuyRecordPojoQuery acctCouponBuyRecordPojoQuery) {
        return baseMapper.myPage(pageParam,acctCouponBuyRecordPojoQuery);
    }

    @Override
    public Page<MyAcbrStatus> myPage2(Page<MyAcbrStatus> pageParam, AcctCouponBuyRecordPojoQuery acctCouponBuyRecordPojoQuery) {
        return baseMapper.myPage2(pageParam,acctCouponBuyRecordPojoQuery);
    }

    @Override
    public List<MyAcbrStatus> queryAll() {
        return baseMapper.queryAll();
    }

    @Override
    public MyAcbrStatus queryNormal(String acUuid) {
        return baseMapper.queryNormal(acUuid);
    }
}

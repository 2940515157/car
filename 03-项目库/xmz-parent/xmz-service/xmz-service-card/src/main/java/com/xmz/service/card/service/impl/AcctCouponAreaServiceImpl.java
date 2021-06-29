package com.xmz.service.card.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.card.entity.pojo.AcctCouponArea;
import com.xmz.service.card.entity.pojo.BrCoupon;
import com.xmz.service.card.entity.vo.MyAcctCouponArea;
import com.xmz.service.card.mapper.AcctCouponAreaMapper;

import com.xmz.service.card.mapper.BrCouponMapper;
import com.xmz.service.card.service.IAcctCouponAreaService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 优惠券使用范围 服务实现类
 * </p>
 *
 * @author liujiaxi
 * @since 2021-06-16
 */
@Service
public class AcctCouponAreaServiceImpl extends ServiceImpl<AcctCouponAreaMapper, AcctCouponArea> implements IAcctCouponAreaService {

    @Autowired(required = false)
    AcctCouponAreaMapper acctCouponAreaMapper;

    @Autowired(required = false)
    BrCouponMapper brCouponMapper;

    /**
     * 根据优惠券UUID 查询出优惠券使用范围
     * @param pageParam
     * @param acUuid
     * @return
     */
    @Override
    public Page<MyAcctCouponArea> queryAllPage(Page<MyAcctCouponArea> pageParam, String acUuid) {
        return acctCouponAreaMapper.queryAllPage(pageParam,acUuid);
    }


    @Override
    public boolean add(AcctCouponArea acctCouponArea) {

        acctCouponAreaMapper.insert(acctCouponArea);

        //创建门店优惠券
        BrCoupon brCoupon=new BrCoupon();
        brCoupon.setBcUuid(null);
        //门店id
        brCoupon.setBrUuid(acctCouponArea.getPkUuid());
        //优惠券id
        brCoupon.setCouponUuid(acctCouponArea.getAcUuid());
        //版本
        brCoupon.setVersion(acctCouponArea.getVersion());
        brCoupon.setCreatedDate(new Date());
        brCoupon.setIsActive("Y");
        brCoupon.setStatus("init");

        brCouponMapper.insert(brCoupon);

        return true;
    }
}

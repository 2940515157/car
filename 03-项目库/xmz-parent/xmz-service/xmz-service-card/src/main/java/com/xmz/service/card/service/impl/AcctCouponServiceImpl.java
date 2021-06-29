package com.xmz.service.card.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.card.entity.pojo.AcctCoupon;
import com.xmz.service.card.entity.pojo.AcctCouponDetail;
import com.xmz.service.card.entity.vo.AcctCouponQuery;
import com.xmz.service.card.entity.vo.MyAcctCoupon;
import com.xmz.service.card.mapper.AcctCouponDetailMapper;
import com.xmz.service.card.mapper.AcctCouponMapper;
import com.xmz.service.card.service.IAcctCouponService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 优惠券 服务实现类
 * </p>
 *
 * @author ;liujiaxi
 * @since 2021-06-07
 */
@Service
public class AcctCouponServiceImpl extends ServiceImpl<AcctCouponMapper, AcctCoupon> implements IAcctCouponService {

    @Autowired(required = false)
    AcctCouponMapper acctCouponMapper;

    @Autowired(required = false)
    AcctCouponDetailMapper acctCouponDetailMapper;

    /**
     * 优惠券分页条件查询
     * @param pageParam
     * @param acctCouponQuery
     * @return
     */
    @Override
    public Page<MyAcctCoupon> queryAllACoup(Page<MyAcctCoupon> pageParam, AcctCouponQuery acctCouponQuery) {
        return acctCouponMapper.queryAllACoup(pageParam,acctCouponQuery);
    }

    @Override
    public Page<MyAcctCoupon> queryBrACoup(Page<MyAcctCoupon> pageParam, AcctCouponQuery acctCouponQuery) {
        return baseMapper.queryBrACoup(pageParam,acctCouponQuery);
    }

    /**
     * 添加优惠券
     * @param data
     * @return
     */
    @Override
    public boolean add(Map<String, String> data) {
        //获取优惠券数据
        AcctCoupon acctCoupon= JSONObject.parseObject(data.get("form"), AcctCoupon.class);

        acctCouponMapper.insert(acctCoupon);
        //获取优惠券与服务明细
        List<AcctCouponDetail> list
                = JSONObject.parseArray(data.get("forms"),AcctCouponDetail.class);

        //new 优惠券与服务明细对象
        AcctCouponDetail acctCouponDetail=new AcctCouponDetail();

        for(int i=0;i<list.size();i++){
            acctCouponDetail.setAcdUuid(null);
            //卡券id
            acctCouponDetail.setAcUuid(acctCoupon.getAcUuid());
            //服务类型id
            acctCouponDetail.setStUuid(list.get(i).getServTypeUuid());
            //服务项目id
            acctCouponDetail.setCatalogUuid(list.get(i).getCatalogUuid());
            //销售价格
            acctCouponDetail.setSalePrice(acctCoupon.getSalPrice());
            //服务可抵用金额
            acctCouponDetail.setReplacePrice(list.get(i).getReplacePrice());

            //是否有效
            acctCouponDetail.setIsActive(list.get(i).getIsActive());
            //创建日期
            acctCouponDetail.setCreatedDate(list.get(i).getCreatedDate());
            //创建人
            acctCouponDetail.setCreatedBy(list.get(i).getCreatedBy());
            //版本
            acctCouponDetail.setVersion(list.get(i).getVersion());
            //总部分摊比率
            acctCouponDetail.setAllocationBase(list.get(i).getAllocationBase());
            //门店分摊比率
            acctCouponDetail.setAllocationBranches(list.get(i).getAllocationBranches());
            //SA分摊比率
            acctCouponDetail.setAllocationSa(list.get(i).getAllocationSa());
            //添加优惠券服务明细
            acctCouponDetailMapper.insert(acctCouponDetail);

        }


        return true;
    }


}

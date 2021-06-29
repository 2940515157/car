package com.xmz.service.card.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xmz.service.card.entity.pojo.AcctCouponDetail;
import com.xmz.service.card.entity.vo.MyAcctCouponDetail;
import com.xmz.service.card.mapper.AcctCouponDetailMapper;
import com.xmz.service.card.service.IAcctCouponDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 优惠券服务明细 服务实现类
 * </p>
 *
 * @author liujiaxi
 * @since 2021-06-15
 */
@Service
public class AcctCouponDetailServiceImpl extends ServiceImpl<AcctCouponDetailMapper, AcctCouponDetail> implements IAcctCouponDetailService {
    @Autowired(required = false)
    AcctCouponDetailMapper acctCouponDetailMapper;



    /**
     * 根据卡券id 查询出优惠券服务明细
     * @param acctCouponDetail
     * @return
     */
    @Override
    public List<MyAcctCouponDetail> acdList(AcctCouponDetail acctCouponDetail) {
        return acctCouponDetailMapper.acdList(acctCouponDetail);
    }

    /**
     * 修改优惠券服务明细
     * @param data
     * @return
     */
    @Override
    public boolean update(Map<String, String> data) {
        List<AcctCouponDetail> list
                = JSONObject.parseArray(data.get("forms2"),AcctCouponDetail.class);
        //获取卡券id
        String AcUuid= data.get("AcUuid");
        AcctCouponDetail acctCouponDetail=new AcctCouponDetail();
        //根据卡券id 删除优惠券服务明细
        acctCouponDetailMapper.deleteAcUuid(AcUuid);

        for(int i=0;i<list.size();i++){
            System.out.println("江志威是猪");
            acctCouponDetail.setAcdUuid(null);
            //卡券id
            acctCouponDetail.setAcUuid(AcUuid);
            acctCouponDetail.setStUuid(list.get(i).getStUuid());
            //acctCouponDetail.setStUuid(list.get(i).getServTypeUuid());
            //服务项目id
            acctCouponDetail.setCatalogUuid(list.get(i).getCatalogUuid());
            //销售价格
            acctCouponDetail.setSalePrice(list.get(i).getSalePrice());
            //是否有效
            acctCouponDetail.setIsActive(list.get(i).getIsActive());
            //服务可抵用金额
            acctCouponDetail.setReplacePrice(list.get(i).getReplacePrice());
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
             baseMapper.insert(acctCouponDetail);
        }
        return true;
    }

    @Override
    public void deleteAcUuid(String AcUuid) {
        acctCouponDetailMapper.deleteAcUuid(AcUuid);
    }


}

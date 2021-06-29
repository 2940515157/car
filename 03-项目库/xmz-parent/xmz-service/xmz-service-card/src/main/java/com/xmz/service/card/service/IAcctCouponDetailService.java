package com.xmz.service.card.service;

import com.xmz.service.card.entity.pojo.AcctCouponDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmz.service.card.entity.vo.MyAcctCouponDetail;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 优惠券服务明细 服务类
 * </p>
 *
 * @author liujiaxi
 * @since 2021-06-15
 */
public interface IAcctCouponDetailService extends IService<AcctCouponDetail> {
    /**
     * 根据卡券id 查询出优惠券服务明细
     * @param acctCouponDetail
     * @return
     */
    List<MyAcctCouponDetail> acdList(AcctCouponDetail acctCouponDetail);

    boolean update(Map<String,String> data);

    /**
     * 根据卡券id 删除
     * @param AcUuid
     */
    void deleteAcUuid(String AcUuid);
}

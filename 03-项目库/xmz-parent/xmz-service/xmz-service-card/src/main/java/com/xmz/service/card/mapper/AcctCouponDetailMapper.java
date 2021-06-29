package com.xmz.service.card.mapper;

import com.xmz.service.card.entity.pojo.AcctCouponDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmz.service.card.entity.vo.MyAcctCouponDetail;

import java.util.List;

/**
 * <p>
 * 优惠券服务明细 Mapper 接口
 * </p>
 *
 * @author liujiaxi
 * @since 2021-06-15
 */
public interface AcctCouponDetailMapper extends BaseMapper<AcctCouponDetail> {
    /**
     * 根据卡券id 查询出优惠券服务明细
     * @param acctCouponDetail
     * @return
     */
     List<MyAcctCouponDetail> acdList(AcctCouponDetail acctCouponDetail);

    /**
     * 根据卡券id 删除
     * @param AcUuid
     */
    void deleteAcUuid(String AcUuid);


}

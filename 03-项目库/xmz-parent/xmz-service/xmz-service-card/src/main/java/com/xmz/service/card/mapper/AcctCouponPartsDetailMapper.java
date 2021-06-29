package com.xmz.service.card.mapper;

import com.xmz.service.card.entity.pojo.AcctCouponDetail;
import com.xmz.service.card.entity.pojo.AcctCouponPartsDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmz.service.card.entity.vo.MyAcctCouponDetail;
import com.xmz.service.card.entity.vo.MyAcctCouponPartsDetail;

import java.util.List;

/**
 * <p>
 * 优惠券配件明细 Mapper 接口
 * </p>
 *
 * @author liujiaxi
 * @since 2021-06-16
 */
public interface AcctCouponPartsDetailMapper extends BaseMapper<AcctCouponPartsDetail> {
    /**
     * 根据卡券id 查询出优惠券服务明细
     * @param acctCouponPartsDetail
     * @return
     */
    List<MyAcctCouponPartsDetail> acdList(AcctCouponPartsDetail acctCouponPartsDetail);

}

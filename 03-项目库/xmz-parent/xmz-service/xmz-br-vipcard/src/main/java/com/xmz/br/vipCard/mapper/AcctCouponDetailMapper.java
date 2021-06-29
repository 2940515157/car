package com.xmz.br.vipCard.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.xmz.br.vipCard.entity.pojo.AcctCouponDetailPojo;
import com.xmz.br.vipCard.entity.pojo.CouponServerVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 优惠券服务明细 Mapper 接口
 * </p>
 *
 * @author LLY
 * @since 2021-06-02
 */
public interface AcctCouponDetailMapper extends BaseMapper<CouponServerVo> {

    List<CouponServerVo> serverSelectAll(@Param("es") AcctCouponDetailPojo acctCouponDetailPojo);

}

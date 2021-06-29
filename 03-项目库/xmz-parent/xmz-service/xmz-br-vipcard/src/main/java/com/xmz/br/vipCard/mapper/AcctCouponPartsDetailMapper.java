package com.xmz.br.vipCard.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmz.br.vipCard.entity.pojo.AcctCouponPartsDetailPojo;
import com.xmz.br.vipCard.entity.pojo.CouponPartsVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 优惠券配件明细 Mapper 接口
 * </p>
 *
 * @author LLY
 * @since 2021-06-03
 */
public interface AcctCouponPartsDetailMapper extends BaseMapper<AcctCouponPartsDetailPojo> {

    List<CouponPartsVo> partsSelectAll(@Param("es") AcctCouponPartsDetailPojo acctCouponPartsDetailPojo);
}

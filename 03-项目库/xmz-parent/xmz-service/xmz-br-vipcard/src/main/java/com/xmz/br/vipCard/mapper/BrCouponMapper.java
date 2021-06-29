package com.xmz.br.vipCard.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.br.vipCard.entity.pojo.BrCouponPojo;
import com.xmz.br.vipCard.entity.pojo.BrCouponVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 门店与优惠券对应关系 Mapper 接口
 * </p>
 *
 * @author LLY
 * @since 2021-06-10
 */
public interface BrCouponMapper extends BaseMapper<BrCouponPojo> {

    List<BrCouponVo> pageList(Page<BrCouponVo> pojoPage, @Param("es") BrCouponVo brCouponVo);


}

package com.xmz.br.vipCard.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.br.vipCard.entity.pojo.AcctCouponBuyRecordPojo;
import com.xmz.br.vipCard.entity.pojo.AcctCouponPojo;
import com.xmz.br.vipCard.entity.pojo.RecordVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 优惠券购买及消费跟踪 Mapper 接口
 * </p>
 *
 * @author LLY
 * @since 2021-06-17
 */
public interface AcctCouponBuyRecordMapper extends BaseMapper<AcctCouponBuyRecordPojo> {

    int updateNum(@Param("ac") AcctCouponPojo acctCouponPojo);

    AcctCouponPojo selectCouPonById(String acUuid);

    List<RecordVo> pageList(Page<RecordVo> pojoPage, @Param("es") RecordVo recordVo);

    List<RecordVo> queryList(String acUuid);
}


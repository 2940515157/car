package com.xmz.br.vipCard.service;



import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmz.br.vipCard.entity.pojo.AcctCouponBuyRecordPojo;
import com.xmz.br.vipCard.entity.pojo.RecordVo;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 优惠券购买及消费跟踪 服务类
 * </p>
 *
 * @author LLY
 * @since 2021-06-17
 */
public interface AcctCouponBuyRecordService extends IService<AcctCouponBuyRecordPojo> {

    boolean addList(AcctCouponBuyRecordPojo acctCouponBuyRecordPojo, BigDecimal num);

    List<RecordVo> pageList(Page<RecordVo> pojoPage, RecordVo recordVo);

    boolean addList2(AcctCouponBuyRecordPojo acctCouponBuyRecordPojo, BigDecimal num);

    List<RecordVo> queryList(String acUuid);
}

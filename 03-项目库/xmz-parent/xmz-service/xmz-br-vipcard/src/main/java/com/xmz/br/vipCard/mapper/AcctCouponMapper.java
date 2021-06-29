package com.xmz.br.vipCard.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.br.vipCard.entity.pojo.*;
import com.xmz.br.vipCard.entity.vo.AcctBrCardQuery;
import com.xmz.br.vipCard.entity.vo.AcctCouponBuyRecordPojoQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 优惠券 Mapper 接口
 * </p>
 *
 * @author LLY
 * @since 2021-05-27
 */
public interface AcctCouponMapper extends BaseMapper<AcctCouponPojo> {

    List<CouponVo> pageList(Page<CouponVo> pojoPage, @Param("es") CouponVo couponVo);

    int updateNum(@Param("ac") AcctCouponPojo acctCouponPojo);

    /**
     * 分页的多表查询方法
     * @param pageParam  分页参数
     * @param acctCouponBuyRecordPojoQuery  查询条件对象
     * @return
     */
    Page<MyAcctCouponBuyRecordPojo> myPage(Page<MyAcctCouponBuyRecordPojo> pageParam,
                              @Param(value = "acbr") AcctCouponBuyRecordPojoQuery acctCouponBuyRecordPojoQuery);

    /**
     * 分页的多表查询方法
     * @param pageParam  分页参数
     * @param acctCouponBuyRecordPojoQuery  查询条件对象
     * @return
     */
    Page<MyAcbrStatus> myPage2(Page<MyAcbrStatus> pageParam,
                                           @Param(value = "acbr") AcctCouponBuyRecordPojoQuery acctCouponBuyRecordPojoQuery);

    //查询优惠券汇总
    List<MyAcbrStatus> queryAll();

    //查询未转赠的优惠券数量
    MyAcbrStatus queryNormal(String acUuid);
}

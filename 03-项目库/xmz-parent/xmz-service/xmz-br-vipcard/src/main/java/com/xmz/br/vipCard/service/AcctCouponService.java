package com.xmz.br.vipCard.service;



import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmz.br.vipCard.entity.pojo.AcctCouponPojo;
import com.xmz.br.vipCard.entity.pojo.CouponVo;
import com.xmz.br.vipCard.entity.pojo.MyAcbrStatus;
import com.xmz.br.vipCard.entity.pojo.MyAcctCouponBuyRecordPojo;
import com.xmz.br.vipCard.entity.vo.AcctCouponBuyRecordPojoQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 优惠券 服务类
 * </p>
 *
 * @author LLY
 * @since 2021-05-27
 */
public interface AcctCouponService extends IService<AcctCouponPojo> {

    List<CouponVo> pageList(Page<CouponVo> pojoPage, CouponVo couponVo);

    /**
     * 分页的多表查询方法
     * @param pageParam  分页参数
     * @param acctCouponBuyRecordPojoQuery  查询条件对象
     * @return
     */
    Page<MyAcctCouponBuyRecordPojo> myPage(Page<MyAcctCouponBuyRecordPojo> pageParam,
                                           AcctCouponBuyRecordPojoQuery acctCouponBuyRecordPojoQuery);

    /**
     * 分页的多表查询方法
     * @param pageParam  分页参数
     * @param acctCouponBuyRecordPojoQuery  查询条件对象
     * @return
     */
    Page<MyAcbrStatus> myPage2(Page<MyAcbrStatus> pageParam,
                               AcctCouponBuyRecordPojoQuery acctCouponBuyRecordPojoQuery);

    //查询优惠券汇总
    List<MyAcbrStatus> queryAll();

    //查询未转赠的优惠券数量
    MyAcbrStatus queryNormal(String acUuid);
}

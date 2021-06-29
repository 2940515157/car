package com.xmz.br.vipCard.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xmz.br.vipCard.config.CodeNoGenerateUtil;
import com.xmz.br.vipCard.entity.pojo.AcctCouponBuyRecordPojo;
import com.xmz.br.vipCard.entity.pojo.AcctCouponPojo;
import com.xmz.br.vipCard.entity.pojo.RecordVo;
import com.xmz.br.vipCard.mapper.AcctCouponBuyRecordMapper;
import com.xmz.br.vipCard.service.AcctCouponBuyRecordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 优惠券购买及消费跟踪 服务实现类
 * </p>
 *
 * @author LLY
 * @since 2021-06-17
 */
@Transactional
@Service
public class AcctCouponBuyRecordServiceImpl extends ServiceImpl<AcctCouponBuyRecordMapper, AcctCouponBuyRecordPojo> implements AcctCouponBuyRecordService {

    /**
     * 批量添加优惠券
     *
     * @param acctCouponBuyRecordPojo
     * @param num
     * @return
     */
    @Override
    public boolean addList(AcctCouponBuyRecordPojo acctCouponBuyRecordPojo, BigDecimal num) {
        int number = 0;
        int num2 = num.intValue();

        // 查询对于的优惠券，判断优惠券是否足够
        AcctCouponPojo acctCouponPojo = baseMapper.selectCouPonById(acctCouponBuyRecordPojo.getAcUuid());
        int acNum = acctCouponPojo.getRemainder().intValue();
        if (acNum < num2) {
            return false;
        }
        // 修改总部优惠券数量
        acctCouponPojo.setAcUuid(acctCouponBuyRecordPojo.getAcUuid());
        acctCouponPojo.setRemainder(num);
        number = baseMapper.updateNum(acctCouponPojo);

        //循环添加优惠券
        for (int i = 0; i < num2; i++) {
            // 生成优惠券号
            String str = CodeNoGenerateUtil.createNo();
            // 给对象赋值
            acctCouponBuyRecordPojo.setAcNumber(str);
            acctCouponBuyRecordPojo.setStatus("normal");
            number = baseMapper.insert(acctCouponBuyRecordPojo);
            acctCouponBuyRecordPojo.setAcbrUuid(null);
            System.out.println(str);
            if (number == 0) {
                break;
            }
        }


        return number == 0 ? false : true;
    }

    @Override
    public List<RecordVo> pageList(Page<RecordVo> pojoPage, RecordVo recordVo) {
        return baseMapper.pageList(pojoPage,recordVo);
    }

    @Override
    public boolean addList2(AcctCouponBuyRecordPojo acctCouponBuyRecordPojo, BigDecimal num) {
        int number = 0;
        int num2 = num.intValue();

        // 查询对于的优惠券，判断优惠券是否足够
        AcctCouponPojo acctCouponPojo = baseMapper.selectCouPonById(acctCouponBuyRecordPojo.getAcUuid());
        int acNum = acctCouponPojo.getRemainder().intValue();
        if (acNum < num2) {
            return false;
        }
        // 修改总部优惠券数量
        acctCouponPojo.setAcUuid(acctCouponBuyRecordPojo.getAcUuid());
        acctCouponPojo.setRemainder(num);
        number = baseMapper.updateNum(acctCouponPojo);


        //根据优惠券号查询优惠券明细
       List<RecordVo> list=baseMapper.queryList(acctCouponBuyRecordPojo.getAcUuid());

        //循环编辑优惠券
        for (int i = 0; i < num2; i++) {
            acctCouponBuyRecordPojo.setAcbrUuid(list.get(i).getAcbrUuid());
            acctCouponBuyRecordPojo.setStatus("attorn");
            acctCouponBuyRecordPojo.setAttornDate(new Date());
            number = baseMapper.updateById(acctCouponBuyRecordPojo);
            if (number == 0) {
                break;
            }
        }

        return number == 0 ? false : true;


    }

    @Override
    public List<RecordVo> queryList(String acUuid) {
        return baseMapper.queryList(acUuid);
    }


}

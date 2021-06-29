package com.xmz.br.vipCard.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.br.vipCard.entity.pojo.AcctCustCard;
import com.xmz.br.vipCard.entity.pojo.AcctRechargeRecord;
import com.xmz.br.vipCard.entity.pojo.MyAcctRechargeRecord;
import com.xmz.br.vipCard.entity.vo.AcctRechargeRecordQuery;
import com.xmz.br.vipCard.mapper.AcctRechargeRecordMapper;
import com.xmz.br.vipCard.service.IAcctCustCardService;
import com.xmz.br.vipCard.service.IAcctRechargeRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 充值记录表 服务实现类
 * </p>
 *
 * @author zt
 * @since 2021-06-09
 */
@Service
public class AcctRechargeRecordServiceImpl extends ServiceImpl<AcctRechargeRecordMapper, AcctRechargeRecord> implements IAcctRechargeRecordService {
    @Autowired
    IAcctRechargeRecordService iAcctRechargeRecordService;

    @Autowired
    IAcctCustCardService iAcctCustCardService;

    @Override
    public Page<MyAcctRechargeRecord> myPage(Page<MyAcctRechargeRecord> pageParam, AcctRechargeRecordQuery acctRechargeRecordQuery) {
        return baseMapper.myPage(pageParam,acctRechargeRecordQuery);
    }

    @Override
    public boolean add(MyAcctRechargeRecord myAcctRechargeRecord) {
        System.out.println(myAcctRechargeRecord);
        System.out.println(myAcctRechargeRecord.getProductUuid());
        MyAcctRechargeRecord myAcctRechargeRecord1=new MyAcctRechargeRecord();
        myAcctRechargeRecord1.setCustCardUuid(myAcctRechargeRecord.getCustCardUuid());
        myAcctRechargeRecord1.setCardNo(myAcctRechargeRecord.getCardNo());
        myAcctRechargeRecord1.setBrUuid(myAcctRechargeRecord.getBrUuid());
        myAcctRechargeRecord1.setProductUuid(myAcctRechargeRecord.getProductUuid());
        myAcctRechargeRecord1.setCustUuid(myAcctRechargeRecord.getCustUuid());
        myAcctRechargeRecord1.setSaUuid("4028858278351f74017835241db2006a");
        double f=myAcctRechargeRecord.getZfPrice()/myAcctRechargeRecord.getTotalPrice();
        BigDecimal b=new BigDecimal(f);
        double b1 = b.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
        myAcctRechargeRecord1.setRate(new BigDecimal(b1));
        myAcctRechargeRecord1.setTrueAmt(new BigDecimal(myAcctRechargeRecord.getZfPrice()));
        myAcctRechargeRecord1.setAmount(new BigDecimal(myAcctRechargeRecord.getTotalPrice()));
        myAcctRechargeRecord1.setIsActive("Y");
        myAcctRechargeRecord1.setCreatedDate(new Date());
        myAcctRechargeRecord1.setCreatedBy("wwm");
        myAcctRechargeRecord1.setBalance(myAcctRechargeRecord.getBalance());
        myAcctRechargeRecord1.setStatus("payed");
        boolean flag= iAcctRechargeRecordService.save(myAcctRechargeRecord1);

        AcctCustCard acctCustCard=new AcctCustCard();
        acctCustCard.setCustCardUuid(myAcctRechargeRecord.getCustCardUuid());
        acctCustCard.setBalance(new BigDecimal(myAcctRechargeRecord.getZongPrice()));
        iAcctCustCardService.updateById(acctCustCard);


       return  flag;
    }
}

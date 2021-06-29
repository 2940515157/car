package com.xmz.br.vipCard.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.br.vipCard.entity.pojo.AcctBrCard;
import com.xmz.br.vipCard.entity.pojo.AcctCustCard;
import com.xmz.br.vipCard.entity.pojo.MyAcctCustCard;
import com.xmz.br.vipCard.entity.vo.AcctCustCardQuery;
import com.xmz.br.vipCard.mapper.AcctCustCardMapper;
import com.xmz.br.vipCard.service.IAcctBrCardService;
import com.xmz.br.vipCard.service.IAcctCustCardService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 客户卡 服务实现类
 * </p>
 *
 * @author zt
 * @since 2021-06-04
 */
@Service
public class AcctCustCardServiceImpl extends ServiceImpl<AcctCustCardMapper, AcctCustCard> implements IAcctCustCardService {
    @Autowired
    IAcctCustCardService iAcctCustCardService;

    @Autowired
    IAcctBrCardService iAcctBrCardService;

    @Override
    public Page<MyAcctCustCard> myPage(Page<MyAcctCustCard> pageParam, AcctCustCardQuery acctCustCardQuery) {
        return baseMapper.myPage(pageParam,acctCustCardQuery);
    }

    @Override
    public List<MyAcctCustCard> queryPhone(AcctCustCardQuery acctCustCardQuery) {
        return baseMapper.queryPhone(acctCustCardQuery);
    }

    @Override
    public boolean add(Map<String, String> data) {
        System.out.println(data);
        MyAcctCustCard myAcctCustCard = JSONObject.parseObject(data.get("addCarform"), MyAcctCustCard.class);
        MyAcctCustCard myAcctCustCard2 = JSONObject.parseObject(data.get("addCarform2"), MyAcctCustCard.class);
        System.out.println(myAcctCustCard);
        System.out.println(myAcctCustCard2);

        AcctCustCard acctCustCard=new AcctCustCard();
        acctCustCard.setCardUuid(myAcctCustCard2.getCardUuid());
        acctCustCard.setCardNo(myAcctCustCard2.getCardNo());
        acctCustCard.setBrUuid(myAcctCustCard2.getBrUuid());
        acctCustCard.setCustUuid(myAcctCustCard.getCustUuid());
        acctCustCard.setSaUuid(myAcctCustCard.getSaUuid());
        acctCustCard.setCardType("general");
        acctCustCard.setBalance(new BigDecimal(0));
        acctCustCard.setDiscountRate(new BigDecimal(0));
        acctCustCard.setBalance(new BigDecimal(0.00));
        acctCustCard.setStatus("normal");
        acctCustCard.setIsActive("Y");
        acctCustCard.setCreatedDate(new Date());
        acctCustCard.setCreatedBy("xiashuhua");
        acctCustCard.setVersion(new BigDecimal(0));
        acctCustCard.setImpBalance(new BigDecimal(0.00));
        acctCustCard.setImpSetRate(new BigDecimal(1.00));
        acctCustCard.setImpDiscountRate(new BigDecimal(1.00));
        acctCustCard.setImpFlag("Y");
        boolean flag=   iAcctCustCardService.save(acctCustCard);

        AcctBrCard acctBrCard=new AcctBrCard();
        acctBrCard.setCardUuid(myAcctCustCard2.getCardUuid());
        acctBrCard.setIsExtend("Y");
        acctBrCard.setExtendUuid(myAcctCustCard.getSaUuid());
        iAcctBrCardService.updateById(acctBrCard);

        return flag;
    }

    @Override
    public Page<MyAcctCustCard> myPage2(Page<MyAcctCustCard> pageParam, AcctCustCardQuery acctCustCardQuery) {
        return baseMapper.myPage2(pageParam,acctCustCardQuery);
    }
}

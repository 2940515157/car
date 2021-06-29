package com.xmz.br.vipCard.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.br.vipCard.entity.pojo.AcctPackageCustomer;
import com.xmz.br.vipCard.entity.pojo.MyAcctPackageCustomer;
import com.xmz.br.vipCard.entity.vo.AcctPackageCustomerQuery;
import com.xmz.br.vipCard.mapper.AcctPackageCustomerMapper;
import com.xmz.br.vipCard.service.IAcctPackageCustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * <p>
 * 客户已购买套餐 服务实现类
 * </p>
 *
 * @author zt
 * @since 2021-06-11
 */
@Service
public class AcctPackageCustomerServiceImpl extends ServiceImpl<AcctPackageCustomerMapper, AcctPackageCustomer> implements IAcctPackageCustomerService {

    @Autowired
    IAcctPackageCustomerService acctPackageCustomerService;

    AcctPackageCustomerQuery query=new AcctPackageCustomerQuery();
    @Override
    public Page<MyAcctPackageCustomer> myPage(Page<MyAcctPackageCustomer> pageParam, AcctPackageCustomerQuery acctPackageCustomerQuery) {
        return baseMapper.myPage(pageParam,acctPackageCustomerQuery);
    }

    @Override
    public boolean add(Map<String, String> data) {
        System.out.println(data);
        AcctPackageCustomer acctPackageCustomer = JSONObject.parseObject(data.get("form"), AcctPackageCustomer.class);
        System.out.println(acctPackageCustomer);

        AcctPackageCustomer acctPackageCustomer1=new AcctPackageCustomer();
        acctPackageCustomer1.setCustomerUuid(acctPackageCustomer.getCustomerUuid());
        acctPackageCustomer1.setApcName(acctPackageCustomer.getApcName());
        acctPackageCustomer1.setApcType("service");
        acctPackageCustomer1.setTotalPrice(acctPackageCustomer.getTotalPrice());
        acctPackageCustomer1.setStatus("up");
        acctPackageCustomer1.setOrigionType("operCenter");
        acctPackageCustomer1.setCreatorUuid("ff8080814e641142014e66634d0e0043");
        acctPackageCustomer1.setCheckUuid("ff8080814e641142014e66634d0e0043");
        acctPackageCustomer1.setSetCrUuid("ff8080814e641142014e66634d0e0043");
        acctPackageCustomer1.setSaleRate(new BigDecimal(1.5));
        acctPackageCustomer1.setIsActive("Y");
        acctPackageCustomer1.setApcStatus("unPaid");
        acctPackageCustomer1.setValidMonth(acctPackageCustomer.getValidMonth());
        acctPackageCustomer1.setBrUuid("ff80808151f8afb801521583513201b0");
        acctPackageCustomer1.setSaUuid("ff8080814e641142014e66634d0e0043");
        acctPackageCustomer1.setUseEndDate(acctPackageCustomer.getUseEndDate());
        acctPackageCustomer1.setRelPrice(acctPackageCustomer1.getTotalPrice());
        acctPackageCustomer1.setUsedFlag("N");
        acctPackageCustomer1.setAcctPackageUuid(acctPackageCustomer.getAcctPackageUuid());
        acctPackageCustomer1.setUseStartDate(acctPackageCustomer.getUseStartDate());
        acctPackageCustomer1.setImpFlag("Y");
        acctPackageCustomer1.setCreatedDate(new Date());
        boolean flag= acctPackageCustomerService.save(acctPackageCustomer1);

        query.setCreatedDate(acctPackageCustomer1.getCreatedDate());
        return flag;
    }

    @Override
    public AcctPackageCustomer queryDate(AcctPackageCustomerQuery acctPackageCustomerQuery) {
       AcctPackageCustomer acctPackageCustomer= baseMapper.queryDate(query);
       acctPackageCustomer.setApcStatus("paid");
       baseMapper.updateById(acctPackageCustomer);
        return acctPackageCustomer;
    }


}

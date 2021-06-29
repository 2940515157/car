package com.xmz.br.crm.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.br.crm.entity.pojo.CustCustInfo;
import com.xmz.br.crm.entity.pojo.CustCustSasItem;
import com.xmz.br.crm.entity.vo.CustInfoQueryParam;
import com.xmz.br.crm.entity.vo.SaInfo;
import com.xmz.br.crm.mapper.CustCustInfoMapper;
import com.xmz.br.crm.mapper.CustCustSasItemMapper;
import com.xmz.br.crm.service.ICustCustInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 客户信息 服务实现类
 * </p>
 *
 * @author weikang
 * @since 2021-06-08
 */
@Service
public class CustCustInfoServiceImpl extends ServiceImpl<CustCustInfoMapper, CustCustInfo> implements ICustCustInfoService {
    @Autowired(required = false)
    CustCustSasItemMapper custCustSasItemMapper;

    @Override
    public Page<CustCustInfo> queryPage(Page<CustCustInfo> page, CustInfoQueryParam queryParam, String shop) {
        return baseMapper.queryPage(page,queryParam,shop);
    }

    @Override
    public List<CustCustInfo> queryList(CustInfoQueryParam queryParam, String shop) {
        return baseMapper.queryList(queryParam,shop);
    }

    @Override
    public List<SaInfo> querySaInfoList(String shop) {
        return baseMapper.querySaInfoList(shop);
    }

    @Override
    @Transactional
    public int add(CustCustInfo custCustInfo) {
        //添加到客户表
        Calendar cal=Calendar.getInstance();
        String date=String.valueOf(
                cal.get(Calendar.YEAR))+
                cal.get(Calendar.MONDAY)+
                cal.get(Calendar.DAY_OF_MONTH)+
                cal.get(Calendar.HOUR_OF_DAY)+
                cal.get(Calendar.MINUTE)+
                cal.get(Calendar.SECOND);
        custCustInfo.setCustNo(date);
        baseMapper.insert(custCustInfo);
        //根据门店代码查询门店uuid
        String bruuid=baseMapper.queryBrUuidByShop(custCustInfo.getBrcode());
        //添加到门店-SA-客户-车辆明细表
        CustCustSasItem custCustSasItem=new CustCustSasItem();
        custCustSasItem.setBrUuid(bruuid);
        custCustSasItem.setCustUuid(custCustInfo.getCustUuid());
        custCustSasItem.setVersion(new BigDecimal(1.0));
        return custCustSasItemMapper.insert(custCustSasItem);
    }
}

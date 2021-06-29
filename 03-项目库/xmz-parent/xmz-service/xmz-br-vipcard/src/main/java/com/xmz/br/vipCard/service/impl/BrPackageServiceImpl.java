package com.xmz.br.vipCard.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.br.vipCard.entity.pojo.*;
import com.xmz.br.vipCard.entity.vo.AcctPackageQuery;
import com.xmz.br.vipCard.entity.vo.BrPackageQuery;
import com.xmz.br.vipCard.mapper.BrPackageMapper;
import com.xmz.br.vipCard.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 门店套餐 服务实现类
 * </p>
 *
 * @author zt
 * @since 2021-06-11
 */
@Service
public class BrPackageServiceImpl extends ServiceImpl<BrPackageMapper, BrPackage> implements IBrPackageService {
    @Autowired
    IAcctPackageService iAcctPackageService;
    @Autowired
    IBrPackageService iBrPackageService;
    @Autowired
    IAcctPackageDetailService iAcctPackageDetailService;
    @Autowired
    IAcctPackagePartsDetailService iAcctPackagePartsDetailService;

    @Override
    public Page<MyBrPackage> myPage(Page<MyBrPackage> pageParam, BrPackageQuery brPackageQuery) {
        return baseMapper.myPage(pageParam,brPackageQuery);
    }

    @Override
    public List<MyBrPackage> listQuery(BrPackageQuery brPackageQuery) {
        return baseMapper.listQuery(brPackageQuery);
    }

    @Override
    @Transactional
    public boolean add(Map<String, String> data) {
        AcctPackage acctPackage = JSONObject.parseObject(data.get("ruleForm"), AcctPackage.class);
        List<AcctPackageDetail> list = JSONObject.parseArray(data.get("xzFWData"), AcctPackageDetail.class);
        List<AcctPackagePartsDetail> list1 = JSONObject.parseArray(data.get("fwData"), AcctPackagePartsDetail.class);

        //添加套餐
        acctPackage.setStatus("init");
        acctPackage.setOrigionType("branches");
        acctPackage.setCheckUuid(acctPackage.getCreatorUuid());
        acctPackage.setSetCrUuid(acctPackage.getCreatorUuid());
        acctPackage.setIsActive("Y");
        acctPackage.setCreatedDate(new Date());
        acctPackage.setBrUuid("ff80808151f8afb801521583513201b0");
        iAcctPackageService.save(acctPackage);

        //根据刚刚添加的套餐名查询该套餐
        AcctPackage acctPackage1=iAcctPackageService.queryName(acctPackage.getApName());
        //添加门店套餐
        BrPackage brPackage=new BrPackage();
        brPackage.setBrUuid(acctPackage1.getBrUuid());
        brPackage.setApUuid(acctPackage1.getApUuid());
        brPackage.setStatus("init");
        brPackage.setCreatedDate(new Date());
        iBrPackageService.save(brPackage);

        System.out.println("套餐："+acctPackage1.getApUuid());

        //添加套餐服务明细
        for(int i=1;i<list.size();i++){
            AcctPackageDetail acctPackageDetail=list.get(i);
            acctPackageDetail.setApUuid(acctPackage1.getApUuid());
            acctPackageDetail.setServCatalogUuid(list.get(i).getCatalogUuid());
            acctPackageDetail.setStlStdPrice(list.get(i).getStdPrice());
            acctPackageDetail.setIsActive("Y");
            acctPackageDetail.setCreatedDate(new Date());
            iAcctPackageDetailService.save(acctPackageDetail);

       }

        //添加套餐配件明细
        if(list1.size()>1){
            for(int i=1;i<list1.size();i++){
                AcctPackagePartsDetail acctPackagePartsDetail=list1.get(i);
                acctPackagePartsDetail.setIsActive("Y");
                acctPackagePartsDetail.setCreatedDate(new Date());
                acctPackagePartsDetail.setApUuid(acctPackage1.getApUuid());
                iAcctPackagePartsDetailService.save(acctPackagePartsDetail);
            }
        }

        return true;
    }
}

package com.xmz.service.card.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.card.config.PinYinUtil;
import com.xmz.service.card.entity.pojo.AcctPackage;
import com.xmz.service.card.entity.pojo.AcctPackageDetail;
import com.xmz.service.card.entity.vo.MyAcctPackage;
import com.xmz.service.card.mapper.AcctPackageDetailMapper;
import com.xmz.service.card.mapper.AcctPackageMapper;
import com.xmz.service.card.service.IAcctPackageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 套餐 服务实现类
 * </p>
 *
 * @author liujiaxi
 * @since 2021-06-03
 */
@Service
public class AcctPackageServiceImpl extends ServiceImpl<AcctPackageMapper, AcctPackage> implements IAcctPackageService {

    @Autowired(required = false)
    AcctPackageMapper acctPackageMapper;

    @Autowired(required = false)
    AcctPackageDetailMapper acctPackageDetailMapper;
    /**
     * 套餐产品分页条件查询
     * @param pageParam 分页参数
     * @param acctPackage 查询条件对象
     * @return
     */
    @Override
    public Page<MyAcctPackage> queryAllAcctPackage(Page<MyAcctPackage> pageParam, AcctPackage acctPackage) {
        return acctPackageMapper.queryAllAcctPackage(pageParam,acctPackage);
    }

    /**
     * 添加套餐
     * @param data
     * @return
     */
    @Override
    public boolean add(Map<String, String> data) {

        AcctPackage acctPackage= JSONObject.parseObject(data.get("form"), AcctPackage.class);

        List<AcctPackageDetail> list
                = JSONObject.parseArray(data.get("forms"),AcctPackageDetail.class);
        AcctPackageDetail acctPackageDetail=new AcctPackageDetail();
        //拼音
        acctPackage.setPinyin(PinYinUtil.toPinyin(acctPackage.getApName()));

        acctPackageMapper.insert(acctPackage);

        for(int i=0;i<list.size();i++){

            acctPackageDetail.setApdUuid(null);
            //套餐id
            acctPackageDetail.setApUuid(acctPackage.getApUuid());
            //服务项目id
            acctPackageDetail.setServCatalogUuid(list.get(i).getServCatalogUuid());
            //单次成本价格
            acctPackageDetail.setCost(list.get(i).getCost());
            //单次服务标准报价 STD_PRICE
            acctPackageDetail.setStdPrice(list.get(i).getStdPrice());
            //单次套餐服务销售价格 SALE_PRICE
            acctPackageDetail.setSalePrice(list.get(i).getSalePrice());
            //次数 APD_NUMBER
            acctPackageDetail.setApdNumber(list.get(i).getApdNumber());
            //单次协议服务门店结算价 STL_AGREEMENT_PRICE
            acctPackageDetail.setStlAgreementPrice(list.get(i).getStlAgreementPrice());

            acctPackageDetailMapper.insert(acctPackageDetail);
        }

        return true;
    }
}

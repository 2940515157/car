package com.xmz.service.serviceproduct.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.common.utils.ResultMap;
import com.xmz.service.serviceproduct.config.PinYinUtil;
import com.xmz.service.serviceproduct.entity.pojo.MyServCheckupPkg;
import com.xmz.service.serviceproduct.entity.pojo.ServCheckupCatalogItem;
import com.xmz.service.serviceproduct.entity.pojo.ServCheckupPkg;
import com.xmz.service.serviceproduct.entity.pojo.ServCheckupPkgItem;
import com.xmz.service.serviceproduct.entity.vo.ServCheckupPkgQuery;
import com.xmz.service.serviceproduct.mapper.ServCheckupPkgMapper;
import com.xmz.service.serviceproduct.service.IServCheckupPkgItemService;
import com.xmz.service.serviceproduct.service.IServCheckupPkgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 点检包 服务实现类
 * </p>
 *
 * @author zt
 * @since 2021-05-31
 */
@Service
public class ServCheckupPkgServiceImpl extends ServiceImpl<ServCheckupPkgMapper, ServCheckupPkg> implements IServCheckupPkgService {

    @Autowired
    IServCheckupPkgService servCheckupPkgService;

    @Autowired
    IServCheckupPkgItemService iServCheckupPkgItemService;


    @Override
    public Page<ServCheckupPkg> myPage(Page<ServCheckupPkg> pageParam, ServCheckupPkgQuery servCheckupPkgQuery) {
        return baseMapper.myPage(pageParam,servCheckupPkgQuery);
    }

    @Override
    public List<MyServCheckupPkg> queryPkgName(String pkgName) {
        return baseMapper.queryPkgName(pkgName);
    }

    @Override
    public boolean add(Map<String, String> data) {

        ServCheckupPkg servCheckupPkg = JSONObject.parseObject(data.get("form"), ServCheckupPkg.class);
        List<ServCheckupPkgItem> list = JSONObject.parseArray(data.get("tableData"), ServCheckupPkgItem.class);


        servCheckupPkg.setStatus("init");
        servCheckupPkg.setOpUserUuid("ff8080814e641142014e66634d0e0043");
        servCheckupPkg.setIsActive("Y");
        servCheckupPkg.setCreatedDate(new Date());
        servCheckupPkg.setCreatedBy("admin");
        servCheckupPkg.setVersion(new BigDecimal("1"));
        servCheckupPkg.setPinyin(PinYinUtil.toPinyin(servCheckupPkg.getPkgName()));
        boolean flag=  servCheckupPkgService.save(servCheckupPkg);


        ServCheckupPkg servCheckupPkg1= servCheckupPkgService.queryPkgName2(servCheckupPkg.getPkgName());


        for(int i=1;i<list.size();i++){
            ServCheckupPkgItem servCheckupPkgItem=new ServCheckupPkgItem();
            servCheckupPkgItem.setScpUuid(servCheckupPkg1.getScpUuid());
            servCheckupPkgItem.setSccUuid(list.get(i).getSccUuid());
            servCheckupPkgItem.setIsActive("Y");
            servCheckupPkgItem.setCreatedDate(new Date());
            servCheckupPkgItem.setCreatedBy("admin");
            servCheckupPkgItem.setVersion(new BigDecimal("0"));
            iServCheckupPkgItemService.save(servCheckupPkgItem);
        }

        return flag;

    }

    @Override
    public ServCheckupPkg queryPkgName2(String name) {
        return baseMapper.queryPkgName2(name);
    }

    @Override
    public ServCheckupPkg queryById(String id) {
        return baseMapper.selectById(id);
    }

    @Override
    public boolean update(Map<String, String> data) {
        ServCheckupPkg servCheckupPkg = JSONObject.parseObject(data.get("form"), ServCheckupPkg.class);
        List<ServCheckupPkgItem> list = JSONObject.parseArray(data.get("tableData"), ServCheckupPkgItem.class);
        System.out.println(list);

        servCheckupPkg.setUpdatedDate(new Date());
        servCheckupPkg.setUpdatedBy("admin");
        servCheckupPkg.setPinyin(PinYinUtil.toPinyin(servCheckupPkg.getPkgName()));
        boolean flag=  servCheckupPkgService.updateById(servCheckupPkg);

        ServCheckupPkg servCheckupPkg1= servCheckupPkgService.queryPkgName2(servCheckupPkg.getPkgName());


        servCheckupPkgService.deleteScpUuid(list.get(0).getScpUuid());
        for(int i=0;i<list.size();i++){
            ServCheckupPkgItem servCheckupPkgItem=new ServCheckupPkgItem();
            servCheckupPkgItem.setScpUuid(servCheckupPkg1.getScpUuid());
            servCheckupPkgItem.setSccUuid(list.get(i).getSccUuid());
            servCheckupPkgItem.setIsActive("Y");
            servCheckupPkgItem.setCreatedDate(new Date());
            servCheckupPkgItem.setUpdatedDate(new Date());
            servCheckupPkgItem.setCreatedBy("admin");
            servCheckupPkgItem.setUpdatedBy("admin");
            servCheckupPkgItem.setVersion(new BigDecimal("0"));
            iServCheckupPkgItemService.save(servCheckupPkgItem);
        }

        return flag;
    }

    @Override
    public int deleteScpUuid(String scpUuid) {
        return baseMapper.deleteScpUuid(scpUuid);
    }

    @Override
    public int updateStatus(ServCheckupPkg servCheckupPkg) {
        return baseMapper.updateById(servCheckupPkg);
    }
}

package com.xmz.service.serviceproduct.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.common.utils.ResultMap;
import com.xmz.service.serviceproduct.config.PinYinUtil;
import com.xmz.service.serviceproduct.entity.pojo.MyServCheckupCatalog;
import com.xmz.service.serviceproduct.entity.pojo.ServCheckupCatalog;
import com.xmz.service.serviceproduct.entity.pojo.ServCheckupCatalogItem;
import com.xmz.service.serviceproduct.entity.vo.ServCheckupCatalogQuery;
import com.xmz.service.serviceproduct.mapper.ServCheckupCatalogMapper;
import com.xmz.service.serviceproduct.service.IServCheckupCatalogItemService;
import com.xmz.service.serviceproduct.service.IServCheckupCatalogService;
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
 * 点检项目 服务实现类
 * </p>
 *
 * @author zt
 * @since 2021-05-28
 */
@Service
public class ServCheckupCatalogServiceImpl extends ServiceImpl<ServCheckupCatalogMapper, ServCheckupCatalog> implements IServCheckupCatalogService {

    @Autowired
    IServCheckupCatalogItemService iServCheckupCatalogItemService;

    @Autowired
    IServCheckupCatalogService iServCheckupCatalogService;

    @Override
    public Page<MyServCheckupCatalog> myPage(Page<MyServCheckupCatalog> pageParam, ServCheckupCatalogQuery servCheckupCatalogQuery) {
        return baseMapper.myPage(pageParam,servCheckupCatalogQuery);
    }

    @Override
    public ServCheckupCatalog querycatalogName(String catalogName) {
        return baseMapper.querycatalogName(catalogName);
    }

    @Override
    public List<MyServCheckupCatalog> querySccUuid(String sccUuid) {
        return baseMapper.querySccUuid(sccUuid);
    }

    @Override
    public boolean add(Map<String, Object> myServCheckupCatalog) {
        //提醒产生时间(天)
        //object 类型转成 BigDecimal
        BigDecimal ret = null;
        if (myServCheckupCatalog.get("remindStartTime") != null) {
            if (myServCheckupCatalog.get("remindStartTime") instanceof BigDecimal) {
                ret = (BigDecimal) myServCheckupCatalog.get("remindStartTime");
            } else if (myServCheckupCatalog.get("remindStartTime") instanceof String) {
                ret = new BigDecimal((String) myServCheckupCatalog.get("remindStartTime"));
            } else if (myServCheckupCatalog.get("remindStartTime") instanceof BigInteger) {
                ret = new BigDecimal((BigInteger) myServCheckupCatalog.get("remindStartTime"));
            } else if (myServCheckupCatalog.get("remindStartTime") instanceof Number) {
                ret = new BigDecimal(((Number) myServCheckupCatalog.get("remindStartTime")).doubleValue());
            } else {
                throw new ClassCastException("Not possible to coerce [" + myServCheckupCatalog.get("remindStartTime") + "] from class " + myServCheckupCatalog.get("remindStartTime").getClass() + " into a BigDecimal.");
            }
        }

        //提醒产生时间(天)
        BigDecimal ret2 = null;
        if (myServCheckupCatalog.get("aginCheckupTime") != null) {
            if (myServCheckupCatalog.get("aginCheckupTime") instanceof BigDecimal) {
                ret2 = (BigDecimal) myServCheckupCatalog.get("aginCheckupTime");
            } else if (myServCheckupCatalog.get("aginCheckupTime") instanceof String) {
                ret2 = new BigDecimal((String) myServCheckupCatalog.get("aginCheckupTime"));
            } else if (myServCheckupCatalog.get("aginCheckupTime") instanceof BigInteger) {
                ret2 = new BigDecimal((BigInteger) myServCheckupCatalog.get("aginCheckupTime"));
            } else if (myServCheckupCatalog.get("aginCheckupTime") instanceof Number) {
                ret2 = new BigDecimal(((Number) myServCheckupCatalog.get("aginCheckupTime")).doubleValue());
            } else {
                throw new ClassCastException("Not possible to coerce [" + myServCheckupCatalog.get("aginCheckupTime") + "] from class " + myServCheckupCatalog.get("aginCheckupTime").getClass() + " into a BigDecimal.");
            }
        }

        //添加点检项目
        ServCheckupCatalog servCheckupCatalog=new ServCheckupCatalog();
        //项目所属目录
        servCheckupCatalog.setScdUuid((String) myServCheckupCatalog.get("scdUuid"));
        //项目名称
        servCheckupCatalog.setCatalogName((String) myServCheckupCatalog.get("catalogName"));
        //剩余里程/时间
        servCheckupCatalog.setIsSetMile((String) myServCheckupCatalog.get("isSetMile"));
        //操作人
        servCheckupCatalog.setOpUserUuid("ff8080814e641142014e66634d0e0043");
        //是否有效
        servCheckupCatalog.setIsActive("Y");
        //创建日期
        servCheckupCatalog.setCreatedDate(new Date());
        //创建人
        servCheckupCatalog.setCreatedBy("admin");
        //版本
        servCheckupCatalog.setVersion(new BigDecimal("0"));
        //拼音
        servCheckupCatalog.setPinyin(PinYinUtil.toPinyin((String) myServCheckupCatalog.get("catalogName")));
        //提醒产生时间(天)
        servCheckupCatalog.setRemindStartTime(ret);
        //再次点检时间(天)
        servCheckupCatalog.setAginCheckupTime(ret2);
        //添加点检项目
        boolean flag = iServCheckupCatalogService.save(servCheckupCatalog);

        //根据项目名称查询刚刚插入数据库的点检项目
        ServCheckupCatalog servCheckupCatalog1= iServCheckupCatalogService.querycatalogName((String) myServCheckupCatalog.get("catalogName"));
        System.out.println("根据项目名称查："+servCheckupCatalog1);

        for(int i = 0; i<((ArrayList)myServCheckupCatalog.get("type")).size(); i++){
            System.out.println(((ArrayList)myServCheckupCatalog.get("type")).get(i));
            //添加点检项目明细
            ServCheckupCatalogItem servCheckupCatalogItem=new ServCheckupCatalogItem();
            //项目所属目录
            servCheckupCatalogItem.setSccUuid(servCheckupCatalog1.getSccUuid());
            //类型
            servCheckupCatalogItem.setType((String) ((ArrayList) myServCheckupCatalog.get("type")).get(i));
            //异常描述
            servCheckupCatalogItem.setDescription((String) ((ArrayList) myServCheckupCatalog.get("descriptions")).get(i));
            //操作人
            servCheckupCatalogItem.setOpUserUuid("ff8080814e641142014e66634d0e0043");
            //是否有效
            servCheckupCatalogItem.setIsActive("Y");
            //创建日期
            servCheckupCatalogItem.setCreatedDate(new Date());
            //创建人
            servCheckupCatalogItem.setCreatedBy("admin");
            iServCheckupCatalogItemService.save(servCheckupCatalogItem);
        }

       return flag;

    }

    @Override
    public boolean update(Map<String, Object> map) {
        //提醒产生时间(天)
        //object 类型转成 BigDecimal
        BigDecimal ret = null;
        if (map.get("remindStartTime") != null) {
            if (map.get("remindStartTime") instanceof BigDecimal) {
                ret = (BigDecimal) map.get("remindStartTime");
            } else if (map.get("remindStartTime") instanceof String) {
                ret = new BigDecimal((String) map.get("remindStartTime"));
            } else if (map.get("remindStartTime") instanceof BigInteger) {
                ret = new BigDecimal((BigInteger) map.get("remindStartTime"));
            } else if (map.get("remindStartTime") instanceof Number) {
                ret = new BigDecimal(((Number) map.get("remindStartTime")).doubleValue());
            } else {
                throw new ClassCastException("Not possible to coerce [" + map.get("remindStartTime") + "] from class " + map.get("remindStartTime").getClass() + " into a BigDecimal.");
            }
        }

        //提醒产生时间(天)
        BigDecimal ret2 = null;
        if (map.get("aginCheckupTime") != null) {
            if (map.get("aginCheckupTime") instanceof BigDecimal) {
                ret2 = (BigDecimal) map.get("aginCheckupTime");
            } else if (map.get("aginCheckupTime") instanceof String) {
                ret2 = new BigDecimal((String) map.get("aginCheckupTime"));
            } else if (map.get("aginCheckupTime") instanceof BigInteger) {
                ret2 = new BigDecimal((BigInteger) map.get("aginCheckupTime"));
            } else if (map.get("aginCheckupTime") instanceof Number) {
                ret2 = new BigDecimal(((Number) map.get("aginCheckupTime")).doubleValue());
            } else {
                throw new ClassCastException("Not possible to coerce [" + map.get("aginCheckupTime") + "] from class " + map.get("aginCheckupTime").getClass() + " into a BigDecimal.");
            }
        }

        ServCheckupCatalog servCheckupCatalog=new ServCheckupCatalog();
        servCheckupCatalog.setSccUuid((String) map.get("sccUuid"));
        servCheckupCatalog.setScdUuid((String) map.get("scdUuid"));
        servCheckupCatalog.setCatalogName((String) map.get("catalogName"));
        servCheckupCatalog.setIsSetMile((String) map.get("isSetMile"));
        servCheckupCatalog.setOpUserUuid("ff8080814e641142014e66634d0e0043");
        servCheckupCatalog.setIsActive("Y");
        servCheckupCatalog.setUpdatedDate(new Date());
        servCheckupCatalog.setUpdatedBy("admin");
        servCheckupCatalog.setVersion(new BigDecimal("1"));
        servCheckupCatalog.setPinyin(PinYinUtil.toPinyin((String) map.get("catalogName")));
        servCheckupCatalog.setRemindStartTime(ret);
        servCheckupCatalog.setAginCheckupTime(ret2);
        boolean flag= iServCheckupCatalogService.updateById(servCheckupCatalog);


        ServCheckupCatalogItem servCheckupCatalogItem=new ServCheckupCatalogItem();
        //根据项目名称编号查询
        List<ServCheckupCatalogItem> list= iServCheckupCatalogItemService.querySccUuid((String) map.get("sccUuid"));
        for(int i=0;i<((ArrayList)map.get("type")).size();i++){
            servCheckupCatalogItem.setScciUuid(list.get(i).getScciUuid());
            servCheckupCatalogItem.setSccUuid((String) map.get("sccUuid"));
            servCheckupCatalogItem.setUpdatedDate(new Date());
            servCheckupCatalogItem.setUpdatedBy("admin");
            servCheckupCatalogItem.setDescription((String) ((ArrayList) map.get("descriptions")).get(i));
            iServCheckupCatalogItemService.updateById(servCheckupCatalogItem);
        }

       return flag;
    }


}

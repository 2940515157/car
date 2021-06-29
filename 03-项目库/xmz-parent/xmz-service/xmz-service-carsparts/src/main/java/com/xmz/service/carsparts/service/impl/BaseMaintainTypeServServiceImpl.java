package com.xmz.service.carsparts.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.carsparts.entity.pojo.BaseMaintainTypeServ;
import com.xmz.service.carsparts.entity.pojo.BaseServCatalogBack;
import com.xmz.service.carsparts.entity.vo.MaintainTypeServCatalogBack;
import com.xmz.service.carsparts.entity.vo.QueryBMTS;
import com.xmz.service.carsparts.mapper.BaseMaintainTypeServMapper;
import com.xmz.service.carsparts.mapper.BaseServCatalogBackMapper;
import com.xmz.service.carsparts.service.IBaseMaintainTypeServService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 立洋保养类型与服务项目的对照表 服务实现类
 * </p>
 *
 * @author leiyaping
 * @since 2021-06-10
 */
@Service
public class BaseMaintainTypeServServiceImpl extends ServiceImpl<BaseMaintainTypeServMapper, BaseMaintainTypeServ> implements IBaseMaintainTypeServService {
    @Autowired(required=false)
    BaseServCatalogBackMapper baseServCatalogBackMapper;
    @Override
    public Page<MaintainTypeServCatalogBack> queryPage(Page<MaintainTypeServCatalogBack> page, QueryBMTS queryBMTS) {
        return baseMapper.queryPage(page,queryBMTS);
    }

    @Override
    public List<MaintainTypeServCatalogBack> queryselect(QueryBMTS queryBMTS) {
        return baseMapper.queryselect(queryBMTS);
    }

    @Override
    public int add(MaintainTypeServCatalogBack maintainTypeServCatalogBack) {
        BaseMaintainTypeServ baseMaintainTypeServ=new BaseMaintainTypeServ();
        baseMaintainTypeServ.setCatalogUuid(baseMaintainTypeServ.getCatalogUuid());
        baseMaintainTypeServ.setRemark(baseMaintainTypeServ.getRemark());
        baseMaintainTypeServ.setVersion(new BigDecimal(1));
        int num=baseMapper.insert(baseMaintainTypeServ);

        BaseServCatalogBack baseServCatalogBack=new BaseServCatalogBack();
        baseServCatalogBack.setCatalogUuid(baseMaintainTypeServ.getCatalogUuid());
        baseServCatalogBack.setCatalogName(baseServCatalogBack.getCatalogName());
        baseServCatalogBack.setVersion(new BigDecimal(1));
        baseServCatalogBackMapper.insert(baseServCatalogBack);

        return num;
    }

    @Override
    public int update(MaintainTypeServCatalogBack maintainTypeServCatalogBack) {
        BaseMaintainTypeServ baseMaintainTypeServ=new BaseMaintainTypeServ();
        baseMaintainTypeServ.setMtsUuid(baseMaintainTypeServ.getMtsUuid());
        baseMaintainTypeServ.setCatalogUuid(baseMaintainTypeServ.getCatalogUuid());
        baseMaintainTypeServ.setRemark(baseMaintainTypeServ.getRemark());
        baseMaintainTypeServ.setVersion(new BigDecimal(1));
        int num=baseMapper.updateById(baseMaintainTypeServ);

        BaseServCatalogBack baseServCatalogBack=new BaseServCatalogBack();
        baseServCatalogBack.setCatalogUuid(baseMaintainTypeServ.getCatalogUuid());
        baseServCatalogBack.setCatalogName(baseServCatalogBack.getCatalogName());
        baseServCatalogBack.setVersion(new BigDecimal(1));
        baseServCatalogBackMapper.updateById(baseServCatalogBack);

        return num;
    }


}

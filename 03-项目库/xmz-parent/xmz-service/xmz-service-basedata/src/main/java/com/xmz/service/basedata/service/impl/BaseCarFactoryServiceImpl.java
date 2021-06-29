package com.xmz.service.basedata.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.basedata.entity.pojo.BaseCarBrand;
import com.xmz.service.basedata.entity.pojo.BaseCarFactory;
import com.xmz.service.basedata.entity.vo.BaseFactoryQuery;
import com.xmz.service.basedata.mapper.BaseCarFactoryMapper;
import com.xmz.service.basedata.service.IBaseCarFactoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 厂家 服务实现类
 * </p>
 *
 * @author lm
 * @since 2021-05-31
 */
@Service
public class BaseCarFactoryServiceImpl extends ServiceImpl<BaseCarFactoryMapper, BaseCarFactory> implements IBaseCarFactoryService {


    @Override
    public Page<BaseCarFactory> myPage(Page<BaseCarFactory> pageParam, BaseFactoryQuery baseFactoryQuery) {
        return baseMapper.myPage(pageParam,baseFactoryQuery);
    }

    @Override
    public List<BaseCarFactory> queryByBrandUuid(String id) {
        return baseMapper.queryByBrandUuid(id);
    }
}

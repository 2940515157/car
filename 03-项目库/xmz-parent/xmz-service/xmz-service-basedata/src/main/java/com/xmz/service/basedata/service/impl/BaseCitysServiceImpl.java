package com.xmz.service.basedata.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.common.serviceBase.MyQueryWrapper;
import com.xmz.service.basedata.entity.pojo.BaseCitys;
import com.xmz.service.basedata.entity.pojo.BaseProvinces;
import com.xmz.service.basedata.mapper.BaseCitysMapper;
import com.xmz.service.basedata.service.IBaseCitysService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 城市 服务实现类
 * </p>
 *
 * @author leiyaping
 * @since 2021-06-03
 */
@Service
public class BaseCitysServiceImpl extends ServiceImpl<BaseCitysMapper, BaseCitys> implements IBaseCitysService {

    @Override
    public Page<BaseCitys> queryPage(Page<BaseCitys> page,String countryUuid,String provUuid) {
        QueryWrapper<BaseCitys> queryWrapper=new QueryWrapper<BaseCitys>()
                .eq("COUNTRY_UUID",countryUuid)
                .eq("PROV_UUID",provUuid);
        return baseMapper.selectPage(page,queryWrapper);
    }

    @Override
    public BaseCitys queryById(String countryUuid,String provUuid, String cityUuid) {
        QueryWrapper<BaseCitys> queryWrapper=new MyQueryWrapper<BaseCitys>()
                .eq("COUNTRY_UUID",countryUuid)
                .eq("PROV_UUID",provUuid)
                .eq("CITY_UUID",cityUuid);
        return baseMapper.selectOne(queryWrapper);
    }
}

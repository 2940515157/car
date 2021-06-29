package com.xmz.service.basedata.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.common.serviceBase.MyQueryWrapper;
import com.xmz.service.basedata.entity.pojo.BaseCountrys;
import com.xmz.service.basedata.entity.pojo.BaseProvinces;
import com.xmz.service.basedata.entity.pojo.EosDictEntry;
import com.xmz.service.basedata.mapper.BaseProvincesMapper;
import com.xmz.service.basedata.service.IBaseProvincesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 省份信息 服务实现类
 * </p>
 *
 * @author leiyaping
 * @since 2021-06-03
 */
@Service
public class BaseProvincesServiceImpl extends ServiceImpl<BaseProvincesMapper, BaseProvinces> implements IBaseProvincesService {

    @Override
    public Page<BaseProvinces> queryPage(Page<BaseProvinces> page,String id) {
        QueryWrapper<BaseProvinces> queryWrapper=new QueryWrapper<BaseProvinces>()
                .eq("COUNTRY_UUID",id);
        return baseMapper.selectPage(page,queryWrapper);
    }

    @Override
    public BaseProvinces queryById(String countryUuid, String provUuid) {
        QueryWrapper<BaseProvinces> queryWrapper=new MyQueryWrapper<BaseProvinces>()
                .eq("COUNTRY_UUID",countryUuid)
                .eq("PROV_UUID",provUuid);
        return baseMapper.selectOne(queryWrapper);
    }
}

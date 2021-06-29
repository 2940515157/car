package com.xmz.service.basedata.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.basedata.entity.pojo.BaseCountrys;
import com.xmz.service.basedata.mapper.BaseCountrysMapper;
import com.xmz.service.basedata.service.IBaseCountrysService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 国家信息 服务实现类
 * </p>
 *
 * @author leiyaping
 * @since 2021-06-03
 */
@Service
public class BaseCountrysServiceImpl extends ServiceImpl<BaseCountrysMapper, BaseCountrys> implements IBaseCountrysService {

    @Override
    public Page<BaseCountrys> queryPage(Page<BaseCountrys> page) {
        QueryWrapper<BaseCountrys> queryWrapper=new QueryWrapper<BaseCountrys>();

        return baseMapper.selectPage(page,queryWrapper);
    }
}

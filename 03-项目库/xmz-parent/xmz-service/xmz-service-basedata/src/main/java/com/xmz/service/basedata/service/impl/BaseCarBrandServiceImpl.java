package com.xmz.service.basedata.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.basedata.entity.pojo.BaseCarBrand;
import com.xmz.service.basedata.entity.vo.BaseCarQuery;
import com.xmz.service.basedata.mapper.BaseCarBrandMapper;
import com.xmz.service.basedata.service.IBaseCarBrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import feign.QueryMap;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 品牌(车辆) 服务实现类
 * </p>
 *
 * @author CodeGenerator
 * @since 2021-05-25
 */
@Service
public class BaseCarBrandServiceImpl extends ServiceImpl<BaseCarBrandMapper, BaseCarBrand> implements IBaseCarBrandService {

    @Override
    public Page<BaseCarBrand> myPage(Page<BaseCarBrand> pageParam, BaseCarQuery baseCarQuery) {
        return baseMapper.myPage(pageParam,baseCarQuery);
    }
}

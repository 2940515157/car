package com.xmz.service.carsparts.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.carsparts.entity.pojo.BaseCarBrand;
import com.xmz.service.carsparts.mapper.BaseCarBrandMapper;
import com.xmz.service.carsparts.service.IBaseCarBrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 品牌(车辆) 服务实现类
 * </p>
 *
 * @author 江志威
 * @since 2021-06-15
 */
@Service
public class BaseCarBrandServiceImpl extends ServiceImpl<BaseCarBrandMapper, BaseCarBrand> implements IBaseCarBrandService {

    @Override
    public List<BaseCarBrand> queryQueryYi(String bcid) {
        return baseMapper.queryQueryYi(bcid);
    }
}

package com.xmz.service.carsparts.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.carsparts.entity.pojo.BaseCarPartsSku;
import com.xmz.service.carsparts.entity.vo.BaseCarPartsSkuQuery;
import com.xmz.service.carsparts.mapper.BaseCarPartsSkuMapper;
import com.xmz.service.carsparts.service.IBaseCarPartsSkuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 配件产品库(含品牌规格型号)(sku级) 服务实现类
 * </p>
 *
 * @author 江志威
 * @since 2021-06-16
 */
@Service
public class BaseCarPartsSkuServiceImpl extends ServiceImpl<BaseCarPartsSkuMapper, BaseCarPartsSku> implements IBaseCarPartsSkuService {

    @Override
    public Page<BaseCarPartsSku> queryQuery(Page<BaseCarPartsSku> pageParam, BaseCarPartsSkuQuery baseCarPartsSkuQuery) {
        return baseMapper.queryQuery(pageParam,baseCarPartsSkuQuery);
    }
}

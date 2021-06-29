package com.xmz.service.carsparts.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xmz.service.carsparts.entity.pojo.BaseMaintainCycle;
import com.xmz.service.carsparts.entity.vo.CarMaintainCycle;
import com.xmz.service.carsparts.entity.vo.QueryParams;
import com.xmz.service.carsparts.mapper.BaseMaintainCycleMapper;
import com.xmz.service.carsparts.service.BaseMaintainCycleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 保养周期 服务实现类
 * </p>
 *
 * @author weikang
 * @since 2021-05-27
 */
@Service
public class BaseMaintainCycleServiceImpl extends ServiceImpl<BaseMaintainCycleMapper, BaseMaintainCycle> implements BaseMaintainCycleService {

    @Override
    public Page<CarMaintainCycle> queryPage(QueryParams queryParams, Page<CarMaintainCycle> page) {
        return baseMapper.queryPage(page,queryParams);
    }

    @Override
    public List<CarMaintainCycle> querySelect(QueryParams queryParams) {
        return baseMapper.querySelect(queryParams);
    }
}

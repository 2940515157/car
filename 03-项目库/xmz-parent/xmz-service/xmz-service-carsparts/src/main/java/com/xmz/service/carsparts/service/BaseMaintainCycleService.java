package com.xmz.service.carsparts.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmz.service.carsparts.entity.pojo.BaseMaintainCycle;
import com.xmz.service.carsparts.entity.vo.CarMaintainCycle;
import com.xmz.service.carsparts.entity.vo.QueryParams;

import java.util.List;

/**
 * <p>
 * 保养周期 服务类
 * </p>
 *
 * @author weikang
 * @since 2021-05-27
 */
public interface BaseMaintainCycleService extends IService<BaseMaintainCycle> {
    Page<CarMaintainCycle> queryPage(QueryParams queryParams, Page<CarMaintainCycle> page);

    List<CarMaintainCycle> querySelect(QueryParams queryParams);

}

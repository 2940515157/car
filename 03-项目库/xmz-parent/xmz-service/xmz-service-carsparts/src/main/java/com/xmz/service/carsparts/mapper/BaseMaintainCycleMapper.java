package com.xmz.service.carsparts.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.carsparts.entity.pojo.BaseMaintainCycle;
import com.xmz.service.carsparts.entity.vo.CarMaintainCycle;
import com.xmz.service.carsparts.entity.vo.QueryParams;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 保养周期 Mapper 接口
 * </p>
 *
 * @author weikang
 * @since 2021-05-27
 */
public interface BaseMaintainCycleMapper extends BaseMapper<BaseMaintainCycle> {

    Page<CarMaintainCycle> queryPage(Page<CarMaintainCycle> page,
                                     @Param("qp") QueryParams queryParams);

    //查询搜索下拉框数据
    List<CarMaintainCycle> querySelect(QueryParams queryParams);

}

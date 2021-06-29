package com.xmz.service.carsparts.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.carsparts.entity.pojo.BaseMaintainTypeServ;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmz.service.carsparts.entity.vo.MaintainTypeServCatalogBack;
import com.xmz.service.carsparts.entity.vo.QueryBMTS;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 立洋保养类型与服务项目的对照表 Mapper 接口
 * </p>
 *
 * @author leiyaping
 * @since 2021-06-10
 */
public interface BaseMaintainTypeServMapper extends BaseMapper<BaseMaintainTypeServ> {
    //带条件分页查询
    Page<MaintainTypeServCatalogBack> queryPage(Page<MaintainTypeServCatalogBack> page,
                                                @Param("qp")QueryBMTS queryBMTS);
    //查询搜索下拉框数据
    List<MaintainTypeServCatalogBack> queryselect(QueryBMTS queryBMTS);
}

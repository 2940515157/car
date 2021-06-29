package com.xmz.service.carsparts.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.carsparts.entity.pojo.BaseMaintainTypeServ;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmz.service.carsparts.entity.vo.MaintainTypeServCatalogBack;
import com.xmz.service.carsparts.entity.vo.QueryBMTS;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 立洋保养类型与服务项目的对照表 服务类
 * </p>
 *
 * @author leiyaping
 * @since 2021-06-10
 */
public interface IBaseMaintainTypeServService extends IService<BaseMaintainTypeServ> {
    Page<MaintainTypeServCatalogBack> queryPage(Page<MaintainTypeServCatalogBack> page, QueryBMTS queryBMTS);
    List<MaintainTypeServCatalogBack> queryselect(QueryBMTS queryBMTS);
    int add(MaintainTypeServCatalogBack maintainTypeServCatalogBack);
    int update(MaintainTypeServCatalogBack maintainTypeServCatalogBack);
}

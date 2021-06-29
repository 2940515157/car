package com.xmz.br.storages.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.br.storages.entity.pojo.WmWarehouse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmz.br.storages.entity.vo.WmWarehouseQuery;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 仓库 Mapper 接口
 * </p>
 *
 * @author hwb
 * @since 2021-06-16
 */
public interface WmWarehouseMapper extends BaseMapper<WmWarehouse> {
    // 多条件分页查询
    public Page<WmWarehouse> pageQuery(Page<WmWarehouse> pageParam,
                                       @Param("query") WmWarehouseQuery wmWarehouseQuery);
}

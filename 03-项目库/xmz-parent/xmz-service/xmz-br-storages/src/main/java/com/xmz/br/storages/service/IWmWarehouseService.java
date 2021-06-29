package com.xmz.br.storages.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.br.storages.entity.pojo.WmWarehouse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmz.br.storages.entity.vo.WmWarehouseQuery;

/**
 * <p>
 * 仓库 服务类
 * </p>
 *
 * @author hwb
 * @since 2021-06-16
 */
public interface IWmWarehouseService extends IService<WmWarehouse> {
    // 多条件分页查询
    public Page<WmWarehouse> pageQuery(Page<WmWarehouse> pageParam,WmWarehouseQuery wmWarehouseQuery);
}

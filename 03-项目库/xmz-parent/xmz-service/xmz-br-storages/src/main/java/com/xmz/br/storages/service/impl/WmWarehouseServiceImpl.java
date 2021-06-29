package com.xmz.br.storages.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.br.storages.entity.pojo.WmWarehouse;
import com.xmz.br.storages.entity.vo.WmWarehouseQuery;
import com.xmz.br.storages.mapper.WmWarehouseMapper;
import com.xmz.br.storages.service.IWmWarehouseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 仓库 服务实现类
 * </p>
 *
 * @author hwb
 * @since 2021-06-16
 */
@Service
public class WmWarehouseServiceImpl extends ServiceImpl<WmWarehouseMapper, WmWarehouse> implements IWmWarehouseService {

//    @Autowired
//    IWmWarehouseService wmWarehouseService;
    @Override
    public Page<WmWarehouse> pageQuery(Page<WmWarehouse> pageParam, WmWarehouseQuery wmWarehouseQuery) {
        return baseMapper.pageQuery(pageParam,wmWarehouseQuery);
    }
}

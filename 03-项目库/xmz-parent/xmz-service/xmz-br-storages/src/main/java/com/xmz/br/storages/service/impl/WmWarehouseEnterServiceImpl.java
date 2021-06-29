package com.xmz.br.storages.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.br.storages.entity.pojo.EosDictEntry;
import com.xmz.br.storages.entity.pojo.WmWarehouse;
import com.xmz.br.storages.entity.pojo.WmWarehouseEnter;
import com.xmz.br.storages.entity.vo.WWhouseEnterQuery;
import com.xmz.br.storages.entity.vo.WmWarehouseQuery;
import com.xmz.br.storages.mapper.WmWarehouseEnterMapper;
import com.xmz.br.storages.service.IWmWarehouseEnterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 入库单 服务实现类
 * </p>
 *
 * @author hwb
 * @since 2021-06-16
 */
@Service
public class WmWarehouseEnterServiceImpl extends ServiceImpl<WmWarehouseEnterMapper, WmWarehouseEnter> implements IWmWarehouseEnterService {

    @Override
    public Page<WmWarehouseEnter> myPage(Page<WmWarehouseEnter> pageParam, WWhouseEnterQuery wWhouseEnterQuery) {
        return baseMapper.myPage(pageParam,wWhouseEnterQuery);
    }

    @Override
    public List<WmWarehouse> listAll() {
        return baseMapper.listAll();
    }

    @Override
    public List<EosDictEntry> listAllwwetype() {
        return baseMapper.listAllwwetype();
    }

    @Override
    public List<EosDictEntry> listAllfromtype() {
        return baseMapper.listAllfromtype();
    }
}

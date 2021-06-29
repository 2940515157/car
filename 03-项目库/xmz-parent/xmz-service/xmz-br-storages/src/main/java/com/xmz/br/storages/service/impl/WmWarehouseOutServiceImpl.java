package com.xmz.br.storages.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.br.storages.entity.pojo.EosDictEntry;
import com.xmz.br.storages.entity.pojo.WmWarehouseOut;
import com.xmz.br.storages.entity.vo.WWhouseOutQuery;
import com.xmz.br.storages.mapper.WmWarehouseOutMapper;
import com.xmz.br.storages.service.IWmWarehouseOutService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 出库单 服务实现类
 * </p>
 *
 * @author hwb
 * @since 2021-06-19
 */
@Service
public class WmWarehouseOutServiceImpl extends ServiceImpl<WmWarehouseOutMapper, WmWarehouseOut> implements IWmWarehouseOutService {

    @Override
    public Page<WmWarehouseOut> myPage(Page<WmWarehouseOut> pageParam, WWhouseOutQuery wWhouseOutQuery) {
        return baseMapper.myPage(pageParam,wWhouseOutQuery);
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

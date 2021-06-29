package com.xmz.br.storages.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.br.storages.entity.pojo.EosDictEntry;
import com.xmz.br.storages.entity.pojo.WmWarehouse;
import com.xmz.br.storages.entity.pojo.WmWarehouseEnter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmz.br.storages.entity.vo.WWhouseEnterQuery;
import com.xmz.br.storages.entity.vo.WmWarehouseQuery;

import java.util.List;

/**
 * <p>
 * 入库单 服务类
 * </p>
 *
 * @author hwb
 * @since 2021-06-16
 */
public interface IWmWarehouseEnterService extends IService<WmWarehouseEnter> {
    Page<WmWarehouseEnter> myPage(Page<WmWarehouseEnter> pageParam,
                         WWhouseEnterQuery wWhouseEnterQuery);

    List<WmWarehouse> listAll();

    List<EosDictEntry> listAllwwetype();

    List<EosDictEntry> listAllfromtype();
}

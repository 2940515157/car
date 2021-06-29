package com.xmz.br.storages.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.br.storages.entity.pojo.EosDictEntry;
import com.xmz.br.storages.entity.pojo.WmWarehouseEnter;
import com.xmz.br.storages.entity.pojo.WmWarehouseOut;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmz.br.storages.entity.vo.WWhouseEnterQuery;
import com.xmz.br.storages.entity.vo.WWhouseOutQuery;

import java.util.List;

/**
 * <p>
 * 出库单 服务类
 * </p>
 *
 * @author hwb
 * @since 2021-06-19
 */
public interface IWmWarehouseOutService extends IService<WmWarehouseOut> {
    Page<WmWarehouseOut> myPage(Page<WmWarehouseOut> pageParam,
                                  WWhouseOutQuery wWhouseOutQuery);

    List<EosDictEntry> listAllwwetype();

    List<EosDictEntry> listAllfromtype();
}

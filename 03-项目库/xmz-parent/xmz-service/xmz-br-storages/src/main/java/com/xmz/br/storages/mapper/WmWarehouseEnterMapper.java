package com.xmz.br.storages.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.br.storages.entity.pojo.EosDictEntry;
import com.xmz.br.storages.entity.pojo.WmWarehouse;
import com.xmz.br.storages.entity.pojo.WmWarehouseEnter;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmz.br.storages.entity.vo.WWhouseEnterQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 入库单 Mapper 接口
 * </p>
 *
 * @author hwb
 * @since 2021-06-16
 */
public interface WmWarehouseEnterMapper extends BaseMapper<WmWarehouseEnter> {
    Page<WmWarehouseEnter> myPage(Page<WmWarehouseEnter> pageParam,
                         @Param(value = "wwe") WWhouseEnterQuery wWhouseEnterQuery);

    List<WmWarehouse> listAll();

    List<EosDictEntry> listAllwwetype();

    List<EosDictEntry> listAllfromtype();

}

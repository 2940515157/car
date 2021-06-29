package com.xmz.br.storages.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.br.storages.entity.pojo.EosDictEntry;
import com.xmz.br.storages.entity.pojo.ServReceiveCar;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmz.br.storages.entity.pojo.WmWarehouseEnter;
import com.xmz.br.storages.entity.pojo.WmWarehouseOut;
import com.xmz.br.storages.entity.vo.WWhouseEnterQuery;
import com.xmz.br.storages.entity.vo.WWhouseOutQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *出库单 Mapper 接口
 * </p>
 *
 * @author hwb
 * @since 2021-06-19
 */
public interface WmWarehouseOutMapper extends BaseMapper<WmWarehouseOut> {
    Page<WmWarehouseOut> myPage(Page<WmWarehouseOut> pageParam,
                                @Param(value = "wwo") WWhouseOutQuery wWhouseOutQuery);

    List<EosDictEntry> listAllwwetype();

    List<EosDictEntry> listAllfromtype();
}

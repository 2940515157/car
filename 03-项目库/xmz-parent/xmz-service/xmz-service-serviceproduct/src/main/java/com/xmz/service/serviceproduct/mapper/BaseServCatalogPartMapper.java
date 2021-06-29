package com.xmz.service.serviceproduct.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.serviceproduct.entity.pojo.BaseServCatalogPart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmz.service.serviceproduct.entity.vo.MyBaseServCatalogPart;

/**
 * <p>
 * 总部服务目录与配件目录的对应关系，描述某一服务需要哪些零配件 Mapper 接口
 * </p>
 *
 * @author liujiaxi
 * @since 2021-06-09
 */
public interface BaseServCatalogPartMapper extends BaseMapper<BaseServCatalogPart> {

    /**
     * 根据服务项目id 分页查询出和配件对应的关系
     * @param pageParam
     * @param catalogUuid
     * @return
     */
    Page<MyBaseServCatalogPart> queryByPart(Page<MyBaseServCatalogPart>pageParam,String catalogUuid);

}

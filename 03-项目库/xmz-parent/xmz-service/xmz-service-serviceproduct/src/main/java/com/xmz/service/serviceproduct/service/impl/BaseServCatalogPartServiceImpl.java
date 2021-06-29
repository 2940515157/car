package com.xmz.service.serviceproduct.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.serviceproduct.entity.pojo.BaseServCatalogPart;
import com.xmz.service.serviceproduct.entity.vo.MyBaseServCatalogPart;
import com.xmz.service.serviceproduct.mapper.BaseServCatalogPartMapper;
import com.xmz.service.serviceproduct.service.IBaseServCatalogPartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 总部服务目录与配件目录的对应关系，描述某一服务需要哪些零配件 服务实现类
 * </p>
 *
 * @author liujiaxi
 * @since 2021-06-09
 */
@Service
public class BaseServCatalogPartServiceImpl extends ServiceImpl<BaseServCatalogPartMapper, BaseServCatalogPart> implements IBaseServCatalogPartService {
    @Autowired(required = false)
    BaseServCatalogPartMapper baseServCatalogPartMapper;
    /**
     * 根据服务项目id 分页查询出和配件对应的关系
     * @param pageParam
     * @param catalogUuid
     * @return
     */
    @Override
    public Page<MyBaseServCatalogPart> queryByPart(Page<MyBaseServCatalogPart> pageParam, String catalogUuid) {
        return baseServCatalogPartMapper.queryByPart(pageParam,catalogUuid);
    }

}

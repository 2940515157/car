package com.xmz.service.serviceproduct.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.serviceproduct.entity.pojo.BaseServCatalog;
import com.xmz.service.serviceproduct.entity.vo.BaseServCatalogQuery;
import com.xmz.service.serviceproduct.entity.vo.MyBaseServCatalog;
import com.xmz.service.serviceproduct.mapper.BaseServCatalogMapper;
import com.xmz.service.serviceproduct.service.IBaseServCatalogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 总部统一服务目录 服务实现类
 * </p>
 *
 * @author liujiaxi
 * @since 2021-06-08
 */
@Service
public class BaseServCatalogServiceImpl extends ServiceImpl<BaseServCatalogMapper, BaseServCatalog> implements IBaseServCatalogService {
    @Autowired(required = false)
    BaseServCatalogMapper baseServCatalogMapper;

    /**
     * 分页条件查询服务项目
     * @param pageParam
     * @param baseServCatalogQuery
     * @return
     */
    @Override
    public Page<MyBaseServCatalog> queryAllPage(Page<MyBaseServCatalog> pageParam, BaseServCatalogQuery baseServCatalogQuery) {
        return baseServCatalogMapper.queryAllPage(pageParam,baseServCatalogQuery);
    }

    @Override
    public boolean updateCommRateSa(BaseServCatalog baseServCatalog) {
        boolean flag=baseServCatalogMapper.updateCommRateSa(baseServCatalog);
        return flag;
    }

    @Override
    public boolean updateCommRateWorker(BaseServCatalog baseServCatalog) {
        boolean flag= baseServCatalogMapper.updateCommRateWorker(baseServCatalog);
        return flag;
    }


}

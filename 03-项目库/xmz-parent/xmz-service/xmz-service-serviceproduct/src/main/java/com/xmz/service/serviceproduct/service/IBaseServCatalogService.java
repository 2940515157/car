package com.xmz.service.serviceproduct.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.serviceproduct.entity.pojo.BaseServCatalog;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmz.service.serviceproduct.entity.vo.BaseServCatalogQuery;
import com.xmz.service.serviceproduct.entity.vo.MyBaseServCatalog;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 总部统一服务目录 服务类
 * </p>
 *
 * @author liujiaxi
 * @since 2021-06-08
 */
public interface IBaseServCatalogService extends IService<BaseServCatalog> {
    /**
     * 分页条件查询服务项目
     * @param pageParam
     * @param baseServCatalogQuery
     * @return
     */
    Page<MyBaseServCatalog> queryAllPage(Page<MyBaseServCatalog> pageParam, BaseServCatalogQuery baseServCatalogQuery);


    /**
     * 修改SA提成
     * @param baseServCatalog
     * @return
     */
    boolean updateCommRateSa(BaseServCatalog baseServCatalog);

    /**
     * 修改技师提成
     * @param baseServCatalog
     * @return
     */
    boolean updateCommRateWorker(BaseServCatalog baseServCatalog);


}

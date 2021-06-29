package com.xmz.service.serviceproduct.service;

import com.xmz.service.serviceproduct.entity.pojo.ServCheckupCatalogItem;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 点检项目明细 服务类
 * </p>
 *
 * @author zt
 * @since 2021-05-28
 */
public interface IServCheckupCatalogItemService extends IService<ServCheckupCatalogItem> {
    /**
     * 根据项目名称id删除
     * @param id
     * @return
     */
    int deleteBySccUuid(String id);

    /**
     * 根据项目名称id 查询
     * @param sccUuid
     * @return
     */
    List<ServCheckupCatalogItem> querySccUuid(String sccUuid);
}

package com.xmz.service.serviceproduct.mapper;

import com.xmz.service.serviceproduct.entity.pojo.ServCheckupCatalogItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 点检项目明细 Mapper 接口
 * </p>
 *
 * @author zt
 * @since 2021-05-28
 */
public interface ServCheckupCatalogItemMapper extends BaseMapper<ServCheckupCatalogItem> {

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

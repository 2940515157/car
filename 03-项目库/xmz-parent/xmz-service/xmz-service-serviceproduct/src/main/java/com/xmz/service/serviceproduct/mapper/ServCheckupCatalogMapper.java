package com.xmz.service.serviceproduct.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.serviceproduct.entity.pojo.MyServCheckupCatalog;
import com.xmz.service.serviceproduct.entity.pojo.ServCheckupCatalog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmz.service.serviceproduct.entity.vo.ServCheckupCatalogQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 点检项目 Mapper 接口
 * </p>
 *
 * @author zt
 * @since 2021-05-28
 */
public interface ServCheckupCatalogMapper extends BaseMapper<ServCheckupCatalog> {
    /**
     * 分页的多表查询方法
     * @param pageParam  分页参数
     * @param servCheckupCatalogQuery  查询条件对象
     * @return
     */
    Page<MyServCheckupCatalog> myPage(Page<MyServCheckupCatalog> pageParam,
                                      @Param(value = "scc") ServCheckupCatalogQuery servCheckupCatalogQuery);

    /**
     * 根据点检项目名称查询
     * @param catalogName
     * @return
     */
    ServCheckupCatalog querycatalogName(String catalogName);

    /**
     * 根据点检项目id查询信息
     * @param sccUuid
     * @return
     */
    List<MyServCheckupCatalog> querySccUuid(String sccUuid);
}

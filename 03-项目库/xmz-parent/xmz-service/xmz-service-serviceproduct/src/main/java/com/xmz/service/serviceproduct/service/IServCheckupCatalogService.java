package com.xmz.service.serviceproduct.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.common.utils.ResultMap;
import com.xmz.service.serviceproduct.entity.pojo.MyServCheckupCatalog;
import com.xmz.service.serviceproduct.entity.pojo.ServCheckupCatalog;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmz.service.serviceproduct.entity.vo.ServCheckupCatalogQuery;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 点检项目 服务类
 * </p>
 *
 * @author zt
 * @since 2021-05-28
 */
public interface IServCheckupCatalogService extends IService<ServCheckupCatalog> {

    /**
     * 分页的多表查询方法
     * @param pageParam  分页参数
     * @param servCheckupCatalogQuery  查询条件对象
     * @return
     */
    Page<MyServCheckupCatalog> myPage(Page<MyServCheckupCatalog> pageParam,
                                      ServCheckupCatalogQuery servCheckupCatalogQuery);

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

    /**
     * 添加点检项目
     * @param myServCheckupCatalog
     * @return
     */
    boolean add(Map<String,Object> myServCheckupCatalog);

    /**
     * 修改点检项目
     * @param map
     * @return
     */
    boolean update(Map<String,Object> map);
}

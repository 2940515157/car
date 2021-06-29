package com.xmz.service.serviceproduct.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.serviceproduct.entity.pojo.MyServCheckupPkg;
import com.xmz.service.serviceproduct.entity.pojo.ServCheckupCatalog;
import com.xmz.service.serviceproduct.entity.pojo.ServCheckupPkg;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmz.service.serviceproduct.entity.vo.ServCheckupPkgQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 点检包 服务类
 * </p>
 *
 * @author zt
 * @since 2021-05-31
 */
public interface IServCheckupPkgService extends IService<ServCheckupPkg> {

    /**
     * 分页的多表查询方法
     * @param pageParam  分页参数
     * @param servCheckupPkgQuery  查询条件对象
     * @return
     */
    Page<ServCheckupPkg> myPage(Page<ServCheckupPkg> pageParam,
                                 ServCheckupPkgQuery servCheckupPkgQuery);

    /**
     * 根据点检包名称模糊查询
     * @param pkgName
     * @return
     */
    List<MyServCheckupPkg> queryPkgName(String pkgName);

    /**
     * 添加点检包
     * @param data
     * @return
     */
    boolean add(Map<String,String> data);

    /**
     * 根据点检包名称查询
     * @param pkgName
     * @return
     */
    ServCheckupPkg queryPkgName2(String pkgName);

    /**
     * 根据点检包名称查询
     * @param id
     * @return
     */
    ServCheckupPkg queryById(String id);

    /**
     * 添加点检包
     * @param data
     * @return
     */
    boolean update(Map<String,String> data);

    /**
     * 根据点检包id删除点检明细
     * @param scpUuid
     * @return
     */
    int deleteScpUuid(String scpUuid);

    /**
     * 上架下架状态修改
     * @param servCheckupPkg
     * @return
     */
    int updateStatus(ServCheckupPkg servCheckupPkg);


}

package com.xmz.service.serviceproduct.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.serviceproduct.entity.pojo.MyServCheckupCatalog;
import com.xmz.service.serviceproduct.entity.pojo.MyServCheckupPkg;
import com.xmz.service.serviceproduct.entity.pojo.ServCheckupPkg;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmz.service.serviceproduct.entity.vo.ServCheckupCatalogQuery;
import com.xmz.service.serviceproduct.entity.vo.ServCheckupPkgQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 点检包 Mapper 接口
 * </p>
 *
 * @author zt
 * @since 2021-05-31
 */
public interface ServCheckupPkgMapper extends BaseMapper<ServCheckupPkg> {

    /**
     * 分页的多表查询方法
     * @param pageParam  分页参数
     * @param servCheckupPkgQuery  查询条件对象
     * @return
     */
    Page<ServCheckupPkg> myPage(Page<ServCheckupPkg> pageParam,
                                      @Param(value = "scp") ServCheckupPkgQuery servCheckupPkgQuery);


    /**
     * 根据点检包名称查询
     * @param pkgName
     * @return
     */
    List<MyServCheckupPkg> queryPkgName(String pkgName);

    /**
     * 根据点检包名称查询
     * @param pkgName
     * @return
     */
    ServCheckupPkg queryPkgName2(String pkgName);

    /**
     * 根据点检包id删除点检明细
     * @param scpUuid
     * @return
     */
    int deleteScpUuid(String scpUuid);

//    /**
//     * 上架下架状态修改
//     * @param status
//     * @return
//     */
//    int updateStatus(String status);
}

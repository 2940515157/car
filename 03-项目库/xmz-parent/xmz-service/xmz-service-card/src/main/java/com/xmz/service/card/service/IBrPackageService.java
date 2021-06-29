package com.xmz.service.card.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmz.service.card.entity.pojo.BrPackage;
import com.xmz.service.card.entity.vo.BrPackageQuery;
import com.xmz.service.card.entity.vo.MyBrPackage;

/**
 * <p>
 * 门店套餐 服务类
 * </p>
 *
 * @author liujiaxi,
 * @since 2021-05-28
 */
public interface IBrPackageService extends IService<BrPackage> {

    /**
     * 门店套餐情况 分页多表查询方法
     * @param pageParam 分页参数
     * @param brPackageQuery 查询条件对象
     * @return
     */
    Page<MyBrPackage> queryAllBrPack(Page<MyBrPackage> pageParam, BrPackageQuery brPackageQuery);

    /**
     * 通过编号查询
     * @param brPackageUuid
     * @return
     */
    MyBrPackage getByBrPackageUuid(String brPackageUuid);

}

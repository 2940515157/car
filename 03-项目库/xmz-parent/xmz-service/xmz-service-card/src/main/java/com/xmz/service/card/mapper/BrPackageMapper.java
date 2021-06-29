package com.xmz.service.card.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.card.entity.pojo.BrPackage;
import com.xmz.service.card.entity.vo.BrPackageQuery;
import com.xmz.service.card.entity.vo.MyBrPackage;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 门店套餐 Mapper 接口
 * </p>
 *
 * @author liujiaxi,
 * @since 2021-05-28
 */
public interface BrPackageMapper extends BaseMapper<BrPackage> {

    /**
     * 门店套餐情况 分页多表查询方法
     * @param pageParam 分页参数
     * @param brPackageQuery 查询条件对象
     * @return
     */
    Page<MyBrPackage> queryAllBrPack(Page<MyBrPackage> pageParam,
                                     @Param(value = "brs") BrPackageQuery brPackageQuery);
    /**
     * 通过编号查询
     * @param brPackageUuid
     * @return
     */
    MyBrPackage getByBrPackageUuid(String brPackageUuid);
}

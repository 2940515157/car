package com.xmz.service.card.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.card.entity.pojo.BasePkgTagDirectory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmz.service.card.entity.vo.BasePkgTagDirectoryQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 套餐标签目录 服务类
 * </p>
 *
 * @author liujiaxi
 * @since 2021-05-28
 */
public interface IBasePkgTagDirectoryService extends IService<BasePkgTagDirectory> {

    /**
     * 查询左边套餐标签 树状
     * @return
     */
    List<BasePkgTagDirectory> findAllTaoCanBiaoQian();

    /**
     * 查询左边套餐标签 树状
     * @return
     */
    List<BasePkgTagDirectory> getMenuChildren(List<BasePkgTagDirectory> list);


    /**
     * 点击左边菜单  查询出右边的数据
     * @param pageParam
     * @param basePkgTagDirectoryQuery
     * @return
     */
    Page<BasePkgTagDirectory> getMenuRight(Page<BasePkgTagDirectory> pageParam, BasePkgTagDirectoryQuery basePkgTagDirectoryQuery);

}

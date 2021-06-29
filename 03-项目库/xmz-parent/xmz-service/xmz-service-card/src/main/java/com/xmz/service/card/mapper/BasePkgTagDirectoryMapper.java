package com.xmz.service.card.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.card.entity.pojo.BasePkgTagDirectory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmz.service.card.entity.vo.BasePkgTagDirectoryQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 套餐标签目录 Mapper 接口
 * </p>
 *
 * @author liujiaxi
 * @since 2021-05-28
 */
public interface BasePkgTagDirectoryMapper extends BaseMapper<BasePkgTagDirectory> {

    /**
     * 查询左边套餐标签 树状
     * @return
     */
   List<BasePkgTagDirectory> findAllTaoCanBiaoQian();

    /**
     * 查询左边套餐标签 树状
     * @return
     */
    List<BasePkgTagDirectory> getMenuChildren(String bptduuid);


    Page<BasePkgTagDirectory> getMenuRight(Page<BasePkgTagDirectory> pageParam,
                                           @Param("bq") BasePkgTagDirectoryQuery basePkgTagDirectoryQuery);

}

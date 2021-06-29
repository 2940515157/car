package com.xmz.service.serviceproduct.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.serviceproduct.entity.pojo.BaseServType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmz.service.serviceproduct.entity.vo.ServTypeQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 服务类型 Mapper 接口
 * </p>
 *
 * @author hwb
 * @since 2021-06-03
 */
public interface BaseServTypeMapper extends BaseMapper<BaseServType> {
    Page<BaseServType> myPage(Page<BaseServType> pageParam,
                                      @Param(value = "st") ServTypeQuery servTypeQuery);

    /**
     * 查询左边套餐标签 树状
     * @return
     */
    List<BaseServType> findAllBase();

    /**
     * 查询左边套餐标签 树状
     * @return
     */
    List<BaseServType> getMenuChildren(String stUuid);

}

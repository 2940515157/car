package com.xmz.service.basedata.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.basedata.entity.pojo.BaseCarFactory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmz.service.basedata.entity.vo.BaseFactoryQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 厂家 Mapper 接口
 * </p>
 *
 * @author lm
 * @since 2021-05-31
 */
public interface BaseCarFactoryMapper extends BaseMapper<BaseCarFactory> {
    /**
     * 分页的多表查询方法
     * @param pageParam  分页参数
     * @param baseFactoryQuery  查询条件对象
     * @return
     */
    Page<BaseCarFactory> myPage(Page<BaseCarFactory> pageParam,
                              @Param(value = "bcf") BaseFactoryQuery baseFactoryQuery);


    List<BaseCarFactory> queryByBrandUuid(String id);
}

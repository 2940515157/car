package com.xmz.service.hr.mapper;

import com.xmz.service.hr.entity.pojo.BaseCitys;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 城市 Mapper 接口
 * </p>
 *
 * @author 江志威
 * @since 2021-06-07
 */
public interface BaseCitysMapper extends BaseMapper<BaseCitys> {

    /**
     * 根据省份查询城市
     */
    List<BaseCitys> queryallByProvUuid(@Param(value = "id")String id);
}

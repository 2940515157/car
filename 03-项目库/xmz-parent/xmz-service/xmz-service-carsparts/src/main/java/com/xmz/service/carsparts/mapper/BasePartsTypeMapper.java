package com.xmz.service.carsparts.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.carsparts.entity.pojo.BasePartsType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmz.service.carsparts.entity.vo.BasePartsTypeQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 配件目录 Mapper 接口
 * </p>
 *
 * @author 江志威
 * @since 2021-05-31
 */
public interface BasePartsTypeMapper extends BaseMapper<BasePartsType> {
    /**
     * 获取总父节点
     */
    List<BasePartsType> carTypeFuTree();


    /**
     * 获取自身子节点
     */
    List<BasePartsType> carTypeZiTree(@Param(value = "parentTypeUuid") String parentTypeUuid);

    /**
     * 分页查询
     * @return
     */
    Page<BasePartsType> queryQuery(Page<BasePartsType> pageParam,
                                   @Param(value = "bptc") BasePartsTypeQuery basePartsTypeQuery);
}

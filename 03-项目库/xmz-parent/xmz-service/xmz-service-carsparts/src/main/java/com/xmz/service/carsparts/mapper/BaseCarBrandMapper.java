package com.xmz.service.carsparts.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.carsparts.entity.pojo.BaseCarBrand;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmz.service.carsparts.entity.pojo.BasePartsType;
import com.xmz.service.carsparts.entity.vo.BasePartsTypeQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 品牌(车辆) Mapper 接口
 * </p>
 *
 * @author 江志威
 * @since 2021-06-15
 */
public interface BaseCarBrandMapper extends BaseMapper<BaseCarBrand> {
    /**
     * 分页查询未选择品牌
     * @return
     */
    //Page<BaseCarBrand> queryQueryWei(Page<BaseCarBrand> pageParam);

    /**
     * 分页查询以选择品牌
     * @return
     */
    List<BaseCarBrand> queryQueryYi(@Param(value = "bcid") String bcid);
}
